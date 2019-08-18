package com.example.angelruiz.cursoandroid.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
//asignar permisos para android 6 tomar foto guardarla en galeria y mostrarla en IV
public class FragmentListaContacts extends Fragment  {
    View vista;
    private final String APP_NAME_DIRECTORY = "/misfotos.jpg";//esta constante(codigo de solicitud) sera el directorio donde se guardaran las imagenes tomadas
    private final int MY_PERMISSIONS=100;//esta constante permite saber al metodo requestPermissions() y onRequestPermissionsResult() entenderse y otorgar permisos
    private final int PHOTO_CODE=200;//esta constante permite comunicar al intent de la camara con el metodo onActivityResult() y realizar operaciones
    private final int SELECT_PICTURE=300;//esta constante permite comunicar al intent de la galeria con el metodo onActivityResult() y realizar operaciones
    String cPath,imageName;//

    private ImageView ivImg;
    private Button btnCargarImg;
    private Context context;//creamos nuestro objeto context ya que es mu necesario para otros objetos o metodos
    FrameLayout frameLayout;//nos permite pasarlo como parametro context al SnackBar, para ver donde esta el xml
    ProgressBar progressBar;

    public FragmentListaContacts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();//inicializamos nuestro context, obteniendo el contexto de donde nos encontramos
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       vista = inflater.inflate(R.layout.fragment_lista_contacts, container, false);
       ivImg = vista.findViewById(R.id.ivImg);
       btnCargarImg = vista.findViewById(R.id.btnCargarImg);
       frameLayout = vista.findViewById(R.id.frameContent);//inicializamos en content xml
       progressBar = vista.findViewById(R.id.pbImagen);

       if (myRequestStoragePermission()){//si los permisos se cumplen y aceptan, el boton cargarImagen se abilita si no no
           btnCargarImg.setEnabled(true);
       }else {
           btnCargarImg.setEnabled(false);
       }

       btnCargarImg.setOnClickListener(new View.OnClickListener() {//metodo onClick para el boton que muestra el menu, tomar foto y cargar imagen
          @Override
          public void onClick(View v) {
              showOptions();//si se presiona el boton se carga el metodo que trae el menuDialog con 3 opsiones
          }
      });
       return vista;
    }

    public boolean myRequestStoragePermission(){//este metodo permite pedir y comprobar los permisos de camara
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){//verificamos si la version de android es < a marshmellow 6 api 23 es
          return true;//,si es menor tomara los permisos del manifest y devuelve true, si no pasa al siguiente if()
      }
      //si la version es >= a marshmallow tomara y veridficara si se otorgaron estos permisos:WRITE_EXTERNAL_STORAGE y camera, verifica si son permisos garantizados(aceptados), si lo son devuelve true
      if ((context.checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED &&//mediante el contexto accedemos al metodo: checkSelfPermission(): el cual recibe como parametro cada permiso
                             context.checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)){//ambos son comprobados sin son garantizados
          return true;//si son aceptados accedemos a la camara y galeria
      }
      if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE) || shouldShowRequestPermissionRationale(CAMERA)) {//este metodo nos permite comprobar si cualquiera de los dos permisos fue denegado
          Snackbar.make(frameLayout, "Permisos necesarios", Snackbar.LENGTH_INDEFINITE)//de ser asi se nos pide informar al usuario que son necesarios para el completo funcionamiento de la app y debe permitirlos
          .setAction(android.R.string.ok, new View.OnClickListener() {
              @Override
              public void onClick(View v) {
               requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},MY_PERMISSIONS);//si se le da ok al SB, solocitamos los permisos con el metodo requestPermissions(), guardandolos en un arreglo String y pasandole el codigo de solicitud
              }
          }).show();
      }else {
              requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},MY_PERMISSIONS);//si no se da ok se solicitan de todos mosdos, en este caso pedimos 2 permisos el de la camara y el de galeria
      }
        return false;
    }
    //este metodo nos permite crear un menuDialog con varias opsiones, se ejecuta alpresionar el boton
    public void showOptions(){
      final AlertDialog.Builder builder = new AlertDialog.Builder(context);
      final CharSequence options[] = {"Tomar foto","Seleccionar foto","Cancelar"};
      builder.setTitle("Elegir una opción");
      builder.setItems(options, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
             if (options[which]=="Tomar foto"){
                 openCamera();
             }else if (options[which]=="Seleccionar foto"){
                 cargarImagen();
             }else if (options[which]=="Cancelar"){
                 dialog.dismiss();
             }
          }
      });
    builder.show();
    }

    private void openCamera() {//este metodo contiene la logica para acceder a la camara tomar foto, crear directorio y guardarla en galeria

            File file = new File(Environment.getExternalStorageDirectory(), APP_NAME_DIRECTORY);//creamos un objeto de tipo File(archivo), para crear un directorio dentro del directorio de almacenamiento, le pasamos nuestro directorio como segundo parametro
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//con este intent accedemos a la camara, y le mandamos mediante putExtra() el nuevo archivo(newFile,foto tomada)como Uri de tipo archivo, y la guarde en galeria

            boolean isDirectoryCreated = file.exists();//en esta variable booleana guardamos el objeto file que tiene guardado el directorio que se creara en la galeria

            if (!isDirectoryCreated) {//comprobamos si el directorio se a creado en el dispositivo
                isDirectoryCreated = file.mkdir();//si no esta creado lo creamos
                Toast.makeText(context, "Directory created ", Toast.LENGTH_SHORT).show();
            }
            if (isDirectoryCreated) {//si se a creado
                Long timesTamp = System.currentTimeMillis() / 1000;//creamos esta variable de tipo Long la cual genera el nombre de cada foto que se tome
                imageName = timesTamp.toString() + ".jpg";//la guardamos en una cadena mas una extencion .jpg
                cPath = Environment.getExternalStorageDirectory() + File.separator + APP_NAME_DIRECTORY + File.separator + imageName;//en esta cadena guardamos el directorio y el nombre de la imagen, separator es un slash(//) que nos indica que esta dentro de
                File newFileUri = new File(cPath);//en un nuevo objeto file pasamos la cadena cPath como parametro para mantenerlo como archivo

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //permissions for api 24 nougat a 7
               String authorities = context.getPackageName() + ".provider";
               Uri imageCameraUri = FileProvider.getUriForFile(context, authorities, newFileUri);
               camera.putExtra(MediaStore.EXTRA_OUTPUT, imageCameraUri);

            }else{
               camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFileUri));
            }
               startActivityForResult(camera, PHOTO_CODE);//con el metodo startActivityForResult(), mandamos el intent y la constante PHOTO_CODE, para llamarlo desde onActivityResult() y saber que hacer
         }
    }

    //este metodo permite mediante un intent implisito, acceder a la galeria para seleccionar una imagen, y mostrarla en un IV
    @SuppressLint("IntentReset")
    public void cargarImagen(){
        Intent cargarImg = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        cargarImg.setType("image/*");//permite seleccionar imagenes con cualquier extencion con /*, y determinar de que tipo sera el archivo
        startActivityForResult(Intent.createChooser(cargarImg,"Seleccione una App"), SELECT_PICTURE);//permite elegir una app para abrir la galeria, si se tienen varias apps de fotos/imagenes, y pasarle el codigo de solicitud SELECT_PICTURE
    }

    @Override//este metodo recibe los codigos de solicitud de cada startActivituResult(), de cada intent que envia uno, son de utilidad para los intent implicitos que abren apps o sition we
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//como vemos uno de sus parametros es requestCode, el cual recibe el codigo de silicitud
       super.onActivityResult(requestCode, resultCode, data);//el parametro data guarda la foto tomada, y con el podemos mostrarla en el IV

        switch (requestCode){//si el codigo de solicitud es igual al del intent que abre la camara, creamos la logica correspondiente,
           case PHOTO_CODE:
             //Toast.makeText(context, "tomarf", Toast.LENGTH_SHORT).show();
               MediaScannerConnection.scanFile(context, new String[]{cPath}, null,
                                            new MediaScannerConnection.OnScanCompletedListener() {//este metodo permite escanear la imagen recien creada, para poder mandarla a la galeria, recive cPath que trae la imagen y el directorio es lo que escaneara
            @Override
            public void onScanCompleted(String path, Uri uri) {//notifica que el escaneado del archivo(foto)a sido completado
               Log.i("External Storage", "Sacened" +path+ ":");
               Log.i("External Storage","--> Uri = " +uri);
              }
           });

               /*Thread hilo=new Thread(){//progressBar
                   @Override
                   public void run() {
                       super.run();
                       for (int x=0; x <=100; x++){
                         progressBar.setProgress(x);
                           try {
                               Thread.sleep(50);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   }
               };
               hilo.start();*/

               //cPath=Environment.getExternalStorageDirectory() + File.separator + APP_DIRECTORY + File.separator + imageName;
               Bitmap bitmap=BitmapFactory.decodeFile(cPath);//decodifica la ruta del archivo en un mapa de bits
               ivImg.setImageBitmap(bitmap);//mediante metodo setImageBitmap(): mostramos el archivo decodificado como mapa de bits(bitmap) en IV
               break;
        }
                                                     //si el codigo de solicitud(requestCode) es igual al de del intent que abre la galeria realiza lo siguiente
        if(data!=null && requestCode==SELECT_PICTURE){//verifica si la data trae una imagen y la clave de solicitud del requestCode es el mismo del startActivityForResult
           Uri path=data.getData();//si se cumple en una Uri guardamos(obtenemos) lo que trae la data
           ivImg.setImageURI(path);//y se lo pasamos al IV con su metodo setImageURI(), el cual recibe como parametro la data(imagen) guardada en la uri path
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("filePath",cPath);
    }

    @Override//este metodo trabaja de la mano con los permisos y el metodo requestPermissions(), ya que recibe la respuesta de cada permiso(GRANTED,DENIED) mediante el codigo de solicitud--cacha los permisos--
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_PERMISSIONS){//si el codigo solicitado es el de los permisos verifica
            //el parametro grantResults es un arreglo que guarda el numero de permisos que hemos solicitado
            if (grantResults.length==2 && grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){//si el resultado concedido es de tamaño 2(si el array grantResults titne 2 permisos), cada posision es un permiso, si el que esta en la posision 0 es aceptado(GRANTED) y el otro igual
                Toast.makeText(context, "Permisos aceptados", Toast.LENGTH_SHORT).show();//entonces ambos permisos han sido aceptados, se cumplen las 3 condiciones del if()
                btnCargarImg.setEnabled(true);//como han sido aceptados los permisos abilitamos el boton cargarImagen
            }
        }else {//si por el contrario el tamaño del arreglo grantResults[] no contiene los permisos o no son aceptados mostramos una ultima opsion para activarlos manualmente
            AlertDialog.Builder builder = new AlertDialog.Builder(context);//creamos AD, mensionando por que deben ser aceptados
             builder.setTitle("Permisos denegados");
             builder.setMessage("Es necesario aceptar los permisos"+"\n"+"Para el mejor funcionamiento de la App ");
             builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {//en la opsion aceptar creamos un intent para ir a ajustes y activar los permisos manualmente que necesita esta app
                @Override
              public void onClick(DialogInterface dialog, int which) {
               Intent ajustes=new Intent();
               ajustes.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
               Uri uri= Uri.fromParts("package", context.getPackageName(), null);
               ajustes.setData(uri);
               startActivity(ajustes);
               }
            });//si se da cancelar solo cerramos la ventana y se cierra la app->no se aceptaron los permisos
              builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                  @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                  }
              });
              builder.show();
        }
      }
    }

/*
int numero = Integer.parseInt(etTelefono.getText().toString());-->para numeros enteros
URI es como una URL en Internet (Uniform Resource Locator)
pero con un alcance más amplio. por ejemplo file:
something.txt, http://www.example.com/, ftp://example.com.
Cada cadena diferente antes de :indica un protocolo / controlador diferente.
 En Android, URI se utilizan para apuntar a otras aplicaciones, acciones, etc.,
por ejemplo tel:+44123456789. Vea aquí un ejemplo del uso de un Uri para hacer una llamada telefónica -*/

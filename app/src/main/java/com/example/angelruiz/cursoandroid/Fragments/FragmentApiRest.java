package com.example.angelruiz.cursoandroid.Fragments;


import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.angelruiz.cursoandroid.Activitys.WebServiceMysql;
import com.example.angelruiz.cursoandroid.Adapters.AdapterApiRest;
import com.example.angelruiz.cursoandroid.ArraysAPI_REST.ArrayWSMysqlApi;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.ICommunicateDialogFmtWithFragmentApiRest;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IEndPointAPI_REST;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IOnClickMenuItemRecyclerApiRest;
import com.example.angelruiz.cursoandroid.R;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.ArrayRespuestaApiRest;
import com.example.angelruiz.cursoandroid.RespuestaAPI_REST.FileInformationUploadImage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//we implements ifz ICommunicateDialogFmtWithFragmentApiRest for receive the data of daialogfragmenteditapirest
public class FragmentApiRest extends Fragment implements View.OnClickListener, ICommunicateDialogFmtWithFragmentApiRest {
    private static final int SELECT_PIKTURE = 100;
    private ProgressBar pb;
    private Uri pathImageSelected;
    View vista;
    Context context;
    private FloatingActionButton fabImagenApi;
    private EditText etNumeroFolio, etNombre, etProfesion;
    private Button btRegistraApi;
    private Retrofit retrofit;
    ArrayList<ArrayWSMysqlApi> listaJson;
    IEndPointAPI_REST service;
    private AdapterApiRest adapterApiRest;
    private static final String TAG = "API_REST_LOG_E";
    private RecyclerView rvDatosApiRest;
    private SwipeRefreshLayout srfRVAPI;
    private int positionPass;
    private String nameRceive, uriPathImage, imageNameUrl, mediaPath, result, encodedImage;
    private ImageView ivTesting;

    public FragmentApiRest() {
        // Required empty public constructor
    }

    //the fmt its join to activity content, is useful for communicate o pass data between fmts with one interface
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(context, "fmt joined to activity - onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getContext();
        listaJson = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_fragment_api_rest, container, false);

        fabImagenApi = vista.findViewById(R.id.fabImagenApi);
        etNumeroFolio = vista.findViewById(R.id.etNumeroFolio);
        etNombre = vista.findViewById(R.id.etNombre);
        etProfesion = vista.findViewById(R.id.etProfesion);
        btRegistraApi = vista.findViewById(R.id.btRegistraApi);

        pb = vista.findViewById(R.id.pbDatosApi);
        srfRVAPI = vista.findViewById(R.id.srfRVAPI);
        rvDatosApiRest = vista.findViewById(R.id.rvDatosApiRest);
        ivTesting = vista.findViewById(R.id.ivTesting);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pb.setVisibility(View.VISIBLE);
        btRegistraApi.setOnClickListener(this);

        rvDatosApiRest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        //rvDatosApiRest.setLayoutManager(gridLayoutManager);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder() //inicializamos nuestro obj retrofit
                .baseUrl("https://proyectosangelito.000webhostapp.com/webServiceMysql/") //url de la API, debe terminar con slash(/)rft2
                .addConverterFactory(GsonConverterFactory.create(gson)).build(); //GsonConverterFactory, nos permite decerealizar los datos Json

        service = retrofit.create(IEndPointAPI_REST.class); //unimos nuestra interfaz mediante el obj retrofit

        new DatosApiRest().execute(); //mostramos los datos, con una instancia de la clase que extiende de AsynckTask ejecutamos dicha clase en segundo plano, para ejecutar un AsynckTask creamos un objeto de la clase que lo extiende y accedemos a su metodo execute(), puede recibir parametros

        srfRVAPI.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DatosApiRest().execute(); //si refrescamos se ejecuta la instancia a la clase que trae los datos en segundo plano(AsynckTask)
            }
        });

        fabImagenApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cargarImagenGaleria();
            }
        });
    }

    //the fmt this created, in use for the user
    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(context, "fmt in use - onResume", Toast.LENGTH_SHORT).show();
    }

    //parametros a guardar en bd(api rest) mediante ws en interfaz
    @Override
    public void onClick(View v) {

        int numberFolio = Integer.parseInt(etNumeroFolio.getText().toString());
        String name = etNombre.getText().toString();
        String profetion = etProfesion.getText().toString();

        if (v.getId() == R.id.btRegistraApi) {

            uploadImageDataBase();
            registrarUsuarioApi(numberFolio, name, profetion, imageNameUrl);

            etNumeroFolio.setText("");
            etNombre.setText("");
            etProfesion.setText("");

            showRegisterApiRest();
        }
    }

    //register user in bd Api Rest
    private void registrarUsuarioApi(int numberFolio, final String name, String profetion, String image) {

        Call<ArrayRespuestaApiRest> registerUserApiRest = service.registroAPIRest(numberFolio, name, profetion, image); //mediante el obj de la interfaz accedemos a su metodo y le pasamos los parametros que pide
        registerUserApiRest.enqueue(new Callback<ArrayRespuestaApiRest>() { //con el objeto Call, llamamos al metodo equeue, para crear los metodos onResponse, onFilure, el primero gestiona la respuesta debuelta por el server, el segundo cacha si hay error de malformacion de JSON o wx
            @Override
            public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {//gestiona respuesta de datos de server

                if (response.isSuccessful()) {
                    Toast.makeText(context, "Usuario guerdado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al guardar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) {//gestiona si ocurren fallos al traer datos del server
                Toast.makeText(context, "Error server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("apirest", t.getMessage());
                notificationNewUser(name);
            }
        });
    }

    //we access to the galeri for select image to upload
    @SuppressLint("IntentReset")
    private void cargarImagenGaleria() {
        Intent imagenGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        imagenGaleria.setType("image/*");
        /*String [] typeImage = {"image/jpeg" , "image/png"};
        imagenGaleria.putExtra(Intent.EXTRA_MIME_TYPES, typeImage);*/
        startActivityForResult(Intent.createChooser(imagenGaleria, "Seleccione."), SELECT_PIKTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == SELECT_PIKTURE) {
            pathImageSelected = data.getData();
            uriPathImage = getRealPathFromUri(pathImageSelected);

            ivTesting.setImageBitmap(BitmapFactory.decodeFile(uriPathImage));

            /*
            select image of galery, using cursor for retrofit
            String [] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(pathImageSelected, filePathColumn, null, null, null);

            if (cursor != null){
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                ivTesting.setImageBitmap(BitmapFactory.decodeFile(uriPathImage));
                cursor.close();
            }*/

            /*
            select image of galery, using Bitmap for retrofit
            try {
             Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), pathImageSelected); //para mandar la imagen a server
             bitmap = resizeImage(bitmap, 300, 300);
             ivTesting.setImageBitmap(bitmap);

            } catch (IOException e) {
             e.printStackTrace();
            }*/

        }else{
            Toast.makeText(context, "¡Error al elegir imagen!", Toast.LENGTH_SHORT).show();
        }
    }

    //resize image
    private Bitmap resizeImage(Bitmap bitmap, float newWidth, float newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (width > newWidth || height > newHeight){
            float scaleWidth = newWidth / width;
            float scaleHeight = newHeight / height;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);

            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }else {
            return bitmap;
        }
    }

    private String getRealPathFromUri(Uri uri){

        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, uri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null){
            int columnIdx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(columnIdx);

            cursor.close();
        }
        return result;
    }

    //upload image to server
    private void uploadImageDataBase(){

        File fileImage = new File(uriPathImage);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), fileImage);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", fileImage.getName(), requestBody);

        imageNameUrl = "https://proyectosangelito.000webhostapp.com/webServiceMysql/imagenes/" + fileImage.getName();

        Call<FileInformationUploadImage> uploadImage = service.uploadImageServer(body);
        uploadImage.enqueue(new Callback<FileInformationUploadImage>() {
            @Override
            public void onResponse(@NonNull Call<FileInformationUploadImage> call, @NonNull Response<FileInformationUploadImage> response) {

                if (response.isSuccessful()){
                    Toast.makeText(context, "¡Imagen subio con exito!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "¡Error al subir imagen!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FileInformationUploadImage> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error intente más tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //notification push to register new user
    private void notificationNewUser(String name) {

        Intent i = new Intent(context, WebServiceMysql.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context,0, i, PendingIntent.FLAG_ONE_SHOT);
        Uri sonidoNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(context, "N")
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle("Nuevo usuario")
                .setContentText("Se registro, " + name)
                .setSound(sonidoNotification)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }

    //esta clase extiende de AsynckTask(hilo) ya que puede ser pesado traer los datos desde el servidor y lo hara en segundo plano
    @SuppressLint("StaticFieldLeak")
    public class DatosApiRest extends AsyncTask<Void, Integer, Void> {

        //lo que queremos que se ejecute antes del doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setMax(10); //el maximo para llenar el progressBar
        }

        //lo que se ejecuta durante la operacion
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]); //podemos colocar el progreso de un progressBar
        }

        //colocamos la logica en su metodo, doInBackground, lo que hara el AnsyckTask
        @Override
        protected Void doInBackground(Void... voids) {
            showRegisterApiRest();
            return null;
        }
    }

    //show data consumed of Api Rest
    private void showRegisterApiRest(){

      Call<ArrayRespuestaApiRest> callRegisterResponse = service.obtenerListadoJson();
      callRegisterResponse.enqueue(new Callback<ArrayRespuestaApiRest>() {

        //verificamos que la respuesta del servidor sea = 200 a 300 satisfactoria
        @Override
        public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {//el metodo response recibe,ArrayRespuestaApiRest, que guarda los datos de la API
          if (response.isSuccessful()) {

              //we save the response with the data in the object and we save in array listaJson (Body() trae la data)
              ArrayRespuestaApiRest respuestaApiRest = response.body();

              //we check if they are saved the data cunsumed of the api
              if (respuestaApiRest != null) {
                  listaJson = respuestaApiRest.getResults();

                  if (listaJson != null) {

                      //if there is data we set the params to adapter and we pass to the RV
                      adapterApiRest = new AdapterApiRest(context, listaJson);
                      rvDatosApiRest.setAdapter(adapterApiRest);

                      //we hide pb, refresh when is displayed the data in RV
                      pb.setVisibility(View.GONE);
                      srfRVAPI.setRefreshing(false);

                      //if already have data the adapter we can we call the menu popup
                      showMenuPopUpItemRV();

                      //mostramos los valores desde el ws por consola
                      for (ArrayWSMysqlApi x : listaJson) {
                          Log.e(TAG, "noms: " + x.getNombre());
                      }

                      for (int i = 0; i < listaJson.size(); i++) {
                          ArrayWSMysqlApi api = listaJson.get(i);
                          Log.e(TAG, "nombres: " + api.getNombre());
                      }
                  } else {
                      Toast.makeText(context, "vacio", Toast.LENGTH_SHORT).show();
                  }

              } else {
                  Toast.makeText(context, "sin respuesta", Toast.LENGTH_SHORT).show();
                  Log.e(TAG, "onResponse: "+ response.errorBody());
              }
          } else {
                Log.e(TAG, "onResponse: "+ response.errorBody());
            }
        }

        @Override
        public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) { //este metodo cacha si hay un error al conectar al servidor API
            Log.e(TAG, "onFailure: " + t.getMessage());
            Toast.makeText(context, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
    }

    //method genered to implement interface, bring the data to the dialogfragmenteditapirest
    @Override
    public void passDataDialogFragmentEdit(String name) {
      nameRceive = name;

      //this method receive two parameters, the idPersona and the name to update
      Call<ArrayRespuestaApiRest> editRegisterApiRest = service.actualizarRegistroApiRest(listaJson.get(positionPass).getIdPersona(), nameRceive);
      editRegisterApiRest.enqueue(new Callback<ArrayRespuestaApiRest>() {
          @Override
          public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {

              if (response.isSuccessful()){
                  Log.i("response", "succesfull");
              }else {
                  Log.i("response", "no succesfull");
              }
          }

          @Override
          public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) {
              Log.i("filure", t.getMessage());
          }
      });
    }

    //update, delete register of the RV through item menu popup
    private void showMenuPopUpItemRV(){

      adapterApiRest.setOnClickListenerDelete(new IOnClickMenuItemRecyclerApiRest() {
         @Override
         public void onClickImageDelete(View v, final int position) {

           PopupMenu popupMenu = new PopupMenu(context, v); //v is the control to will show the menu
           popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
           popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem menuItem) {
               switch (menuItem.getItemId()){

                 //we receive and show the dialogfragment
                 case R.id.mEdit:

                  positionPass = position; //we save position global for use in passDataDialogFragmentEdit

                  //we receive the data to the DialogFragmentEditApiRest through his objects
                  DialogFragmentEditApiRest dialogFragmentEditApiRest = new DialogFragmentEditApiRest();
                  dialogFragmentEditApiRest.setTargetFragment(FragmentApiRest.this, 1); //we receive the fmt target

                  //we show the dialogfragment
                  FragmentManager fragmentManager = getFragmentManager();

                  if (fragmentManager != null){
                      dialogFragmentEditApiRest.show(fragmentManager, "ShowDialogFragment");
                  }

                 break;

                 //delete item of RV throug his id
                 case R.id.mDelete:

                   //we pass as parameter the position of the idPersona RV to delete, asks IEndPointAPI_REST
                   Call<ArrayRespuestaApiRest> deleteRegisterApiRest = service.eliminarRegApiRest(listaJson.get(position).getIdPersona());
                   deleteRegisterApiRest.enqueue(new Callback<ArrayRespuestaApiRest>() {
                       @Override
                       public void onResponse(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Response<ArrayRespuestaApiRest> response) {

                           if (response.isSuccessful()){
                               Log.i("response", "succesfull");
                             }else {
                               Log.i("response", "no succesfull");
                               }
                           }

                           @Override
                           public void onFailure(@NonNull Call<ArrayRespuestaApiRest> call, @NonNull Throwable t) {
                               Log.i("filure", t.getMessage());
                           }
                       });

                     //show the items actualized when deleting 1 register
                     new DatosApiRest().execute();

                 break;
               }
                 return true; //we return true
             }
             });
             popupMenu.show();
            }
        });
    }

    //if it pass to other activity or fmt its pause the fmt, save his state current
    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(context, "fmt in pause - onPause", Toast.LENGTH_SHORT).show();
    }

    //fragment not visible if it press home or pass to other activity or fmt, stop task how animations or others(save state current)
    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(context, "fmt in stop - onStop", Toast.LENGTH_SHORT).show();
    }

    //if we press back or it destroy the activity its destroy the fmt, no save his state current(close all task)
    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(context, "fmt destroid - onDetach", Toast.LENGTH_SHORT).show();
    }
}

/*UPDATE PRUEBA SET NOMBRE = "PANCARSIO" WHERE NOMBRE = "ANGEL";
UPDATE PRUEBA SET NOMBRE = "PANCARSIO" WHERE idPersona = 20;*/



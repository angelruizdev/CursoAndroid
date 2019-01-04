package com.example.angelruiz.cursoandroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayFragmentListaRecycler;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
//para crear metodo OnClick en el RV, impelementamos la interface: View.OnClickListener y su metodo
public class AdapterFragmentListaRecycler extends
        RecyclerView.Adapter<AdapterFragmentListaRecycler.ViewHolderFragmentListRecycler> implements View.OnClickListener {

    private ArrayList<ArrayFragmentListaRecycler> personas;
    private Activity activity;
    private Context context;//(menu),necesitamos un contexto para el menuPopUp
    private int position;//esta variable recivira la posision del adapterRV que tenga cada item
    private View.OnClickListener listener;//declaramos un objeto de tipo View.OnClick,para el (menú) y el OnClick en el RV

    public AdapterFragmentListaRecycler(ArrayList<ArrayFragmentListaRecycler> personas, Activity activity, Context context){
        this.personas=personas;
        this.activity=activity;
        this.context=context;
    }
    //creamos sus metodos get y set para asignarle, mostrar su valos
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public ViewHolderFragmentListRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_fragm_list_recycler, null, false);
        vista.setOnClickListener(this);//mediante el objeto vista pasamos el contexto ,this, al metodo: setOnClickListener, para usarlo en el fragment o activity donde esta el RV, y poder hacer click en cada item con funciones y usar el (menú)
        return new ViewHolderFragmentListRecycler(vista);
    }

    @Override
    public void onBindViewHolder(final ViewHolderFragmentListRecycler holder, final int position) {
        holder.imgPersona.setImageResource(personas.get(position).getImgPersona());
        holder.nomPersona.setText(personas.get(position).getNomPersona());
        holder.itemView.setOnLongClickListener(null);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {//creamos metodo: onLongClick al item del RV,(menuContext)
            @Override
            public boolean onLongClick(View view) {//al dejar presionado un item del RV,(menuContext)
                setPosition(holder.getAdapterPosition());//asignamos la posision de cada item del RV, a la la variable position, cuando se presione un item(menuContext)
                return false;
            }
        });
        holder.tvEliminar.setOnClickListener(new View.OnClickListener() {//al control ,tvEliminar, le creamos el metodo onClick: para que al ser presionado, muestre el menu popUp
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.tvEliminar);//pasamos el contexto y el control de esta clase al constructor del menuPopUp como parametros
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mDetalle:
                                personas.remove(holder.getAdapterPosition());//del arreglo personas borramos la posision sleccionada desde el item del RV, con el metodo remove(), el cual recibe dicha posision
                                notifyItemRemoved(position);//notificamos con el metodo:notifyItemRemoved(), que dicha posision se a removido, recibe como parametro la position que se removio
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();//mostramos el menu
            }
        });
        //activity.startActivity(new Intent(activity, FragmentListaRecycler.class));
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }
    //damos cuerpo al metodo: setOnClickListener, pasandole el objeto escuchador ,listener, y lo inicializamos, este escuchara los eventos en el fgmt, al mismo tiempo sirve para pasar la posision al fragmentListaRecycler
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    //en el metodo que nos genera la interface implementada, pasamos al escuchador la vista, para que funcione el item del RV seleccionado
    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }
    //para crear menu popup en recycler, implementamos en este metodo la siguiente interfaz
    public class ViewHolderFragmentListRecycler extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView imgPersona;
        TextView nomPersona,tvEliminar;
        private ViewHolderFragmentListRecycler(View itemView) {
            super(itemView);
            imgPersona=itemView.findViewById(R.id.imgPersona);
            nomPersona=itemView.findViewById(R.id.nomPersona);
            tvEliminar=itemView.findViewById(R.id.tvEliminar);//(menú)inicializamos el control(vista) que conecta al menuPopup, desde la vista que infla el RV
            itemView.setOnCreateContextMenuListener(this);//(menú)mediante el objeto itemView accedemos al metodo: setOnCreateContextMenuListener, y le pasamos el contexto this
            itemView.setTag(this);//con el objeto itemView accedemos al metodo: setTag() que recibe como parametro el contexto this, el cual nos ayuda a enviar la posision del itemRV al FragmentListaRecycler, o a una Activity
        }
        //creamos nuestro menu context con puro java sin jalar un archivo menu xml
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderIcon(personas.get(position).getImgPersona());//con el parametro ,contextMenu, asignamos el icono del item seleccionado al menuContext
            contextMenu.setHeaderTitle(personas.get(position).getNomPersona());//con el parametro ,contextMenu, asignamos el titulo del menuContext con el nombre del item seleccionado
            contextMenu.add(Menu.NONE,1,1,"Eliminar");//con el parametro ,contextMenu, accedemos al metodo add(), con el cual inflamos el menu  creando los item eliminar y ver
            contextMenu.add(Menu.NONE,2,2,"ver");//este menuContext se conecta con un switch case en el FragmentListaRecycler para coordinarce con el Menu.NONE
        }
    }
}

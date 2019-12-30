package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayCrudFirebase;
import com.example.angelruiz.cursoandroid.R;

import java.util.List;

//class
public class AdapterCrudFireBaseRV extends RecyclerView.Adapter<AdapterCrudFireBaseRV.ViewHolderCrudFireBase> {

    //vars
    private Context context;
    private List<ArrayCrudFirebase> arrayCrudFirebase;

    //builder
    public AdapterCrudFireBaseRV(Context context, List<ArrayCrudFirebase> arrayCrudFirebases) {
        this.context = context;
        this.arrayCrudFirebase = arrayCrudFirebases;
    }

    //get and set for array
    public List<ArrayCrudFirebase> getArrayCrudFirebases() {
        return arrayCrudFirebase;
    }

    public void setArrayCrudFirebases(List<ArrayCrudFirebase> arrayCrudFirebases) {
        this.arrayCrudFirebase = arrayCrudFirebases;
    }

    //inflate the view RV
    @NonNull
    @Override
    public ViewHolderCrudFireBase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_inflate_crud_firebase, parent, false);
        return new ViewHolderCrudFireBase(view);
    }

    //create and inizialice views of RV
    public class ViewHolderCrudFireBase extends RecyclerView.ViewHolder {
        TextView tvUserNameFrb, tvUserEmailFrb;

        public ViewHolderCrudFireBase(@NonNull View itemView) {
            super(itemView);

            tvUserNameFrb = itemView.findViewById(R.id.tv_username_firebase);
            tvUserEmailFrb = itemView.findViewById(R.id.tv_useremail_firebase);
        }
    }

    //show data of firebase in views of RV
    @Override
    public void onBindViewHolder(@NonNull ViewHolderCrudFireBase holder, int position) {
        ArrayCrudFirebase result = arrayCrudFirebase.get(position);

        holder.tvUserNameFrb.setText(result.getFrbUserName());
        holder.tvUserEmailFrb.setText(result.getFrbUserEmail());
    }

    //return the size(content) of array
    @Override
    public int getItemCount() {
        return arrayCrudFirebase.size();
    }
}

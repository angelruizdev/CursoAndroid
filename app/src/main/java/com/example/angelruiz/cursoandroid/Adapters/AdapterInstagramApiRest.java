package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramObjects;
import com.example.angelruiz.cursoandroid.InterfazAPI_REST.IOnClickRecyclerInstagram;
import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterInstagramApiRest extends RecyclerView.Adapter<AdapterInstagramApiRest.ViewHolderInstagram> {
    private Context context;
    //save the data obtain form api instagram whit(DeserializeArrayResponseInstagram)
    private ArrayList<ArrayInstagramObjects> dataInstagram;
    //we create a object of the interface for pass him tha position of the item rv
    private IOnClickRecyclerInstagram listener;

    public AdapterInstagramApiRest(Context context, ArrayList<ArrayInstagramObjects> dataInstagram) {
        this.context = context;
        this.dataInstagram = dataInstagram;
    }

    //method for implement the onClickListener in rv the FragmentInstagramApiRest
    public void setOnClickLiatenerItem(IOnClickRecyclerInstagram listener){
        this.listener = listener;
    }

    //we fill the array
    public void passData(ArrayList<ArrayInstagramObjects> coments) {
        dataInstagram.addAll(coments);

        //join the information of the arrays
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderInstagram onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_inflate_instagram_rv, parent, false);
        return new ViewHolderInstagram(view, listener); //we also return the listener
    }

    public class ViewHolderInstagram extends RecyclerView.ViewHolder {
        ImageView ivImageInstagram;
        TextView tvLikeInstagram;

        public ViewHolderInstagram(@NonNull View itemView, final IOnClickRecyclerInstagram listener1) {
            super(itemView);
            ivImageInstagram = itemView.findViewById(R.id.ivImageInstagram);
            tvLikeInstagram = itemView.findViewById(R.id.tvLikeInstagram);

            //we obtain the position the item rv through click on the image
            ivImageInstagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener1.onClickItemRvIntagram(position); //we pass the position to the interface
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstagram holder, int position) {

       //we obtain the position of array(pojo)
       final ArrayInstagramObjects arrayInstagramObjects = dataInstagram.get(position);
       Picasso.with(context)
               .load(arrayInstagramObjects.getImageUrlUser()) //dataInstagram.get(position).getImageUrlUser() - together - we show the image from link inastagram from pojo whit method get
               .placeholder(R.drawable.ic_no_image) //image background if the picture is not shown
               .into(holder.ivImageInstagram);

       //we show the likes as string
       holder.tvLikeInstagram.setText(String.valueOf(dataInstagram.get(position).getImageLikes()));
    }

    @Override
    public int getItemCount() {
        return dataInstagram.size();
    }
}

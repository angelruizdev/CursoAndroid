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
import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterInstagramApiRest extends RecyclerView.Adapter<AdapterInstagramApiRest.ViewHolderInstagram> {
    private Context context;
    private ArrayList<ArrayInstagramObjects> dataInstagram;

    public AdapterInstagramApiRest(Context context, ArrayList<ArrayInstagramObjects> dataInstagram) {
        this.context = context;
        this.dataInstagram = dataInstagram;
    }

    public void passData(ArrayList<ArrayInstagramObjects> coments) {
        dataInstagram.addAll(coments);
        notifyDataSetChanged();
    }

    public class ViewHolderInstagram extends RecyclerView.ViewHolder {
        ImageView ivImageInstagram;
        TextView tvLikeInstagram;

        public ViewHolderInstagram(@NonNull View itemView) {
            super(itemView);
            ivImageInstagram = itemView.findViewById(R.id.ivImageInstagram);
            tvLikeInstagram = itemView.findViewById(R.id.tvLikeInstagram);
        }
    }

    @NonNull
    @Override
    public ViewHolderInstagram onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_inflate_instagram_rv, parent, false);
        return new ViewHolderInstagram(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInstagram holder, int position) {
       final ArrayInstagramObjects arrayInstagramObjects = dataInstagram.get(position); // we obtain the position of array
       Picasso.with(context)
               .load(arrayInstagramObjects.getImageUrlUser()) //dataInstagram.get(position).getImageUrlUser() - together - we show the image from link inastagram
               .placeholder(R.drawable.ic_no_image)
               .into(holder.ivImageInstagram);

       holder.tvLikeInstagram.setText(String.valueOf(dataInstagram.get(position).getImageLikes())); //we show the likes as string
    }

    @Override
    public int getItemCount() {
        return dataInstagram.size();
    }

}

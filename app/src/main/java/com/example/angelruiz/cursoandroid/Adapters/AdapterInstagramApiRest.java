package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramApiRest;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class AdapterInstagramApiRest extends RecyclerView.Adapter<AdapterInstagramApiRest.ViewHolderInstagram> {
    private Context context;
    private ArrayList<ArrayInstagramApiRest> dataInstagram;

    public AdapterInstagramApiRest(Context context, ArrayList<ArrayInstagramApiRest> dataInstagram) {
        this.context = context;
        this.dataInstagram = dataInstagram;
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
       holder.ivImageInstagram.setImageResource(dataInstagram.get(position).getImageUser());
       holder.tvLikeInstagram.setText(String.valueOf(dataInstagram.get(position).getImageLikes()));
    }

    @Override
    public int getItemCount() {
        return dataInstagram.size();
    }

}

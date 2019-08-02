package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class AdapterCaroucelImgRest extends RecyclerView.Adapter<AdapterCaroucelImgRest.ViewHolderCaroucel> {
    private Context context;
    private ArrayList<ArrayImgCaroucelRest> imagesCaroucel;

    public AdapterCaroucelImgRest(Context context, ArrayList<ArrayImgCaroucelRest> imagesCaroucel) {
        this.context = context;
        this.imagesCaroucel = imagesCaroucel;
    }

    public class ViewHolderCaroucel extends RecyclerView.ViewHolder {
        ImageView ivCaroucel;

        public ViewHolderCaroucel(@NonNull View itemView) {
            super(itemView);
            ivCaroucel = itemView.findViewById(R.id.ivCaroucel);
        }
    }
    @NonNull
    @Override
    public ViewHolderCaroucel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_cmp_inflate_caroucel, viewGroup, false);
        return new ViewHolderCaroucel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCaroucel viewHolderCaroucel, int i) {
        viewHolderCaroucel.ivCaroucel.setImageResource(imagesCaroucel.get(i).getNumberImage());

        Glide.with(context)
             .load("http://pokeapi.co/media/sprites/pokemon/" + imagesCaroucel.get(i).getName() + ".png")
             .into(viewHolderCaroucel.ivCaroucel);
    }

    @Override
    public int getItemCount() {
        return imagesCaroucel.size();
    }
}

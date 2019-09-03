package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCaroucelImgRest extends RecyclerView.Adapter<AdapterCaroucelImgRest.ViewHolderCaroucel> {
    private Context context;
    private ArrayList<ArrayImgCaroucelRest> imagesCaroucel;

    public AdapterCaroucelImgRest(Context context) {
        this.context = context;
        this.imagesCaroucel = new ArrayList<>();
    }

    public void listImagesCaroucel(ArrayList<ArrayImgCaroucelRest> names) {
        imagesCaroucel.addAll(names);
        notifyDataSetChanged();
    }

    public class ViewHolderCaroucel extends RecyclerView.ViewHolder {
        ImageView ivCaroucel;
        TextView tvNameCaroucel;

        public ViewHolderCaroucel(@NonNull View itemView) {
            super(itemView);
            ivCaroucel = itemView.findViewById(R.id.ivCaroucel);
            tvNameCaroucel = itemView.findViewById(R.id.tvNameCaroucel);
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
        ArrayImgCaroucelRest x = imagesCaroucel.get(i);

        Picasso.with(context)
             .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + x.getNumberImage() + ".png")
             .into(viewHolderCaroucel.ivCaroucel);

        viewHolderCaroucel.tvNameCaroucel.setText(x.getName());
        //viewHolderCaroucel.ivCaroucel.setImageResource(x.getNumberImage());
    }

    @Override
    public int getItemCount() {
        return imagesCaroucel.size();
    }
}

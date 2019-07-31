package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.Components.CmpCarouselImage;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;

public class FragmentPendiente extends Fragment implements View.OnClickListener{
    View vista;
    Context context;
    ImageView ivImgGlade;
    Button btCargarImg;
    CmpCarouselImage cmpCarouselImage;
    ArrayList<Integer> imagesCaroucel;
    FloatingActionButton fabPrevius, fabNext;

    public FragmentPendiente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pendiente, container, false);
        context = getContext();
        cmpCarouselImage = vista.findViewById(R.id.ivCaroucelCmp);

        imagesCaroucel = new ArrayList<>();
        imagesCaroucel.add(R.drawable.phone);
        imagesCaroucel.add(R.drawable.email);
        imagesCaroucel.add(R.drawable.ic_touch_app);
        imagesCaroucel.add(R.drawable.face);
        cmpCarouselImage.carrucelAnimation(imagesCaroucel);
        cmpCarouselImage.setCurrentCaroucel(0);

        fabPrevius = vista.findViewById(R.id.fabPrevius);
        fabPrevius.setOnClickListener(this);
        fabNext = vista.findViewById(R.id.fabNext);
        fabNext.setOnClickListener(this);
        ivImgGlade = vista.findViewById(R.id.ivImgGlade);
        btCargarImg = vista.findViewById(R.id.btCargarImg);
        btCargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://js2.pngtree.com/v3/images/home/paradrop.png";
                Glide.with(context).load(url).into(ivImgGlade);
            }
        });
        return vista;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
         case R.id.fabPrevius:
             cmpCarouselImage.touchRight(imagesCaroucel);
         break;
         case R.id.fabNext:
             cmpCarouselImage.touchLeft(imagesCaroucel);
             break;
       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

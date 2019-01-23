package com.example.angelruiz.cursoandroid.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.R;
public class FragmentPendiente extends Fragment {
View vista;
Context context;
ImageView ivImgGlade;
Button btCargarImg;
    public FragmentPendiente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pendiente, container, false);
        context = getContext();
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
}

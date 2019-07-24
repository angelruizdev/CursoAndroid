package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.Components.CmpPointIndicatorIntruction;
import com.example.angelruiz.cursoandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentPendiente extends Fragment {
    View vista;
    Context context;
    ImageView ivImgGlade;
    Button btCargarImg;
    CmpPointIndicatorIntruction cmpPointIndicatorIntruction;

    public FragmentPendiente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pendiente, container, false);
        context = getContext();
        cmpPointIndicatorIntruction = vista.findViewById(R.id.llcontentPointsIntruction1); //--->
        cmpPointIndicatorIntruction.mensaje();
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

    private void carrucel() {
        int timerSeconds = 1000;
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "timer", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, timerSeconds);
    }

}

package com.example.angelruiz.cursoandroid.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

public class FragmentDetailInstagramApiRest extends Fragment {
    View view;
    Context context;
    ImageView ivImageDetail;
    TextView tvLikesDetail;
    String imageUrlUser, imageLikes;

    public FragmentDetailInstagramApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //recibimos datos desde fmt instagram
        super.onCreate(savedInstanceState);
        context = getContext();

        Bundle receiveData = getArguments();
        if (receiveData != null){
            imageUrlUser = getArguments().getString("imageUrlUser");
            imageLikes = getArguments().getString("imageLikes");

            Log.i("arguments", "successful argument: " + imageLikes); //log.i information
        }else {
            Log.e("arguments", "without arguments");            //log.e error
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_instagram_api_rest, container, false);
        ivImageDetail = view.findViewById(R.id.ivImageDetail);
        Picasso.with(context) //we show the image whit pacasso too
                .load(imageUrlUser) //we pass the image obtain from FragmentInstagramApiRest
                .placeholder(R.drawable.ic_no_image)
                .into(ivImageDetail);

        tvLikesDetail = view.findViewById(R.id.tvLikesDetail);
        tvLikesDetail.setText(imageLikes + " Likes");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

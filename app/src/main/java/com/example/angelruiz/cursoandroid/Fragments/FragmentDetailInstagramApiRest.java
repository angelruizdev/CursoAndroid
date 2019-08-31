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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.angelruiz.cursoandroid.Arrays.ArrayInstagramObjects;
import com.example.angelruiz.cursoandroid.R;
import com.squareup.picasso.Picasso;

public class FragmentDetailInstagramApiRest extends Fragment {
    View view;
    Context context;
    ArrayInstagramObjects dataUserInstagram; //this array save the data obtain of the key, parceable
    ImageView ivImageDetail;
    TextView tvLikesDetail;
    String imageUrlUser, imageLikes, nameUsuarioLocal;

    public FragmentDetailInstagramApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        //we receive data from fmt instagram with Parceable
        Bundle receiveData = this.getArguments();

        if (receiveData != null){
            nameUsuarioLocal = getArguments().getString("nameUsuarioLocal");     //we obtain variable local without pojo neither parceable

            dataUserInstagram = receiveData.getParcelable("dataUserInstagram"); //we obtain and save the key with the data with parceable
            if(dataUserInstagram != null) {
                imageUrlUser = dataUserInstagram.getImageUrlUser();                //we save and extract the data to the array with parceable
                imageLikes = String.valueOf(dataUserInstagram.getImageLikes());
            }else {
                Toast.makeText(context, "Sin informacíon.", Toast.LENGTH_SHORT).show();
            }

            Log.i("arguments", "successful argument: " + imageLikes); //log.i information
        }else {
            Log.e("arguments", "without arguments");                 //log.e error
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_instagram_api_rest, container, false);

        ivImageDetail = view.findViewById(R.id.ivImageDetail);
        tvLikesDetail = view.findViewById(R.id.tvLikesDetail);

        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Picasso.with(context) //we show the image whit pacasso too
                .load(imageUrlUser) //we pass the image obtain from FragmentInstagramApiRest
                .placeholder(R.drawable.ic_no_image)
                .into(ivImageDetail);

        tvLikesDetail.setText(imageLikes + " Likes");

        Toast.makeText(context, "" + nameUsuarioLocal, Toast.LENGTH_SHORT).show(); //we show local variable
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

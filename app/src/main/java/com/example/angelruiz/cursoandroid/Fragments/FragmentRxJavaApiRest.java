package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angelruiz.cursoandroid.R;

public class FragmentRxJavaApiRest extends Fragment {

    public View view;
    private Context context;
    private RecyclerView rvShowPostsComments;

    public FragmentRxJavaApiRest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view =  inflater.inflate(R.layout.fragment_rx_java_api_rest, container, false);

    rvShowPostsComments = view.findViewById(R.id.rv_show_posts_comments);
    return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //tvNumComents.setVisibility(View.GONE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

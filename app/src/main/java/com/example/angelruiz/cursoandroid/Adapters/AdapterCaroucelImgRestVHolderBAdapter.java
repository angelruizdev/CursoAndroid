package com.example.angelruiz.cursoandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;


public class AdapterCaroucelImgRestVHolderBAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArrayImgCaroucelRest> imagesCaroucel;
    private LayoutInflater layoutInflater;

    public AdapterCaroucelImgRestVHolderBAdapter(Context context, ArrayList<ArrayImgCaroucelRest> imagesCaroucel) {
        this.context = context;
        this.imagesCaroucel = imagesCaroucel;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesCaroucel.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class  Holder{
        ImageView ivCaroucel;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        convertView = layoutInflater.inflate(R.layout.view_cmp_inflate_caroucel, parent);
        holder.ivCaroucel = convertView.findViewById(R.id.ivCaroucel);
        //holder.ivCaroucel.setImageResource(imagesCaroucel.get(position).getNumberImage());
        convertView.setTag(holder);
        parent.addView(convertView);
        return convertView;
    }
}

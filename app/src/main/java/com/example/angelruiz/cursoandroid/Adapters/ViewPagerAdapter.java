package com.example.angelruiz.cursoandroid.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

//extendemos de FragmentPagerAdapter e implementamos su constructor y sus metodos,F getItem y getCount
public class ViewPagerAdapter extends FragmentPagerAdapter {
private ArrayList<Fragment> fragments;
    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);//retornamos la posision del fragment
    }

    @Override
    public int getCount() {
        return fragments.size();//retornamos el tamaño del arreglo, en este caso es un arreglo de fragment
    }
}

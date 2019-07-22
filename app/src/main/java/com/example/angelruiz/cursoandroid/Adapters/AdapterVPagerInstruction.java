package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.Arrays.ArrayVPagerInstruction;
import com.example.angelruiz.cursoandroid.R;

import java.util.List;

public class AdapterVPagerInstruction extends PagerAdapter {

    private List<ArrayVPagerInstruction> information;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterVPagerInstruction(List<ArrayVPagerInstruction> information, Context context) {
        this.information = information;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return information.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;//false default viene
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { //codigo basado en listView e indu youtube o un fragment
        View view = layoutInflater.inflate(R.layout.view_inflate_instruction, container, false);

        TextView tvTitleInstruction = view.findViewById(R.id.tvTitleInstruction);
        ImageView ivImageInstruction = view.findViewById(R.id.ivImageInstruction);
        TextView tvDescriptionInstruction = view.findViewById(R.id.tvDescriptionInstruction);
        ConstraintLayout clviewInflateInstruction = view.findViewById(R.id.clviewInflateInstruction);

        tvTitleInstruction.setText(information.get(position).getTitle());
        ivImageInstruction.setImageResource(information.get(position).getImage());
        tvDescriptionInstruction.setText(information.get(position).getDescription());
        clviewInflateInstruction.setBackgroundColor(information.get(position).getColorBackground());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        View view = (View)object;
        container.removeView(view);
    }
}

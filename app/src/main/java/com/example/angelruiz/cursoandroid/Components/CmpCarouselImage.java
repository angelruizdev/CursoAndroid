package com.example.angelruiz.cursoandroid.Components;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.angelruiz.cursoandroid.Arrays.ArrayImgCaroucelRest;
import com.example.angelruiz.cursoandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CmpCarouselImage extends FrameLayout {
    Context context;
    View view;
    ImageView ivCaroucel;
    FloatingActionButton fabPreviusImage, fabNextImage;
    TextView tvNameCaroucel;
    Handler handler;
    int count, count1, index, countTest;
    int position;
    int timerSeconds;
    ArrayList<ArrayImgCaroucelRest> imagesCaroucel;
    ArrayImgCaroucelRest arrayImgCaroucelRest;

    public CmpCarouselImage(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CmpCarouselImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init() {
        view = inflate(context, R.layout.view_cmp_inflate_caroucel, null);
        this.ivCaroucel = view.findViewById(R.id.ivCaroucel);
        this.fabPreviusImage = view.findViewById(R.id.fabPreviusImage);
        this.fabNextImage = view.findViewById(R.id.fabNextImage);
        this.tvNameCaroucel = view.findViewById(R.id.tvNameCaroucel);

        view.setOnTouchListener(new OnSwipeTouchListener(context));
        imagesCaroucel = new ArrayList<>();
        handler = new Handler();
        position = 0;
        count = 0;
        count1 = 5;
        index = 0;
        countTest = 0;
        timerSeconds = 4000;

        this.addView(view);
    }

    public void left(){
       countTest++;
       if (countTest > 5){
           countTest = 0;
       }
    }

    public void right(){
       if (countTest > 0){
           countTest--;
       }else {
           countTest = 5;
       }
    }
    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        private OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }



        public void onSwipeLeft() { //finish slide automatic
            //touchLeft(imagesCaroucel);
            left();
            Toast.makeText(context, "left" + countTest, Toast.LENGTH_SHORT).show();
        }

        public void onSwipeRight() {
            //touchRight(imagesCaroucel);
            right();
            Toast.makeText(context, "right" + countTest, Toast.LENGTH_SHORT).show();
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();

                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

    public void carrucelAnimation(final ArrayList<ArrayImgCaroucelRest> imagesCaroucel) {

         handler.postDelayed(new Runnable() {
             @Override
             public void run() {

                 count++;
                 if (count >= imagesCaroucel.size()){
                     count = 0;
                 }
                 animation(count, imagesCaroucel);
                 handler.removeCallbacksAndMessages(null);

                 /*if(count < imagesCaroucel.size()) {
                     position = count++;

                     animation(position, imagesCaroucel);
                     handler.removeCallbacksAndMessages(null);

                 }else{
                     count = 0;
                 }*/

                 handler.postDelayed(this, timerSeconds);
             }
         }, 0);
    }

    public void animation(int position, final ArrayList<ArrayImgCaroucelRest> imagesCaroucel) {

        arrayImgCaroucelRest = imagesCaroucel.get(position);
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + arrayImgCaroucelRest.getNumberImage() + ".png")
                .into(ivCaroucel);

        tvNameCaroucel.setText(arrayImgCaroucelRest.getName());
        currentCaroucelImage(position);
    }

    public void currentCaroucelImage(int current){
        Toast.makeText(context, "current" + current,  Toast.LENGTH_SHORT).show(); //pos actual de la img
    }

    public void touchLeft(final ArrayList<ArrayImgCaroucelRest> imagesCaroucel) {
        //cancelAnimation(); permite que no se adelante el timpo si avanzamos una imagen
        carrucelAnimation(imagesCaroucel);
    }

    public void touchRight(final ArrayList<ArrayImgCaroucelRest> imagesCaroucel) { //do manual previus
        carrucelAnimation(imagesCaroucel);
    }

    public void cancelAnimation(){
        if(this.handler != null){
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }
}

/*Random random = new Random();
final int randomInteger = random.nextInt(3);
ivCaroucel.setImageResource(imagesCaroucel[randomInteger]);random de imagenes*/

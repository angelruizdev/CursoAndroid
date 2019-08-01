package com.example.angelruiz.cursoandroid.Components;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.angelruiz.cursoandroid.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CmpCarouselImage extends FrameLayout {
    Context context;
    View view;
    ImageView ivCaroucel;
    Handler handler;
    int current, index;
    Timer timer;
    int timerSeconds;
    ArrayList<Integer> imagesCaroucel;

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
        view.setOnTouchListener(new OnSwipeTouchListener(context));
        imagesCaroucel = new ArrayList<>();
        handler = new Handler();
        current = 0;
        index = 0;
        timerSeconds = 2000;
        timer = new Timer();
        this.addView(view);
    }

    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        private OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
            touchLeft(imagesCaroucel);
        }

        public void onSwipeRight() {
            touchRight(imagesCaroucel);
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

    public void carrucelAnimation(final ArrayList<Integer> imagesCaroucel) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        animation(current++, imagesCaroucel);
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, timerSeconds);
    }

    public void animation(int crnt, final ArrayList<Integer> imagesCaroucel) {
        if (current <= imagesCaroucel.size()) {
            ivCaroucel.setImageResource(imagesCaroucel.get(current -1));
        } else {
            current = 0;
       }
    }

    public void cancelAnimation(){
        if(this.handler != null){
            this.timer.cancel();
            this.handler = null;
        }
    }

    public void setCurrentCaroucel(int index) { // continuar checar logica
    }

    public void touchLeft(ArrayList<Integer> imagesCaroucel) {
        animation(current++, imagesCaroucel);
    }

    public void touchRight(ArrayList<Integer> imagesCaroucel) {
        animation(current--, imagesCaroucel);
    }
}

/*Random random = new Random();
final int randomInteger = random.nextInt(3);
ivCaroucel.setImageResource(imagesCaroucel[randomInteger]);random de imagenes*/

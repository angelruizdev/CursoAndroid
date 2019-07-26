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
    ImageView ivCaroucel;
    Handler handler;
    int current;
    Timer timer;
    int timerSeconds;
    ArrayList<Integer> imagesCaroucel;

    public CmpCarouselImage(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CmpCarouselImage(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void init(){
        View view = inflate(context, R.layout.view_cmp_inflate_caroucel, null);
         this.ivCaroucel = view.findViewById(R.id.ivCaroucel);
         view.setOnTouchListener(new OnSwipeTouchListener(context));
         imagesCaroucel = new ArrayList<>();
         handler = new Handler();
         current = 0;
         timerSeconds = 1000;
         timer = new Timer();
        this.addView(view);
    }

    public class OnSwipeTouchListener implements OnTouchListener {

        private final GestureDetector gestureDetector;

        private OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
          touchLeft();
        }

        public void onSwipeRight() {
          touchRight();
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
                        current++;
                        if(current < 4){
                           ivCaroucel.setImageResource(imagesCaroucel.get(current-1));
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, timerSeconds);
    }

    public void setCurrentCaroucel(int index){ // continuar checar logica
        if (index < 0){
           current = imagesCaroucel.size() -1;
        }else if (index > imagesCaroucel.size() -1){
           current = 0;
        }else {
            current = index;
        }
    }

    //HACER EL MOVIMIENTO DE IMG LUEGO LA ANIMACION

    public void touchLeft(){
      setCurrentCaroucel(current + 1);
    }
    public void touchRight(){
        setCurrentCaroucel(current - 1);
    }
}

/*Random random = new Random();
final int randomInteger = random.nextInt(3);
ivCaroucel.setImageResource(imagesCaroucel[randomInteger]);random de imagenes*/

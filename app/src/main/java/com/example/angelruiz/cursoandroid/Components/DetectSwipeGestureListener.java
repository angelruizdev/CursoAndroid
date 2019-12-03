package com.example.angelruiz.cursoandroid.Components;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static int MIN_SWIPE_DISTANCE_X = 100;
    private static int MIN_SWIPE_DISTANCE_Y = 100;

    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float deltaX = e1.getX() - e1.getY();
        float deltaY = e1.getY() - e1.getY();

        float deltaXabs = Math.abs(deltaX);
        float deltaYabs = Math.abs(deltaY);

        //detect left and right swipes
        if (deltaXabs >= MIN_SWIPE_DISTANCE_X && deltaXabs <= MAX_SWIPE_DISTANCE_X){

            if (deltaX > 0){
                //cmpCarouselImage.showMessageGesture("swipe to left");
            }else {
                //cmpCarouselImage.showMessageGesture("swipe to right");
            }
        }

        //detect up and down swipes
        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y){

            if (deltaY > 0){
                //cmpCarouselImage.showMessageGesture("swipe to up");
            }else {
                //cmpCarouselImage.showMessageGesture("swipe to down");
            }
        }
    return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        //cmpCarouselImage.showMessageGesture("single tap");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //cmpCarouselImage.showMessageGesture("double tap");
        return true;
    }
}

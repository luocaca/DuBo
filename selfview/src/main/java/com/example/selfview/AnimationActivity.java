package com.example.selfview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.selfview.views.AnimatorView;

/**
 * 属性动画练习界面
 */

public class AnimationActivity extends AppCompatActivity {

    private AnimatorView animation_view;

    private static final String TAG = "AnimationActivity";
    private float dx;
    private float dy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_animation);

        animation_view = findViewById(R.id.animation_view);


//        animation_view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
////                    animation_view.setX(event.getRawX() - 315 / 2);
////                    animation_view.setY(event.getRawY() - 315 / 2);
////                    src = animation_view.getDrawable();
////                    animation_view.setImageDrawable(tintDrawable(src, ColorStateList.valueOf(getRandomColor())));
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    dx = event.getX();
//                    dy = event.getY();
//
//                    Log.i(TAG, "ACTION_DOWN:  " + dx + "  " + dy + "  " + animation_view.getX() + "  " + animation_view.getY());
//
//                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    animation_view.setX(event.getRawX() - dx);
//                    animation_view.setY(event.getRawY() - dy - getStatusBarHeight());
////                    animation_view.setY(event.getRawY() - dy - getTitleBarHeight() - getStatusBarHeight());
////                    animation_view.setY(event.getRawY() - animation_view.getY());
//                } else {
////                    animation_view.setX(event.getRawX() - animation_view.getX());
////                    animation_view.setY(event.getRawY() - animation_view.getY());
//                }
//
//
//                Log.i(TAG, "after: \n .getRawX()=" + event.getRawX() + "  .getRawY()" + event.getRawY() + " .getX()= " + animation_view.getX() + " .getY() " + animation_view.getY());
////                Log.i(TAG, "animation_view location" + animation_view.getLeft() + " " + animation_view.getTop() + "  " + animation_view.getRight() + "  " + animation_view.getBottom());
//
//                return true;
//
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, " location  \n" + animation_view.getLeft() + " " + animation_view.getTop() + "  " + animation_view.getRight() + "  " + animation_view.getBottom() + "  width = " + animation_view.getWidth() + "  height = " + animation_view.getHeight());
            }
        }, 1000);


    }


    public static void start(Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, AnimationActivity.class));
    }

    public int getTitleBarHeight() {
        Window window = getWindow();
        int contentViewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();//statusBarHeight是上面所求的状态栏的高度inttitleBarHeight=contentViewTop-getStatusBarHeight();returntitleBarHeight;}}
        return contentViewTop;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


//    {
//        @BindView(R.id.btn)Buttonbtn ;
//        floatdx, dy;
//        @OverrideprotectedvoidonCreate(BundlesavedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_coordinatelayout);
//        ButterKnife.bind(this);
//        btn.setOnTouchListener(newButton.OnTouchListener() {
//            @OverridepublicbooleanonTouch(Viewview, MotionEventmotionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    dx = motionEvent.getX();
//                    dy = motionEvent.getY();
//                }
//                elseif(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//                    view.setX(motionEvent.getRawX() - dx);
//                    view.setY(motionEvent.getRawY() - dy - getStatusBarHeight() - getTitleBarHeight());
//                }
//                returntrue;
//            }
//        });
}

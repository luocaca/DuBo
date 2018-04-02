package com.example.selfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 属性动画    自定义view
 * <p>
 * <p>
 * 属性动画的原理以及使用
 */

public class AnimatorView extends View {


    /**
     * Animation （动画）
     * -- View Animation    View Animation 是纯粹基于 framework 的绘制转变，比较简单   (基本不用了)
     * -- Property Animation   Property Animation，属性动画，这是在 Android 3.0 开始引入的新的动画形式  ( 99% 都是用的它)
     * <p>
     * <p>
     * Transition  （转换 -  转场动画   切换界面时的 动画效果）  不是本次介绍的   重点
     */

    private static final String TAG = "AnimatorView";
    private float dx;
    private float dy;
    private float downX = -1;
    private float downY = -1;

    private Paint whitePaint;

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initAttrs(context, attrs);
        initPaints();
    }

    private void initPaints() {
        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setStrokeWidth(5);
        whitePaint.setTextSize(30);


    }

    @Override
    public boolean performClick() {
        return super.performClick();


    }

    private void initAttrs(Context context, AttributeSet attrs) {

        Log.i(TAG, "initAttrs: ");

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure: ");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");


        if (downX != -1) {
            canvas.drawCircle(dx, dy, 9, whitePaint);

            String pos = String.format("(%.2f , %.2f)", dx, dy);


            Rect rect = new Rect();
            whitePaint.getTextBounds(pos, 0, pos.length(), rect);
            canvas.drawText(pos, 0, pos.length(), dx  + 10 , dy - rect.height(), whitePaint);

            /* 画一条   距离左边距  的  线 */


            canvas.drawLine(0,
                    dy,
                    dx,
                    dy,
                    whitePaint
            );
            String getX = " getX() =" + getX();
            canvas.drawText(getX,
                    0,
                    getX.length(),
                    dx / 4,
                    dy + 30,
                    whitePaint);


            canvas.drawLine(dx,
                    0,
                    dx,
                    dy,
                    whitePaint
            );
            String getY = "getY() =" + getY();
            canvas.drawText(getY,
                    0,
                    getY.length(),
                    dx + 10,
                    dy / 2,
                    whitePaint);


        }


    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Log.i(TAG, "draw: ");


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: ");

        return super.dispatchTouchEvent(event);
    }


    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        Log.i(TAG, "onDrawForeground: ");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

//                    this.setX(event.getRawX() - 315 / 2);
//                    this.setY(event.getRawY() - 315 / 2);
//                    src = this.getDrawable();
//                    this.setImageDrawable(tintDrawable(src, ColorStateList.valueOf(getRandomColor())));
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            dx = event.getX();
            dy = event.getY();
            downX = getX();
            downY = getY();
            Log.i(TAG, "ACTION_DOWN:  " + dx + "  " + dy + "  " + this.getX() + "  " + this.getY());
            invalidate();

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            this.setX(event.getRawX() - dx);
            this.setY(event.getRawY() - dy - getStatusBarHeight());


//                    this.setY(event.getRawY() - dy - getTitleBarHeight() - getStatusBarHeight());
//                    this.setY(event.getRawY() - this.getY());
        } else {
//                    this.setX(event.getRawX() - this.getX());
//                    this.setY(event.getRawY() - this.getY());

            if (Math.abs(downX - this.getX()) > 5 || Math.abs(downY - this.getY()) > 5) {
                Log.i(TAG, "onTouchEvent: 移动");
            } else {
                Log.i(TAG, "onTouchEvent: 点击");
                performClick();
            }


        }

        Log.i(TAG, "after: \n .getRawX()=" + event.getRawX() + "  .getRawY()" + event.getRawY() + " .getX()= " + this.getX() + " .getY() " + this.getY());
//                Log.i(TAG, "this location" + this.getLeft() + " " + this.getTop() + "  " + this.getRight() + "  " + this.getBottom());
        return true;


    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

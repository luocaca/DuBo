package com.example.selfview.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 自定义view   以后经常谢谢。锻炼  api
 * <p>
 * <p>
 * 1.自定义View
 * <p>
 * 首先我们要明白，为什么要自定义View？主要是Android系统内置的View无法实现我们的需求，
 * 我们需要针对我们的业务需求定制我们想要的View。自定义View我们大部分时候只需
 * 重写两个函数
 * ：onMeasure()、onDraw()。onMeasure负责对当前View的尺寸进行测量，
 * .onDraw负责把当前这个View绘制出来。当然了，你还得写至少写2个构造函数：
 */

public class MView extends View {

    private static final String TAG = "MView";
    private boolean isAnimating;

    public MView(Context context) {
        super(context);
    }

    public MView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * onMeasure (测量宽高尺寸)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("onMeasure", "onMeasure  start");
        // 宽高  设置  ---- >    wrap_content、match_parent以及指定固定尺寸
        //测量模式     ---- >    UNSPECIFIED，EXACTLY，AT_MOST

        // 谷歌使用 int 同时 (放测量模式 和尺寸信息) --->  内部是转换成  32 bit 的byte

        // int 32 bit  ，google 将前面 2 个 bit 用于区分不同的布局模式，后面的 30 bit 存放的是尺寸 数据

        //？？？那我们如何从   int 数据 取出测量模式  和尺寸呢
        //放心，   不用你每次都要写一次移位<<和取且&操作
        //Android内置类MeasureSpec帮我们写好啦~，我们只需按照下面方法就可以拿到啦：


//        int width = (int) ((getMySize(100, widthMeasureSpec)) + getMySize(100, widthMeasureSpec) * schal);
//        int height = (int)((getMySize(100, heightMeasureSpec)) + getMySize(100, widthMeasureSpec) * schal);


        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            /**
             *
             *    ----
             *    |   |
             *    |   |
             *    |----
             */
            height = width;
            /**
             *
             *     ----
             *    |    |
             *    |----|
             */


        } else {
            width = height;
        }


        Log.i(TAG, "onMeasure: width= " + (width + schal));
        Log.i(TAG, "onMeasure: height= " + (height + schal));
        setMeasuredDimension(width + schal, height + schal);

        /*宽度 测量 模式 */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        /*高度 测量 模式*/
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        Log.i("onMeasure", "onMeasure  end");

        /**
         * 爱思考的你肯定会问，既然我们能通过widthMeasureSpec拿到宽度尺寸大小，
         * 那我们还要测量模式干嘛？测量模式会不会是多余的？
         * 请注意：这里的的尺寸大小并不是最终我们的View的尺寸大小，而是父View提供的参考大小。我们看看测量模式，测量模式是干啥用的呢？

         测量模式	表示意思
         UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
         代表不变 --  EXACTLY（match_parent  （固定尺寸如100dp））	当前的尺寸就是当前View应该取的尺寸
         代表可变 -- AT_MOST（wrap_content）	当前尺寸是当前View能取的最大尺寸



         */


        /**
         * match_parent—>EXACTLY。怎么理解呢？match_parent就是要利用父View给我们提供的所有剩余空间，而父View剩余空间是确定的，也就是这个测量模式的整数里面存放的尺寸。
         wrap_content—>AT_MOST。怎么理解：就是我们想要将大小设置为包裹我们的view内容，那么尺寸大小就是父View给我们作为参考的尺寸，只要不超过这个尺寸就可以啦，具体尺寸就根据我们的需求去设定。
         固定尺寸（如100dp）—>EXACTLY。用户自己指定了尺寸大小，我们就不用再去干涉了，当然是以指定的大小为主啦。
         */

        // widthMeasureSpec  测量模式  （3种  ---  ）  heightMeasureSpec


        //参数中的widthMeasureSpec和heightMeasureSpec是个什么鬼


    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {

            case MeasureSpec.AT_MOST://（wrap_content） 如果测量 模式是最大  取值为size
                //我们将大小取最大值 ， 你也可以取其他值
                mySize = size;
                break;

            case MeasureSpec.EXACTLY://如果取固定大小，那就不要去改变她  match_parent 或 100dp
                mySize = size;
                break;

            case MeasureSpec.UNSPECIFIED: // 如果没有指定大小，就设置为 默认大小
                mySize = defaultSize;
                break;
        }

        return mySize;

    }

    public int schal = 0;

    public void setAlpha(int progress) {

        this.schal = progress * 10;

        requestLayout();
        invalidate();

    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: ");


    }


    /**
     * 1.3.重写onDraw
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop() + r;

        Paint paint = new Paint();
        paint.setAntiAlias(true); // 指定是否使用抗锯齿功能  如果使用会使绘图速度变慢 默认false
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//绘图样式  对于文字和几何图形都有效
        paint.setTextAlign(Paint.Align.CENTER);//设置文字对齐方式  取值： CENTER LEFT  RIGHT
        paint.setHinting(Paint.HINTING_ON);
        paint.setTextSize(12);// 文字大小
        paint.setColor(Color.GREEN);


        // 样式设置
        paint.setFakeBoldText(true);// 设置是否为粗体文字
        paint.setUnderlineText(true);//设置下划线
        paint.setTextSkewX((float) -0.25);//设置字体水平倾斜度  普通斜体字 是 -0.25
        paint.setStrikeThruText(true);//设置带有删除线效果



        //其他设置
        paint.setTextSkewX(2);//只会将水平方向拉伸  高度不会变


//        canvas.drawColor(Color.TRANSPARENT);
//        canvas.drawCircle(r, r, r, paint);           //绘制圆形


        paint.setStrokeWidth(5);


        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("我是一颗小小的石头", 100, 100, paint);

        paint.setStyle(Paint.Style.STROKE);

        canvas.drawText("我是一颗小小的石头", 100, 300, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("我是一颗小小的石头", 100, 500, paint);


        //开始绘制
//        canvas.drawCircle(centerX, centerY, r, paint);

//        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
//        //基本的而绘制功能，比如绘制背景颜色、背景图片等
//        super.onDraw(canvas);
//        Log.i(TAG, "onDraw: start");
//
//
//        int r = getMeasuredWidth() / 2;
//        //也可以 是 getMeasuredHeight  ， 本例子我们已经将高度设置相等
//
//        //圆心的横坐标 为当前的View的左边起始位置+半径
//        //getLeft()  距离左边的距离   r  宽度的一半
//        int centerX = getLeft() + r;
//
//        //圆心的纵坐标 为当前的View 的顶部起始位置 + 半径
//        int centerY = getTop() + r;
//
//        //新建画笔
//        Paint paint = new Paint();
//        paint.setColor(Color.DKGRAY);
//
//        //开始绘制
//        canvas.drawCircle(centerX, centerY, r, paint);
//        Log.i(TAG, "onDraw: end");

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(TAG, "onTouchEvent: x = " + event.getX() + "   event.action --- > " + event.getAction());
        Log.i(TAG, "onTouchEvent: Y = " + event.getY());
        Toast.makeText(getContext(), "别点我", Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);


//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            Toast.makeText(getContext(), "别点我", Toast.LENGTH_SHORT).show();
//            return true;
//        }
    }

    private void animateOpen(View view) {
        ValueAnimator animator = createDropAnimator(view, 0, 200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.start();
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {

        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


}

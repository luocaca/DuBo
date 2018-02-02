package com.example.selfview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.selfview.R;

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

public class MyView extends View {

    private static final String TAG = "MView";
    private boolean isAnimating;

    private int defaultSize;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 第二个参数就是我们在Styles.xml文件中的 <declare-styleable> 标签
        //即属性集合的标签，在R文件中名称为 R.styleable + name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        //第一个参数为属性集合里面的属性  ， R文件名称： R.styleable+属性集合名称 + 下划线 + 属性名称
        //第二个参数为，如果没有 设置这个属性， 则设置的默认的值
        defaultSize = a.getDimensionPixelSize(R.styleable.MyView_default_size, 100);


        a.recycle();


    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);







    }


    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getLeft() + r;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getTop() + r;

        Paint mPaint = new Paint();

        mPaint.setAntiAlias(false);                       //设置画笔为无锯齿
        mPaint.setColor(Color.GREEN);                    //设置画笔颜色
        mPaint.setStrokeWidth((float) 3.0);              //线宽
        mPaint.setStyle(Paint.Style.FILL);                   //空心效果
        canvas.drawColor(Color.TRANSPARENT);                  //白色背景


//        canvas.drawCircle(50, 50, 10, mPaint);           //绘制圆形
//        canvas.drawCircle(100, 100, 20, mPaint);         //绘制圆形
//        canvas.drawCircle(150, 150, 30, mPaint);         //绘制圆形
//        canvas.drawCircle(200, 200, 40, mPaint);         //绘制圆形
//        canvas.drawCircle(250, 250, 50, mPaint);         //绘制圆形
//        canvas.drawCircle(300, 300, 60, mPaint);         //绘制圆形
//        canvas.drawCircle(350, 350, 70, mPaint);         //绘制圆形

        //开始绘制
        canvas.drawCircle(r, r, r, mPaint);


    }


}

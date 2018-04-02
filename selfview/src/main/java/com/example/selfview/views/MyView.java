package com.example.selfview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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

    private Path mPath;
    private Point startPoint;
    private Point endPoint;
    // 辅助点
    private Point assistPoint;
    private Paint mPaint;
    //----绘制轨迹----
    private float mX;
    private float mY;
    private final Paint mGesturePaint = new Paint();


    //------检测点是否在path内
    private boolean isDraw=false;
    Region re=new Region();



    private void init(Context context) {
        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(300, 600);
        endPoint = new Point(900, 600);
        assistPoint = new Point(600, 900);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);


        mGesturePaint.setColor(context.getResources().getColor(android.R.color.holo_green_dark));
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(4.0f);

        this.setClickable(true);

    }


    private int defaultSize;


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
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
        canvas.drawPath(mPath, mGesturePaint);
//        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
//        //圆心的横坐标为当前的View的左边起始位置+半径
//        int centerX = getLeft() + r;
//        //圆心的纵坐标为当前的View的顶部起始位置+半径
//        int centerY = getTop() + r;
//
//        Paint mPaint = new Paint();
//
//        mPaint.setAntiAlias(false);                       //设置画笔为无锯齿
//        mPaint.setColor(Color.GREEN);                    //设置画笔颜色
//        mPaint.setStrokeWidth((float) 3.0);              //线宽
//        mPaint.setStyle(Paint.Style.FILL);                   //空心效果
//        canvas.drawColor(Color.TRANSPARENT);                  //白色背景
//
//
////        canvas.drawCircle(50, 50, 10, mPaint);           //绘制圆形
////        canvas.drawCircle(100, 100, 20, mPaint);         //绘制圆形
////        canvas.drawCircle(150, 150, 30, mPaint);         //绘制圆形
////        canvas.drawCircle(200, 200, 40, mPaint);         //绘制圆形
////        canvas.drawCircle(250, 250, 50, mPaint);         //绘制圆形
////        canvas.drawCircle(300, 300, 60, mPaint);         //绘制圆形
////        canvas.drawCircle(350, 350, 70, mPaint);         //绘制圆形
//
//        //开始绘制
//        canvas.drawCircle(r, r, r, mPaint);
//
//
//        mPaint.setColor(Color.BLACK);
//        // 笔宽
//        mPaint.setStrokeWidth(5);
//        // 空心
//        mPaint.setStyle(Paint.Style.STROKE);
//        // 重置路径
//        mPath.reset();
//        // 起点
//        mPath.moveTo(startPoint.x, startPoint.y);
//        // 重要的就是这句
//        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
//        // 画路径
//        canvas.drawPath(mPath, mPaint);
//        // 画辅助点
//        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);




    }

    public boolean onTouchEvent(MotionEvent event) {


        if(isDraw){
            //------关键部分 判断点是否在 一个闭合的path内--------//
            if(event.getAction()==MotionEvent.ACTION_DOWN){
                //构造一个区域对象，左闭右开的。
                RectF r=new RectF();
                //计算控制点的边界
                mPath.computeBounds(r, true);
                //设置区域路径和剪辑描述的区域
                re.setPath(mPath, new Region((int)r.left,(int)r.top,(int)r.right,(int)r.bottom));
                //在封闭的path内返回true 不在返回false
                Log.e("","--判断点是否则范围内----"+re.contains((int)event.getX(), (int)event.getY()));
            }
            return true;
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                mPath.close();
                isDraw=true;
                break;
        }
        //更新绘制
        invalidate();
        return true;



    }




    //---------------下边是划线部分----------------------------//
    //手指点下屏幕时调用
    private void touchDown(MotionEvent event)
    {
        //重置绘制路线，即隐藏之前绘制的轨迹
        mPath.reset();
        float x = event.getX();
        float y = event.getY();
        mX = x;
        mY = y;
        mPath.moveTo(x, y);
    }
    //手指在屏幕上滑动时调用
    private void touchMove(MotionEvent event)
    {
        final float x = event.getX();
        final float y = event.getY();
        final float previousX = mX;
        final float previousY = mY;
        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        assistPoint.x = (int) event.getX();
        assistPoint.y = (int) event.getY();
        Log.i(TAG, "assistPoint.x = " + assistPoint.x);
        Log.i(TAG, "assistPoint.Y = " + assistPoint.y);


        //两点之间的距离大于等于3时，连接连接两点形成直线
        if (dx >= 3 || dy >= 3)
        {
            //两点连成直线
            mPath.lineTo(x, y);
            //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
        }
    }







}

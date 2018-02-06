package com.example.selfview.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.selfview.R;

import java.util.ArrayList;
import java.util.List;

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

    private Paint paint;
    private String mFirstText;


    public void initAttr(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MView);

            //第一个参数为属性集合里面的属性  ， R文件名称： R.styleable+属性集合名称 + 下划线 + 属性名称
            //第二个参数为，如果没有 设置这个属性， 则设置的默认的值
            mFirstText = a.getString(R.styleable.MView_text);


            a.recycle();


        }


    }

    List<Point> pointSparseArray = new ArrayList<>();
    List<Point> mControlPoints = new ArrayList();
    List<Point> midPoints = new ArrayList();
    List<Point> mMidMidPoints = new ArrayList();

    RectF rect0;

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

        paint.setAntiAlias(true); // 指定是否使用抗锯齿功能  如果使用会使绘图速度变慢 默认false
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//绘图样式  对于文字和几何图形都有效
        paint.setTextAlign(Paint.Align.LEFT);//设置文字对齐方式  取值： CENTER LEFT  RIGHT
        paint.setHinting(Paint.HINTING_ON);
        paint.setTextSize(sp(18));// 文字大小
        paint.setColor(Color.GREEN);


        // 样式设置
        paint.setFakeBoldText(false);// 设置是否为粗体文字
        paint.setUnderlineText(false);//设置下划线
//        paint.setTextSkewX(0);//设置字体水平倾斜度  普通斜体字 是 -0.25
        paint.setStrikeThruText(false);//设置带有删除线效果


        //其他设置
//        paint.setTextSkewX(2);//只会将水平方向拉伸  高度不会变


//        canvas.drawColor(Color.TRANSPARENT);
//        canvas.drawCircle(r, r, r, paint);           //绘制圆形


        paint.setStrokeWidth(5);


        paint.setStyle(Paint.Style.FILL);
//        mFirstText = mFirstText + " text width =" + paint.measureText(mFirstText) + "  width =" + getMeasuredWidth();
        String str = mFirstText + "  width =" + getMeasuredWidth() + "height = " + getMeasuredHeight();
        canvas.drawText(str + paint.measureText(str), 0, spacing(1), paint);

        TextPaint textPaint = new TextPaint();
        textPaint.set(paint);
        textPaint.setColor(Color.YELLOW);
        StaticLayout layout = new StaticLayout("StaticLayouttestStaticLayouttestStaticLayouttest", textPaint, getMeasuredWidth() - getPaddingRight() - getPaddingLeft(), Layout.Alignment.ALIGN_NORMAL, 1.2F, 0.0F, true);
        canvas.translate(getPaddingLeft(), spacing(2));
        layout.draw(canvas);


        paint.setTextSize(sp(18));// 文字大小
        paint.setStyle(Paint.Style.FILL);

        canvas.drawText("是否文字宽度 > 控件宽度" + (paint.measureText(str + paint.measureText(str)) > getMeasuredWidth()), 0, spacing(2), paint);

        paint.setTextSize(sp(50));// 文字大小
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("文字宽度 - >" + getTextWidth(paint, str), 0, spacing(3), paint);


        //左下角绘制文字
        paint.setTextSize(sp(18));// 文字大小
        paint.setColor(Color.WHITE);
        canvas.drawText("左下角", 0, getMeasuredHeight() - spacing(5), paint);
        canvas.drawText("左下角", getMeasuredWidth() - getTextWidth(paint, "左下角") - getPaddingRight() - getPaddingLeft(), getMeasuredHeight() - spacing(5), paint);

        paint.setColor(Color.RED);
        canvas.drawLine(getTextWidth(paint, "左下角") + 10, getMeasuredHeight() - spacing(5) - getWH("左下角") / 2,
                getMeasuredWidth() - getTextWidth(paint, "左下角") - getPaddingRight() - getPaddingLeft() - 10,
                getMeasuredHeight() - spacing(5), paint
        );

        //右下角绘制文字


        //绘制  x y 轴
//        |
//        |
//        |_______
        //   |
        canvas.drawLine(0, 100,
                0,
                getMeasuredHeight() - spacing(3), paint
        );

        /*x轴的  Y 值*/
        float lineY = getMeasuredHeight() - spacing(3);
        float lineX = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        canvas.drawLine(0, lineY,
                lineX, lineY
                , paint
        );


        int space = 10;
        int width = 40;

        paint.setColor(Color.GREEN);


        pointSparseArray.clear();


        for (int i = 0; i < 15; i++) {

            if (i == 4) {
                paint.setColor(Color.RED);
                rect0 = new RectF(((int) (30 + (60 * i))), (int) lineY - 50 * 2, 30 + (60 * i) + 40, ((int) lineY));
                canvas.drawRect(rect0, paint);
                canvas.drawText(i + "", 30 + (60 * i) + 10, lineY + getWH("i") + 10, paint);
                pointSparseArray.add(i, new Point(30 + (60 * i) + 20, (int) (lineY - 50 * 2)));

//                pointSparseArray.add(i, new Point(30 + (60 * i) + 20, (int) (lineY - 50 * 2)));
                paint.setColor(Color.WHITE);
                canvas.drawCircle(30 + (60 * i) + 20, lineY - 50 * 2, 8, paint);
            } else if (i == 8) {
                paint.setColor(Color.YELLOW);
                canvas.drawRect(30 + (60 * i), lineY - 50 * 6, 30 + (60 * i) + 40, lineY, paint);
                canvas.drawText(i + "", 30 + (60 * i) + 10, lineY + getWH("i") + 10, paint);

                pointSparseArray.add(i, new Point(30 + (60 * i) + 20, (int) (lineY - 50 * 6)));
                paint.setColor(Color.WHITE);
                canvas.drawCircle(30 + (60 * i) + 20, lineY - 50 * 6, 8, paint);

            } else if (i == 0) {
                canvas.drawText(i + "", 30 + 10, lineY + getWH("i") + 10, paint);
                paint.setARGB(125, 200, 100, 100);
                canvas.drawRect(30, lineY - 50 * 6, 30 + 40, lineY, paint);
                pointSparseArray.add(i, new Point(20 + 30, (int) (lineY - 50 * 6)));
                paint.setColor(Color.WHITE);
                canvas.drawCircle(30 + 20, lineY - 50 * 6, 8, paint);
            } else {
                paint.setColor(Color.GREEN);
                canvas.drawRect(30 + (60 * i), lineY - 50 * i, 30 + (60 * i) + 40, lineY, paint);
                paint.setColor(Color.WHITE);
                canvas.drawText(i + "", 30 + (60 * i) + 10, lineY + getWH("i") + 10, paint);
                /**
                 * 画圆点
                 */
                pointSparseArray.add(i, new Point(30 + (60 * i) + 20, (int) (lineY - 50 * i)));
                paint.setColor(Color.WHITE);
                canvas.drawCircle(30 + (60 * i) + 20, lineY - 50 * i, 8, paint);
            }


        }

        initMidPoints(pointSparseArray);
        initMidMidPoints(midPoints);
        initControlPoints(pointSparseArray, midPoints, mMidMidPoints);
        Path path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        // 防抖动
        paint.setDither(true);

//        initMidPoints(pointSparseArray);
//        path.quadTo();
        for (int i = 0; i < pointSparseArray.size(); i++) {
            Point point = pointSparseArray.get(i);
            if (i == 0) { // 第一条为二阶贝塞尔
                path.moveTo(point.x, point.y);
//                paint.setColor(Color.TRANSPARENT);
//                path.quadTo(point.x, point.y, point.x, point.y);
                path.quadTo(mControlPoints.get(i).x, mControlPoints.get(i).y,// 控制点
                        pointSparseArray.get(i + 1).x, pointSparseArray.get(i + 1).y);
            } else if (i < pointSparseArray.size() - 2) {
                path.cubicTo(mControlPoints.get(2 * i - 1).x, mControlPoints.get(2 * i - 1).y,// 控制点
                        mControlPoints.get(2 * i).x, mControlPoints.get(2 * i).y,// 控制点
                        pointSparseArray.get(i + 1).x, pointSparseArray.get(i + 1).y);// 终点
            } else if (i == pointSparseArray.size() - 2) {// 最后一条为二阶贝塞尔
                path.moveTo(pointSparseArray.get(i).x, pointSparseArray.get(i).y);// 起点
                path.quadTo(mControlPoints.get(mControlPoints.size() - 1).x, mControlPoints.get(mControlPoints.size() - 1).y,
                        pointSparseArray.get(i + 1).x, pointSparseArray.get(i + 1).y);// 终点
            }
//            else if (i == 1) {
//                // + ( point.x - lastlastpoint.x)/2,
//                Point lastlastpoint = pointSparseArray.get(i - 1);//上上点
////                Point nextPoint = pointSparseArray.get(i + 1);
//                path.rQuadTo(lastlastpoint.x, lastlastpoint.y,
//                        point.x, point.y);
////                path.rQuadTo(lastlastpoint.x + (point.x - lastlastpoint.x) / 2,
////                        caculterY(getCenterPoint(lastlastpoint, point)
////                                , getCenterPoint(point, nextPoint), point),
////                        point.x, point.y);
//            } else if (i == 2) {
////                path.moveTo(point.x, point.y);
////                Point lastlastpoint = pointSparseArray.get(i - 2);//上上点
//                Point lastPoint = pointSparseArray.get(i - 1);//上点
//                path.quadTo(lastPoint.x , lastPoint.y , point.x, point.y);
////                path.quadTo(lastPoint.x + (point.x - lastPoint.x) / 2, lastPoint.y + (point.y - lastPoint.y) / 2, point.x, point.y);
////                path.cubicTo(lastlastpoint.x, lastlastpoint.y, lastPoint.x, lastPoint.y, point.x, point.y);
//            }
//            else {
//              paint.setColor(Color.WHITE);
//                Point lastPoint = pointSparseArray.get(i);
//                path.quadTo(lastPoint.x, lastPoint.y, point.x, point.y);
//              path.moveTo(point.x, point.y);
//                Point lastlastpoint = pointSparseArray.get(i - 2);//上上点
//                Point lastPoint = pointSparseArray.get(i - 1);//上点
//                path.quadTo( lastPoint.x + 20, lastPoint.y + 20, point.x, point.y);
//                path.quadTo(lastPoint.x, lastPoint.y, point.x, point.y);

//                path.rCubicTo(lastlastpoint.x, lastlastpoint.y, lastPoint.x, lastPoint.y, point.x, point.y);
//            }
        }


        canvas.drawPath(path, paint);

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

    private Point getCenterPoint(Point last, Point current) {
        Point centerPoint = new Point(last.x + (current.x - last.x) / 2, last.y + (current.y - last.y) / 2);
        return centerPoint;
    }

    private float caculterY(Point lastCenter, Point currentCenter, Point topPoint) {
        //根据 2 个点找到 他们的中点  在于 中点的  数轴顶部 比较 Y 的偏移量
        Point centerPoint = new Point(lastCenter.x + (currentCenter.x - lastCenter.x) / 2, lastCenter.y + (currentCenter.y - lastCenter.y) / 2);
        return topPoint.y - centerPoint.y;

    }

    public float spacing(int num) {
        return paint.getFontSpacing() * num;
    }

    private static final String TAG = "MView";
    private boolean isAnimating;

    public MView(Context context) {
        super(context);
        paint = new Paint();
        initAttr(context, null);

    }

    public MView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        initAttr(context, attrs);


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
        setMeasuredDimension(Math.min(width + schal, 1080), Math.min(width + schal, 1080));

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

    Region re = new Region();

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(TAG, "onTouchEvent: x = " + event.getX() + " y  =  " + event.getY());
//        Log.i(TAG, "onTouchEvent: Y = " + event.getY());
//        Toast.makeText(getContext(), "别点我", Toast.LENGTH_SHORT).show();
//        re.getBounds(rect0);

        //构造一个区域对象，左闭右开的。
        RectF r = new RectF();
        //计算控制点的边界
//        mPath.computeBounds(r, true);
        //设置区域路径和剪辑描述的区域
//        re.setPath(mPath, new Region((int)r.left,(int)r.top,(int)r.right,(int)r.bottom));
        //在封闭的path内返回true 不在返回false
        Log.e("", "--判断点是否则范围内----" + re.contains((int) event.getX(), (int) event.getY()));


        if (rect0.contains(((int) event.getX()), ((int) event.getY()))) {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

        }

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


    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param
     * @param dpValue
     * @return
     */
    public int dp(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public int sp(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public int getWH(String str) {
        //2. 计算文字所在矩形，可以得到宽高
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        int w = rect.width();
        int h = rect.height();
        Log.d(TAG, "w=" + w + "  h=" + h);
        return h;
    }


    /**
     * 初始化中点集合
     */
//    private void initMidPoints(List<Point> points) {
//        for (int i = 0; i < points.size(); i++) {
//            Point midPoint = null;
//            if (i == points.size() - 1) {
//                return;
//            } else {
//                midPoint = new Point((points.get(i).x + points.get(i + 1).x) / 2, (points.get(i).y + points.get(i + 1).y) / 2);
//            }
//            points.add(midPoint);
//        }
//    }
    private void initMidPoints(List<Point> points) {
        midPoints.clear();
        for (int i = 0; i < points.size(); i++) {
            Point midPoint = null;
            if (i == points.size() - 1) {
                return;
            } else {
                midPoint = new Point((points.get(i).x + points.get(i + 1).x) / 2, (points.get(i).y + points.get(i + 1).y) / 2);
            }
            midPoints.add(midPoint);
        }
    }


    /**
     * 初始化中点的中点集合
     */
    private void initMidMidPoints(List<Point> midPoints) {
        mMidMidPoints.clear();
        for (int i = 0; i < midPoints.size(); i++) {
            Point midMidPoint = null;
            if (i == midPoints.size() - 1) {
                return;
            } else {
                midMidPoint = new Point((midPoints.get(i).x + midPoints.get(i + 1).x) / 2, (midPoints.get(i).y + midPoints.get(i + 1).y) / 2);
            }
            mMidMidPoints.add(midMidPoint);
        }
    }


    private void initControlPoints(List<Point> points, List<Point> midPoints, List<Point> midMidPoints) {
        mControlPoints.clear();
        for (int i = 0; i < points.size(); i++) {
            if (i == 0 || i == points.size() - 1) {
                continue;
            } else {
                Point before = new Point();
                Point after = new Point();
                before.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i - 1).x;
                before.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i - 1).y;
                after.x = points.get(i).x - midMidPoints.get(i - 1).x + midPoints.get(i).x;
                after.y = points.get(i).y - midMidPoints.get(i - 1).y + midPoints.get(i).y;
                mControlPoints.add(before);
                mControlPoints.add(after);
            }
        }
    }

    /**
     * 测量文字  宽度
     *
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int w = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                w += (int) Math.ceil(widths[j]);
            }
        }
        return w;
    }


}

package com.example.selfview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 自定义surfaceView、  一个可以用线程进行界面刷新的view\  不占用主线程的 资源
 */

public class SurfaceViewL extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "SurfaceViewL";
    // 画布
    private Canvas mCanvas;
    // SurfaceHolder
    private SurfaceHolder mSurfaceHolder;

    /*红笔*/
    private Paint mRedPaint;
    /*路径 path*/
    private Path mPath;

    private float lastX, lastY;


    private Thread uiThread = new Thread(this);

    // 子线程标志位
    private boolean isDrawing = false;
//    private boolean isMoving = false;

    public SurfaceViewL(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*xml 布局引用  */
        init();


    }

    private void init() {
        //  private final SurfaceHolder mSurfaceHolder = new SurfaceHolder()
        mSurfaceHolder = getHolder();//得到SurfaceHolder 对象
        mSurfaceHolder.addCallback(this);//注册SurfaceHolder
        this.setFocusable(true);
        setFocusableInTouchMode(true);
//        this.setKeepScreenOn(true);//保持屏幕常亮

        mRedPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mRedPaint.setStrokeWidth(18f);
        mRedPaint = new Paint();
        mRedPaint.setColor(Color.RED);
        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeJoin(Paint.Join.ROUND);
        mRedPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        isDrawing = true;
        /*创建   开启子线程进行绘制  ， 在子线程中，使用一个 while（isDrawing） 循环来不停绘制
        *
        * 具体绘制过程，有 lockCanvas() 方法进行绘制，并通过 unlockCanvasAndPost(mCanvas)进行画布内容的提交
        * */

        Log.e("surfaceCreated", "--" + isDrawing);
        //绘制线程
//        new Thread(this).start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        /*改变*/
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        /*销毁*/
        isDrawing = false;
        Log.e("surfaceDestroyed", "--" + isDrawing);
//        isMoving = false;
    }

    @Override
    public void run() {
        /*线程执行*/

//        while (isDrawing) {
        drawing();
//            Log.i(TAG, "run: ");
//        }
    }

    /* 线程中绘制 */
    private void drawing() {

//        if (!isMoving) {
//            Log.i(TAG, "no moving");
//            return;
//        }
        Log.i(TAG, "drawing");
        try {
            /*锁定画布，进行绘制  --- -- -    */
            mCanvas = mSurfaceHolder.lockCanvas();
            Log.i(TAG, "lockCanvas");
            mCanvas.drawColor(Color.WHITE);//清屏
            mCanvas.drawPath(mPath, mRedPaint);
            // -------这里进行内容的绘制  --------
            // -------这里进行内容的绘制  --------


        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                Log.i(TAG, "unlockCanvasAndPost");
            }
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX = event.getX();
        float curY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            isDrawing = true;
            Log.i(TAG, "按下");
            lastX = curX;
            lastY = curY;
            mPath.moveTo(lastX, lastY);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            mPath.rLineTo(curX, curY);
//            curX = event.getX();
//            curY = event.getY();


            float dx = Math.abs(curX - lastX);
            float dy = Math.abs(curY - lastY);
            if (dx >= 3 || dy >= 3) {
                mPath.quadTo(lastX, lastY, (lastX + curX) / 2, (lastY + curY) / 2);
            }
            lastY = curY;
            lastX = curX;


        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.i(TAG, "抬起");
//            isMoving = false;
        }

        drawing();
        return true;


    }
}

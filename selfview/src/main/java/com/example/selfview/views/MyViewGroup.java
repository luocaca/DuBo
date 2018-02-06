package com.example.selfview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义 布局
 * ViewGroup
 * 自定义View的过程很简单，就那几步，可自定义ViewGroup可就没那么简单啦~，
 * <p>
 * 因为它不仅要管好自己的，还要兼顾它的子View。
 * 我们都知道ViewGroup是个View容器，
 * 它装纳child View并且负责把child View放入指定的位置。
 * 我们假象一下，如果是让你负责设计ViewGroup，你会怎么去设计呢？
 */

public class MyViewGroup extends ViewGroup {


    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        /**
         * 1.首先，我们得知道各个子View的大小吧，
         * 只有先知道子View的大小，
         * 我们才知道当前的ViewGroup该设置为多大去容纳它们。
         */


        int count = getChildCount();
        //记录当前的高度位置
        int curHight = top;

        //将子 view 逐个 摆放
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();

            //摆放子View ，参数分别是子 view 矩形区域的 左 上 右 下
            child.layout(left, curHight, left + width, curHight + height);
            curHight += height;
        }


        /**
         * 2.根据子View的大小，以及我们的ViewGroup要实现的功能，决定出ViewGroup的大小
         */

        /**
         *
         * 3.ViewGroup和子View的大小算出来了之后，接下来就是去摆放了吧，具体怎么去摆放呢？
         * 这得根据你定制的需求
         */


        /**
         * 4.已经知道怎么去摆放还不行啊，
         * 决定了怎么摆放就是相当于把已有的空间”
         * 分割”成大大小小的空间，
         * 每个空间对应一个子View，
         * 我们接下来就是把子View对号入座了，
         * 把它们放进它们该放的地方去。
         */

        /**
         * 现在就完成了ViewGroup的设计了，我们来个具体的案例：
         * 将子View按从上到下垂直顺序一个挨着一个摆放，
         * 即模仿实现LinearLayout的垂直布局。
         */


    }


    //step 1  实现测量子View大小以及设定ViewGroup的大小：
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        int expandSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec,
//                );
        /**
         * 	int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
         MeasureSpec.AT_MOST);
         super.onMeasure(widthMeasureSpec, expandSpec);
         */

        final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, childHeightMeasureSpec);

        //将所有的子View 进行测量，这会触发每个子View的onMeasure 函数
        //注意要与measureChild 区分，measureChild 是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();


//        if (getChildCount() > 0) {
//            final View child = getChildAt(0);
//            final int widthPadding;
//            final int heightPadding;
//            final int targetSdkVersion = getContext().getApplicationInfo().targetSdkVersion;
//            final ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) child.getLayoutParams();
//            if (targetSdkVersion >= Build.VERSION_CODES.M) {
//                widthPadding = getPaddingLeft() + getPaddingRight();
//                heightPadding = getPaddingTop() + getPaddingBottom() ;
//            } else {
//                widthPadding = getPaddingLeft() + getPaddingRight();
//                heightPadding = getPaddingTop() + getPaddingBottom();
//            }
//
//            final int desiredHeight = getMeasuredHeight() - heightPadding;
//            if (child.getMeasuredHeight() < desiredHeight) {
//                final int childWidthMeasureSpec = getChildMeasureSpec(
//                        widthMeasureSpec, widthPadding, lp.width);
//                final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
//                        desiredHeight, MeasureSpec.EXACTLY);
//                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//            }
//        }

        if (childCount == 0) {
            //如果没有ziView ， 当前 ViewGroup 没有存在的意义，不用占用空间
            setMeasuredDimension(0, 0);
        } else {
            //如果宽高都是 包裹内容
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //我们将高度设置为所有自View 的高度相加 ， 宽度设置为子View中最大的宽度
                int height = getTotleHeight();
                int width = getMaxChildWidth();
            } else if (heightMode == MeasureSpec.AT_MOST) {//如果高度自适应
                //宽度设置为ViewGroup 自己的测量宽度 ， 高度设置为所有子View 的总和
//                setMeasuredDimension(widthSize, MeasureSpec.makeMeasureSpec(getTotleHeight(), MeasureSpec.UNSPECIFIED));
                setMeasuredDimension(widthSize, getTotleHeight());
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //宽度设置为子view 中宽度最大的值，高度设置为 ViewGroup 自己的测量值
                setMeasuredDimension(getMaxChildWidth(), heightSize);
            }
        }


    }


    /**
     * 获取所有子view 最大的宽度
     *
     * @return
     */
    private int getMaxChildWidth() {
        int maxWidth = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            maxWidth = Math.max(maxWidth, childView.getMeasuredWidth());
        }
        return maxWidth;
    }

    /**
     * @return 返回所有子view 高度相加
     */
    private int getTotleHeight() {
        int height = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            height += childView.getMeasuredHeight();
        }
        return height;
    }


}

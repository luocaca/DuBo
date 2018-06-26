package com.example.selfview.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 图片 调整大小  居中 TextView
 */

public class DrawableCenterTextView extends AppCompatTextView {


    public DrawableCenterTextView(Context context) {

        super(context, null);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs) {
        super(context, null, 0);
    }

    public DrawableCenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {


    }
}

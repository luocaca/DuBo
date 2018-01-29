package shishicai.com.dubo.adapter;


import android.app.Activity;
import android.util.Log;
import android.view.View;

import shishicai.com.dubo.R;
import shishicai.com.dubo.WebViewActivity;
import shishicai.com.dubo.ui.hot.HotFragment;
import shishicai.com.dubo.weidet.BaseQuickAdapter;
import shishicai.com.dubo.weidet.BaseViewHolder;

/**
 * 主页
 */

public class PaiLieAdapter extends BaseQuickAdapter<HotFragment.ScoreType, BaseViewHolder> {

    public PaiLieAdapter() {
        super(R.layout.fragment_pai_lie);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotFragment.ScoreType item) {
        helper.setText(R.id.textView, item.str);
//        helper.setBackgroundColor(R.id.textView5, UiUtils.randomColor());
        helper.setImageResource(R.id.imageView, item.res);


        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.initUrl = item.href;
                WebViewActivity.start((Activity) mContext);
                Log.i(TAG, "onClick: " + item.href);
            }
        });


    }
}

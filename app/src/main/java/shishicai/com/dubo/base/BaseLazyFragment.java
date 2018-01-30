package shishicai.com.dubo.base;

import android.util.Log;

/**
 * 基础 fragment
 */

public abstract class BaseLazyFragment extends BaseFragment {

    private static final String TAG = "BaseLazyFragment";


    protected void loadData() {
        if (!mIsVisible || !mIsPrepared || !isFirst) {
            Log.e(TAG, "不加载数据 mIsVisible=" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
            return;
        } else {
            Log.e(TAG, "加载数据" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
        }

    }


}

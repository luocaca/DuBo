package shishicai.com.dubo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weavey.loading.lib.LoadingLayout;

import org.xutils.x;

import shishicai.com.dubo.util.D;

/**
 * 基础 fragment
 */

public abstract class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;

    // fragment是否显示了
    public boolean mIsVisible = false;


    public View rootView;

    protected LoadingLayout loadingLayout;
    private View loadPage;


    public boolean mIsPrepared = false;
    public boolean isFirst = true;


//    public boolean ismIsVisible() {
//        return mIsVisible;
//    }

    protected abstract int bindLayoutID();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(bindLayoutID(), null);
        this.rootView = mRootView;
        x.view().inject(this, rootView);

        initView(mRootView);
        initListener();
        D.e("======当前Fragment===位置=====" + this.getClass().getName());


        initView(inflater, container, savedInstanceState);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        isPrepared = true;
//        loadData();


    }


    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    protected void initListener() {

    }


    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    private static final String TAG = "BaseFragment";

    protected void onVisible() {
//        if (!isPrepared)return;

        if (!mIsVisible || !mIsPrepared || !isFirst) {
            Log.e(TAG, "不加载数据 mIsVisible=" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
            return;
        } else {
            Log.e(TAG, "加载数据" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
        }

        loadData();
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
        mIsPrepared = true;
        /**
         * 因为启动时先走loadData()再走onActivityCreated，
         * 所以此处要额外调用load(),不然最初不会加载内容
         */
        loadData();
    }


    protected void loadData() {
        isFirst = false;
    }

    protected void onInvisible() {
    }


    @Override
    public void onAttach(Context context) {
        mActivity = (AppCompatActivity) context;
        super.onAttach(context);
    }

    protected abstract void initView(View rootView);


    /**
     * Views indexed with their IDs
     */
    private SparseArray<View> views = new SparseArray<>();

    protected <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


    public void showSnackBar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();

    }

}

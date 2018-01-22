package shishicai.com.dubo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    protected Activity mActivity;

    // fragment是否显示了
    public boolean mIsVisible = false;


    public View rootView;

    protected LoadingLayout loadingLayout;
    private View loadPage;

//    public boolean ismIsVisible() {
//        return mIsVisible;
//    }

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

    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    ;


    protected void initListener() {

    }


    public int bindLoadingLayout() {
        return 0;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    protected void onVisible() {
        loadData();
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadData() {


    }

    protected void onInvisible() {
    }


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
    }

    protected abstract void initView(View rootView);

    protected abstract int bindLayoutID();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


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


}

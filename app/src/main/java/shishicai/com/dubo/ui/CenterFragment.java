package shishicai.com.dubo.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.model.HotNews;

import static android.content.ContentValues.TAG;

/**
 * 个人中心界面  用于检查更新，查看版本信息 已经推送中心
 */

public class CenterFragment extends BaseFragment {

    String host = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=3&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";

    RecyclerView rvToDoList;

    List<HotNews> newsList = new ArrayList<>();


    @ViewInject(R.id.base_web_view)
    WebView mWebView;
    @ViewInject(R.id.base_web_progressBar)
    ProgressBar progressBar;


    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_center;
    }


    @Override
    protected void initView(final View rootView) {
        x.view().inject(CenterFragment.this, rootView);


        //启用支持Javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "loadingUrl =   \n" + url);
//                onLoadUrl(mWebView, url);
                view.loadUrl(url);
                return true;
            }
        });
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //页面加载
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress   1-100之间的整数
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                    //页面加载完成，关闭ProgressDialog
//                    mLoadingLayout.setStatus(LoadingLayout.Success);
                } else {
                    //网页正在加载，打开ProgressDialog
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });

        mWebView.loadUrl("http://m.zhcw.com/kaijiang/");


    }


}

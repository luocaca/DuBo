package shishicai.com.dubo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

import shishicai.com.dubo.base.BaseWebViewActivity;

/**
 *
 */

public class WebViewActivity extends BaseWebViewActivity {

    public static String initUrl = "http://www.baidu.com";
    public static String title = "";

    //http://m.zhcw.com/index.jsp;jsessionid=306C148A7E0DE33C0B859137D61FAB80.h5_229

    @Override
    public String BaseUrl() {
        return getUrl();
    }

    public String getUrl() {
        return initUrl;
    }

    @Override
    public int bindLayoutID() {
        return R.layout.activity_webview_base;
    }

    //http://m.zhcw.com/khd/zx/cx/gdlb/14685756.shtml
    //http://www.zhcw.com/czpd/kkcc/k3/'
    @Override
    public void initView() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.new_red));
        }
        super.initView();
//        Toolbar mToolbar = (Toolbar) findViewById(toolbar);
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setTitle("时时彩百科");
//        }
//
//        mToolbar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onLoadUrl(WebView webView, String url) {
        if (url.contains("http://m.zhcw.com/index.jsp;")) {
            finish();//返回首页
        } else {
            super.onLoadUrl(webView, url);
        }

    }

    public static void start(Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, WebViewActivity.class));
    }


}

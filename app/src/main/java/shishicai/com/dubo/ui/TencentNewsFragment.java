package shishicai.com.dubo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.util.D;

/**
 *
 *
 */

public class TencentNewsFragment extends BaseFragment {

    private static final String TAG = "MeFragmentDetail";

    WebView webview;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

//            webview.loadData(stringBuffer.toString(), "text/html","urf-8");
            webview.loadDataWithBaseURL(null, stringBuffer.toString(), "text/html", "utf-8", null);
//            tv_content.setText(Html.fromHtml(stringBuffer.toString()));
//            loadImg();


        }
    };


    private StringBuffer stringBuffer;


    public static TencentNewsFragment newInstances(String url) {
        TencentNewsFragment meFragmentDetail = new TencentNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("aaa", url);
        meFragmentDetail.setArguments(bundle);
        return meFragmentDetail;
    }

    public String getUrl() {
//        String st = getArguments().getString("aaa", "");
//        Snackbar.make(rootView, "url-> " + st, Snackbar.LENGTH_LONG).show();
        return "http://auto.qq.com/a/20180107/000750.htm?pgv_ref=aio2015&ptlang=2052";
    }


    @Override
    protected void initView(final View rootView) {

        webview = rootView.findViewById(R.id.webview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                request();

            }
        }).start();


    }

    public void request() {

        try {
            Document page = Jsoup.connect(getUrl()).get();
            Element lis = page.getElementsByClass("qq_main").first();
            stringBuffer = new StringBuffer();
            for (Element li : lis.getAllElements()) {

//                if (li.getElementsContainingText("上一页") == null) {
//                    return;
//                }


//                if (li.getElementsContainingOwnText("上一页") != null) {
//                    return;
//                }

                D.i("------->" + li);

//                if (li.getElementsContainingText("上一页") != null) {
//                    Log.i(TAG, "request: " + li.getElementsContainingText("上一页"));
//                }


                if (li.getElementsContainingOwnText("上一页") != null) {
//                    return;
                    Log.i(TAG, "request: " + li.getElementsContainingOwnText("上一页"));
//                }

                    stringBuffer.append(li);
                }

            }
            handler.sendEmptyMessage(0);
            Snackbar.make(rootView, "加载成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
//            request();
            Snackbar.make(rootView, "加载失败，重试中 ..." + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_me_tencent;
    }


}

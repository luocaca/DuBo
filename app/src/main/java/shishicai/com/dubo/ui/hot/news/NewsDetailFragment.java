package shishicai.com.dubo.ui.hot.news;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.transition.TransitionInflater;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.util.D;

/**
 * Created by Administrator on 2018/1/25 0025.
 */

public class NewsDetailFragment extends BaseFragment {


    @ViewInject(R.id.share)
    ImageView share;

    @ViewInject(R.id.collapsing_toolbar)
    CollapsingToolbarLayout toolbarLayout;

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;

    @ViewInject(R.id.text)
    TextView text;
    @ViewInject(R.id.webview)
    WebView webview;

    public static NewsDetailFragment newInstance() {

        return new NewsDetailFragment();
    }

    public static String imageUrl = "";
    public static String title = "";
    public static String contentUrl = "";


    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_news_detail;
    }


    @Override
    protected void initView(View rootView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(
                    TransitionInflater.from(getContext())
                            .inflateTransition(android.R.transition.move));
        }
//        toolbarL.setDisplayHomeAsUpEnabled(true);
        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
        compatActivity.setSupportActionBar(toolbar);
        compatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationIcon(R.drawable.sample_footer_loading);
//        toolbar.setTitle("喜马拉雅");
//        toolbar.setNavigationIcon(R.drawable.gray_radius);


        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        toolbarLayout.setTitle(title);

        new Thread(() -> {
            try {
                Document document = Jsoup.connect(contentUrl).get();


                webview.post(() -> {

                    String co = document.getElementsByClass("wzxq").first().toString();
//                    text.setText(Html.fromHtml());
                    showSnackBar(co);
                    webview.loadData(co, "text/html", "UTF-8"); // 加载定义的代码，并设定编码格式和字符集。


                });
            } catch (IOException e) {
                text.post(() -> {
                    text.setText(e.getMessage());
                });

                e.printStackTrace();
            }

        }).start();


        D.i("=========url============\n" + contentUrl);


    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.initView(inflater, container, savedInstanceState);

        x.image().bind(share,
                imageUrl, new Callback.CacheCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
//                        share.setImageDrawable(result);
                        startPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        share.setImageResource(R.mipmap.ssc_logo_160);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public boolean onCache(Drawable result) {
//                        share.setImageDrawable(result);
                        startPostponedEnterTransition();
                        return true;
                    }
                })

        ;

    }

    @Override
    protected void loadData() {
        super.loadData();


//        Glide.with(getContext())
//                .load("https://s3-us-west-1.amazonaws.com/powr/defaults/image-slider2.jpg")
//                .centerCrop()
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        startPostponedEnterTransition();
//
//                    }
//                });
//
//        ;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//
//        作者：SheepYan9
//        链接：https://www.jianshu.com/p/e87c0086a3ae
//        來源：简书
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    private static final String TAG = "NewsDetailFragment";

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionsMenu()");
        menu.clear();
        inflater.inflate(R.menu.toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
//        super.onCreateOptionsMenu(menu, inflater);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Log.i(TAG, "action android selected");
                showSnackBar("navigation_home");
                return true;
            case R.id.navigation_dashboard:
                Log.i(TAG, "action favourite selected");
                showSnackBar("navigation_dashboard");
                return true;
            case R.id.navigation_notifications:
                Log.i(TAG, "action settings selected");
                showSnackBar("navigation_notifications");
                return true;

            case android.R.id.home:

                showSnackBar("back");

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

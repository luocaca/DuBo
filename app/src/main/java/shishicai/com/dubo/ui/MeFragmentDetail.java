package shishicai.com.dubo.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.util.D;

/**
 *
 *
 */

public class MeFragmentDetail extends BaseFragment {

    private static final String TAG = "MeFragmentDetail";

    RecyclerView rvToDoList;

    TextView tv_content;

    FloatingActionButton actionButton;

    ImageView imageView;

    String currentImgUrl = "http://www.zhcw.com/upload/Image/xinwen5/1_26017648341.jpg";


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv_content.setText(Html.fromHtml(stringBuffer.toString()));
            loadImg();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadImg();
                }
            }, 300);


        }
    };



    private StringBuffer stringBuffer;


    public void loadImg() {
//        x.image().loadDrawable()
        x.image().loadDrawable(currentImgUrl
                , ImageOptions.DEFAULT, new org.xutils.common.Callback.CacheCallback<Drawable>() {
                    @Override
                    public boolean onCache(Drawable result) {
                        Log.i(TAG, "onCache: ");
                        actionButton.setBackgroundDrawable(result);
                        imageView.setImageDrawable(result);
                        return true;
                    }

                    @Override
                    public void onSuccess(Drawable result) {
                        Log.i(TAG, "onSuccess: ");
                        actionButton.setBackgroundDrawable(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.i(TAG, "onCancelled: ");
                    }

                    @Override
                    public void onFinished() {
                        Log.i(TAG, "onFinished: ");
                    }
                }
        );


    }


    public static MeFragmentDetail newInstances(String url) {
        MeFragmentDetail meFragmentDetail = new MeFragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putString("aaa", url);
        meFragmentDetail.setArguments(bundle);
        return meFragmentDetail;
    }

    public String getUrl() {
        String st = getArguments().getString("aaa", "");
        Snackbar.make(rootView, "url-> " + st, Snackbar.LENGTH_LONG).show();
        return st;
    }


    @Override
    protected void initView(final View rootView) {

        imageView = rootView.findViewById(R.id.backdrop);
        tv_content = rootView.findViewById(R.id.tv_content);
        actionButton = rootView.findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        /*读取缓存*/
//            newsList = x.getDb(new DbManager.DaoConfig()).findAll(News.class);
//            Snackbar.make(rootView, "加载缓存成功....", Snackbar.LENGTH_LONG).show();


        //http://www.zhcw.com/upload/Image/xinwen5/1_26017648341.jpg
//      actionButton.setBackground();

//        x.image().loadDrawable("http://www.zhcw.com/upload/Image/xinwen5/1_26017648341.jpg"
//                , ImageOptions.DEFAULT, new Callback.CacheCallback<Drawable>() {
//                    @Override
//                    public boolean onCache(Drawable result) {
//                        Log.i(TAG, "onCache: ");
//                        actionButton.setBackgroundDrawable(result);
//                        imageView.setImageDrawable(result);
//                        return true;
//                    }
//
//                    @Override
//                    public void onSuccess(Drawable result) {
//                        Log.i(TAG, "onSuccess: ");
//                        actionButton.setBackgroundDrawable(result);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        Log.i(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//                        Log.i(TAG, "onCancelled: ");
//                    }
//
//                    @Override
//                    public void onFinished() {
//                        Log.i(TAG, "onFinished: ");
//                    }
//                }
//        );


        /**
         *  <div class="minA-a clearfix">
         <dl>
         <dt><img src="/upload/Image/xinwen5/1_26017648341.jpg"  alt="福彩快3概述" title="福彩快3概述" width="180" height="180"/></dt>
         <dd><p>　　<strong>销售地区：</strong><a href="http://www.zhcw.com/kj/hddg/js/k3/" target="_blank">江苏</a>、<a href="http://www.zhcw.com/kj/hbdg/hub/k3/" target="_blank">湖北</a>、<a href="http://www.zhcw.com/kj/dbdg/jl/k3/" target="_blank">吉林</a>、<a href="http://www.zhcw.com/kj/hbdg/hb/k3/" target="_blank">河北</a></p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贵州、<a href="http://www.nmlottery.com.cn/k3/" target="_blank">内蒙古</a>、<a href="http://www.zhcw.com/kj/hddg/ah/k3/" target="_blank">安徽</a>、福建</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.zhcw.com/kj/hddg/sh/k3/" target="_blank">上海</a>、<a href="http://www.zhcw.com/kj/hndg/ax/k3/" target="_blank">广西</a>、青海、西藏</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 甘肃</p><p>　　<strong>开奖频次：</strong>10分钟1期</p><p>　　<strong>选号范围：</strong>三个号码，每个号码1到6<br />&nbsp;</p></dd>
         </dl>

         </div>
         </div>
         */


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
            Elements lis = page.getElementsByClass("minA");

            stringBuffer = new StringBuffer();
            for (Element li : lis) {

                D.i("------->" + li);

                // <h3 class="mTit">投注规则</h3>
                //  <dt><img src="/upload/Image/xinwen5/1_26017648341.jpg"

                String title = li.getElementsByClass("mTit").first().text();

//                stringBuffer.append(title + "\n");


                stringBuffer.append(li);


//                Elements inner = li.getElementsByTag("li");
//                for (int i = 0; i < inner.size(); i++) {
//                    News news = new News();
//                    news.title = inner.get(i).select("a").text();
//                    news.url = inner.get(i).select("a").attr("abs:href");
//                    D.i("============News============\n" + news.toString());
//                    newsList.add(news);
//                      /*add to cache */
//                    x.getDb(new DbManager.DaoConfig())
//                            .saveOrUpdate(news);
//                }

            }

            Element element = page.getElementsByClass("minA-a clearfix").first();

            currentImgUrl = element.getElementsByTag("img").first().attr("abs:src");
//            currentImgUrl = li.getElementsByClass("mTit").first().attr("abs:href");


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
        return R.layout.fragment_me_detail;
    }


}

package shishicai.com.dubo.ui.hot.child;

import android.content.pm.ActivityInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.adapter.VideoRecyclerAdapter;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.model.HotNews;
import shishicai.com.dubo.model.VideoBean;
import shishicai.com.dubo.util.D;
import shishicai.com.dubo.util.GsonUtil;
import shishicai.com.dubo.util.MyAnimator;
import shishicai.com.dubo.video.MyVideoView;

import static android.content.ContentValues.TAG;

/**
 * 视频
 */


public class VideoFragment extends BaseFragment {

    String host = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=3&pageSize=20&busiCode=300209&src=0000100001%7C6000003060";

    RecyclerView rvToDoList;

    List<HotNews.DataListBean> newsList = new ArrayList<>();

    public String busiCode = "300209";


    private Toolbar toolbar;

//    private RecyclerView recyclerView;

//    private VideoRecyclerAdapter mAdapter;

    private FrameLayout videoRootViewFl;

    private MyVideoView videoView;

    private FrameLayout fullScreen;

    private View lastView;

    private int videoPosition = -1;

    private List<VideoBean> videoBeanList = new ArrayList<>();

//    private int[] imageIds = new int[]{R.drawable.hzw_a, R.drawable.hzw_b,
//            R.drawable.hzw_d, R.drawable.hzw_e, R.drawable.hzw_f, R.drawable.hzw_h,
//            R.drawable.hzw_i, R.drawable.hzw_j, R.drawable.hzw_k};

    private static String VIDEO_PATH = "http://dn-chunyu.qbox.me/fwb/static/images/home/video/video_aboutCY_A.mp4";


    int page = 1;

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_video;
    }


    @Override
    protected void initView(final View rootView) {


        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            supportActionBar.setDisplayHomeAsUpEnabled(true);
//            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
//            supportActionBar.setTitle("");
//        }
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        videoRootViewFl = (FrameLayout) rootView.findViewById(R.id.video_root_fl);
        fullScreen = (FrameLayout) rootView.findViewById(R.id.video_full_screen);
//        mAdapter = new VideoRecyclerAdapter(videoBeanList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mAdapter);

        /**
         * w为其提供一个控制器，控制其暂停、播放……等功能
         */
//        video1.setMediaController(new MediaController(mActivity));


//        video1.setVisibility(View.VISIBLE);


        rvToDoList = rootView.findViewById(R.id.rvToDoList);

//        rvToDoList.setVisibility(View.GONE);


        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvToDoList.setItemAnimator(new DefaultItemAnimator());

        rvToDoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
//                if (recyclerView.getAdapter() != null) {
//                    return;
//                }
                try {
                    int totalItemCount = recyclerView.getAdapter().getItemCount();
                    int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                    int visibleItemCount = recyclerView.getChildCount();

                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastVisibleItemPosition == totalItemCount - 1
                            && visibleItemCount > 0) {
                        //加载更多
                        request(page++);

                    }
                } catch (Exception e) {
                    Log.w(TAG, "onScrollStateChanged: " + e.getMessage());
//                    e.printStackTrace();
                }


            }
        });


        request(page++);

//        rootView.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebActivity.start(getActivity());
//            }
//        });

    }

    private void request(int page) {

        Snackbar.make(rootView, "加载中新数据....", Toast.LENGTH_SHORT).show();
//        String url = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";
        String url = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=" + busiCode + "&src=0000100001%7C6000003060";

        RequestParams params = new RequestParams(url);

        // 默认缓存存活时间, 单位:毫秒.(如果服务没有返回有效的max-age或Expires)
        params.setCacheMaxAge(1000 * 60 * 60 * 24);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {

                // 得到缓存数据, 缓存过期后不会进入这个方法.
                // 如果服务端没有返回过期时间, 参考params.setCacheMaxAge(maxAge)方法.
                //
                // * 客户端会根据服务端返回的 header 中 max-age 或 expires 来确定本地缓存是否给 onCache 方法.
                //   如果服务端没有返回 max-age 或 expires, 那么缓存将一直保存, 除非这里自己定义了返回false的
                //   逻辑, 那么xUtils将请求新数据, 来覆盖它.
                //
                // * 如果信任该缓存返回 true, 将不再请求网络;
                //   返回 false 继续请求网络, 但会在请求头中加上ETag, Last-Modified等信息,
                //   如果服务端返回304, 则表示数据没有更新, 不继续加载数据.
                //

//                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
//                D.i(result);
                HotNews hotNews = GsonUtil.formateJson2Bean(result, HotNews.class);

//                newsList.addAll(hotNews.dataList);
                setDatas(hotNews.dataList);
//                WebActivity.start(SplashActivity.this);
                D.i("====================json===============\n" + GsonUtil.formatJson2String(result));

                return true;
            }

            @Override
            public void onSuccess(String result) {
//              Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
//                D.i(result);
                HotNews hotNews = GsonUtil.formateJson2Bean(result, HotNews.class);

//                newsList.addAll(hotNews.dataList);
                setDatas(hotNews.dataList);
//                WebActivity.start(SplashActivity.this);
                D.i("====================json===============\n" + GsonUtil.formatJson2String(result));

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//
                Snackbar.make(rootView, "加载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Snackbar.make(rootView, "取消请求", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {
                Snackbar.make(rootView, "加载结束", Toast.LENGTH_SHORT).show();
            }
        });


    }


    VideoRecyclerAdapter myAdapter;

    public void setDatas(List<HotNews.DataListBean> datas) {


        MyAnimator.animationsLocked = false;
        newsList.addAll(datas);
        if (myAdapter == null) {
            myAdapter = new VideoRecyclerAdapter(conver2VideoBeans(newsList));
            rvToDoList.setAdapter(myAdapter);
            initEvent();

        } else {
            myAdapter.notifyDataSetChanged();
        }


    }

    List<VideoBean> videoBeans = new ArrayList<>();

    public List<VideoBean> conver2VideoBeans(List<HotNews.DataListBean> newsList) {
        for (HotNews.DataListBean dataListBean : newsList) {
            VideoBean videoBean = new VideoBean(dataListBean.logoFile, "http://video1.zhcw.com/zhcw/app/csbb/cxjst20180122.mp4");
            videoBeans.add(videoBean);
        }

        return videoBeans;
    }


//
//    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//
//        List<HotNews.DataListBean> mNewsList;
//        Context mContext;
//
//        public MyAdapter(List<HotNews.DataListBean> newsList, Context context) {
//            mNewsList = newsList;
//            mContext = context;
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_video, parent, false);
//            return new MyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            MyAnimator.runEnterAnimation(holder.itemView, position);
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    WebViewActivity.initUrl = mNewsList.get(position).url;
////                    WebViewActivity.start((Activity) mContext);
////                    Log.i(TAG, "onClick: " + mNewsList.get(position).url);
//                    holder.video.setVideoPath("http://video1.zhcw.com/zhcw/app/csbb/cxjst20180122.mp4");
////                    holder.video.setBackgroundColor(0);
//                    holder.video.start();
////                    holder.video.setMediaController(new MyMediaController(mContext, holder.video, (Activity) mContext));
////                    holder.video.setMediaController(new MyMediaController(mContext, holder.video, (Activity) mContext));
//                }
//            });
////          holder.time.setText(mNewsList.get(position).publishDate);
//            holder.content.setText(TextUtils.isEmpty(mNewsList.get(position).summary) ? "-" : mNewsList.get(position).summary);
//            holder.t.setText(mNewsList.get(position).title);
////            x.image().bind(holder.imageView, mNewsList.get(position).logoFile);
//
//            D.i("onBindViewHolder" + mNewsList.toString());
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return mNewsList.size();
//        }
//    }
//
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView t;
//        TextView time;
//        TextView content;
//        ImageView imageView;
//
//        VideoView video;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            t = itemView.findViewById(R.id.title);
//            content = itemView.findViewById(R.id.content);
//            time = itemView.findViewById(R.id.time);
//            video = itemView.findViewById(R.id.video);
//
//
//            imageView = itemView.findViewById(R.id.imageView);
//
//        }
//    }


    private void showVideo(View view, final String videoPath) {
        View v;
        removeVideoView();
        if (videoRootViewFl.getVisibility() == View.VISIBLE) {
            videoRootViewFl.removeAllViews();
            videoRootViewFl.setVisibility(View.GONE);
        }
        if (videoView == null) {
            videoView = new MyVideoView(getContext());
            videoView.setListener(new MyVideoView.IFullScreenListener() {
                @Override
                public void onClickFull(boolean isFull) {
                    if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
                        fullScreen.setVisibility(View.VISIBLE);
                        removeVideoView();
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        fullScreen.addView(videoView, new ViewGroup.LayoutParams(-1, -1));
                        videoView.setVideoPath(VIDEO_PATH);
                        videoView.start();
                    } else {
                        fullScreen.removeAllViews();
                        fullScreen.setVisibility(View.GONE);
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        if (lastView instanceof ViewGroup) {
                            ((ViewGroup) lastView).addView(videoView);
                        }
                        videoView.setVideoPath(VIDEO_PATH);
                        videoView.start();
                    }

                }
            });
        }
        videoView.stop();
        v = view.findViewById(R.id.item_imageview);
        if (v != null) v.setVisibility(View.INVISIBLE);
        v = view.findViewById(R.id.item_image_play);
        if (v != null) v.setVisibility(View.INVISIBLE);
        v = view.findViewById(R.id.item_video_root_fl);
        if (v != null) {
            v.setVisibility(View.VISIBLE);
            FrameLayout fl = (FrameLayout) v;
            fl.removeAllViews();
            fl.addView(videoView, new ViewGroup.LayoutParams(-1, -1));
            VIDEO_PATH = videoPath;
            videoView.setVideoPath(videoPath);
            videoView.start();
        }
        lastView = view;
    }

    private void removeVideoView() {
        View v;
        if (lastView != null) {
            v = lastView.findViewById(R.id.item_imageview);
            if (v != null) v.setVisibility(View.VISIBLE);
            v = lastView.findViewById(R.id.item_image_play);
            if (v != null) v.setVisibility(View.VISIBLE);
            v = lastView.findViewById(R.id.item_video_root_fl);
            if (v != null) {
                FrameLayout ll = (FrameLayout) v;
                ll.removeAllViews();
                v.setVisibility(View.GONE);
            }
        }
    }


    private void initEvent() {
        myAdapter.setListener(new VideoRecyclerAdapter.OnClickPlayListener() {
            @Override
            public void onPlayClick(View view, String videoPath) {
                showVideo(view, videoPath);
            }
        });
        rvToDoList.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if (videoPosition == -1 || videoRootViewFl.getVisibility() != View.VISIBLE) {
                    return;
                }
                if (videoPosition == rvToDoList.getChildAdapterPosition(view)) {
                    videoPosition = -1;
                    showVideo(view, VIDEO_PATH);
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if (videoView == null || videoRootViewFl.getVisibility() == View.VISIBLE) return;
                View v = view.findViewById(R.id.item_video_root_fl);
                if (v != null) {
                    FrameLayout fl = (FrameLayout) v;
                    videoPosition = rvToDoList.getChildAdapterPosition(view);
                    if (fl.getChildCount() > 0) {
                        fl.removeAllViews();
                        int position = 0;
                        if (videoView.isPlaying()) {
                            position = videoView.getPosition();
                            videoView.stop();
                        }
                        videoRootViewFl.setVisibility(View.VISIBLE);
                        videoRootViewFl.removeAllViews();
                        lastView = videoRootViewFl;
                        videoRootViewFl.addView(videoView, new ViewGroup.LayoutParams(-1, -1));
                        videoView.setVideoPath(VIDEO_PATH);
                        videoView.start();
                        videoView.seekTo(position);
//                        if (videoView.isPause()) {
//                            videoView.resume();
//                        }
                    }
                    fl.setVisibility(View.GONE);
                }
                v = view.findViewById(R.id.item_imageview);
                if (v != null) {
                    if (v.getVisibility() != View.VISIBLE) {
                        v.setVisibility(View.VISIBLE);
                    }
                }
                v = view.findViewById(R.id.item_image_play);
                if (v != null) {
                    if (v.getVisibility() != View.VISIBLE) {
                        v.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        if (videoView != null) {
            videoView.stop();
        }
        super.onDestroy();
    }

}

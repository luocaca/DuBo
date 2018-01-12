package shishicai.com.dubo.ui.hot.child;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.WebViewActivity;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.model.HotNews;
import shishicai.com.dubo.util.D;
import shishicai.com.dubo.util.GsonUtil;
import shishicai.com.dubo.util.MyAnimator;

import static android.content.ContentValues.TAG;

/**
 * 头条
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060
 */

public class TopFragment extends BaseFragment {

    String host = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";

    RecyclerView rvToDoList;

    List<HotNews.DataListBean> newsList = new ArrayList<>();

    public String busiCode = "300203";

    int page = 1;

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_hot;
    }


    @Override
    protected void initView(final View rootView) {


        rvToDoList = rootView.findViewById(R.id.rvToDoList);


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

        //            http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=300203&src=0000100001%7C6000003060
        String url = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=\" + busiCode + \"&src=0000100001%7C6000003060";
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


    MyAdapter myAdapter;

    public void setDatas(List<HotNews.DataListBean> datas) {
        MyAnimator.animationsLocked = false;
        newsList.addAll(datas);
        if (myAdapter == null) {
            myAdapter = new MyAdapter(newsList, getActivity());
            rvToDoList.setAdapter(myAdapter);
        } else {
            myAdapter.notifyDataSetChanged();
        }


    }


    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<HotNews.DataListBean> mNewsList;
        Context mContext;

        public MyAdapter(List<HotNews.DataListBean> newsList, Context context) {
            mNewsList = newsList;
            mContext = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_hot, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            MyAnimator.runEnterAnimation(holder.itemView, position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebViewActivity.initUrl = mNewsList.get(position).url;
                    WebViewActivity.start((Activity) mContext);

                    Log.i(TAG, "onClick: " + mNewsList.get(position).url);
                }
            });
            holder.time.setText(mNewsList.get(position).publishDate);
            holder.content.setText(TextUtils.isEmpty(mNewsList.get(position).summary) ? "-" : mNewsList.get(position).summary);
            holder.t.setText(mNewsList.get(position).title);
            x.image().bind(holder.imageView, mNewsList.get(position).logoFile);

            D.i("onBindViewHolder" + mNewsList.toString());

        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t;
        TextView time;
        TextView content;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);


            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}

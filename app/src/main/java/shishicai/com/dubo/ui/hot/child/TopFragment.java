package shishicai.com.dubo.ui.hot.child;

import android.app.Activity;
import android.content.Context;
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
        return R.layout.fragment_top;
    }


    @Override
    protected void initView(final View rootView) {

        this.rootView = rootView;

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


//        rootView.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebActivity.start(getActivity());
//            }
//        });


    }

    @Override
    protected void loadData() {
        request(page++);
    }

    private void request(int page) {

        D.e("========request===========");

//        Snackbar.make(rootView, "加载中新数据....", Toast.LENGTH_SHORT).show();
//        String host = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";

        //            http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=300203&src=0000100001%7C6000003060
        String url = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=" + busiCode + "&src=0000100001%7C6000003060";
        RequestParams params = new RequestParams(url);

        // 默认缓存存活时间, 单位:毫秒.(如果服务没有返回有效的max-age或Expires)
        params.setCacheMaxAge(1000 * 60 * 60 * 24);
        x.http().get(params, new Callback.CommonCallback<String>() {
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

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

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
            myAdapter.notifyDataSetChanged();
        } else {
            myAdapter.notifyDataSetChanged();
        }


    }


    static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<HotNews.DataListBean> mNewsList;
        Context mContext;

        public MyAdapter(List<HotNews.DataListBean> newsList, Context context) {
            mNewsList = newsList;
            mContext = context;
        }

        @Override
        public int getItemViewType(int position) {

            if (position % 10 == 0) {
                return 1;
            } else {
                return super.getItemViewType(position);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == 1) {
                view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_hot_type, parent, false);
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_hot, parent, false);
            }

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

            if (holder.time != null)
                holder.time.setText(mNewsList.get(position).publishDate);
            if (holder.content != null)
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

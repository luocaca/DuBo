package shishicai.com.dubo.ui;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import static android.content.ContentValues.TAG;

/**
 * 个人中心界面  用于检查更新，查看版本信息 已经推送中心
 */

public class HotFragment extends BaseFragment {

    String host = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=3&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";

    RecyclerView rvToDoList;

    List<HotNews.DataListBean> newsList = new ArrayList<>();

    int page = 1;

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_hot;
    }


    @Override
    protected void initView(final View rootView) {


        rvToDoList = rootView.findViewById(R.id.rvToDoList);


        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvToDoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                int visibleItemCount = recyclerView.getChildCount();

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition == totalItemCount - 1
                        && visibleItemCount > 0) {
                    //加载更多
                    request(page++);

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
        String url = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=" + page + "&pageSize=20&busiCode=300205&src=0000100001%7C6000003060";
        RequestParams params = new RequestParams(url);
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebViewActivity.initUrl = mNewsList.get(position).url;
                    WebViewActivity.start((Activity) mContext);

                    Log.i(TAG, "onClick: " + mNewsList.get(position).url);
                }
            });
            holder.time.setText(mNewsList.get(position).publishDate);
            holder.content.setText(mNewsList.get(position).summary);
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

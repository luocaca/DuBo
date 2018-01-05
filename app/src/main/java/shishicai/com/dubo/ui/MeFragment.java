package shishicai.com.dubo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.WebViewActivity;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.util.D;

/**
 * 个人中心界面  用于检查更新，查看版本信息 已经推送中心
 */

public class MeFragment extends BaseFragment {

    RecyclerView rvToDoList;

    List<News> newsList = new ArrayList<>();


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            setDatas();
        }
    };

    @Override
    protected void initView(final View rootView) {

        /**
         * <li class="m-expanded">
         <p>快开彩种</p>
         <ul>
         <li><a href="/czpd/kkcc/k3/" title="快3" target="_blank">快3</a></li>
         <li><a href="/czpd/kkcc/klsb/" title="快乐十分" target="_blank">快乐十分</a></li>
         <li><a href="/czpd/kkcc/ssc/" title="时时彩" target="_blank">时时彩</a></li>
         <li><a href="/czpd/kkcc/qyh/" title="群英会" target="_blank">群英会</a></li>
         </ul>
         </li>
         */
        //http://www.zhcw.com/czpd/kkcc/k3/
//        Document html = Jsoup.connect().get();

        new Thread(new Runnable() {
            @Override
            public void run() {

                request();


            }
        }).start();


//        rootView.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebActivity.start(getActivity());
//            }
//        });


        rootView.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.initUrl="http://m.zhcw.com/index.jsp;jsessionid=306C148A7E0DE33C0B859137D61FAB80.h5_229";
                WebViewActivity.start(getActivity());
            }
        });

        rvToDoList = rootView.findViewById(R.id.rvToDoList);


        rvToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    public void request() {

        try {
            Document page = Jsoup.connect("http://www.zhcw.com/czpd/kkcc/k3/").get();
            Elements lis = page.getElementsByClass("m-expanded");
            for (Element li : lis) {

                Elements inner = li.getElementsByTag("li");
                for (int i = 0; i < inner.size(); i++) {
                    News news = new News();
                    news.title = inner.get(i).select("a").text();
                    news.url = inner.get(i).select("a").attr("abs:href");
                    D.i("============News============\n" + news.toString());
                    newsList.add(news);
                }

            }


            handler.sendEmptyMessage(0);
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    setDatas();
//                }
//            });


            Snackbar.make(rootView, "加载成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            request();
            Snackbar.make(rootView, "加载失败，重试中 ...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_me;
    }


    public void setDatas() {
        rvToDoList.setAdapter(new News.MyAdapter(newsList, mActivity));

//        rvToDoList.setAdapter(new RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item, null);
//                return new RecyclerView.ViewHolder(view) {
//
//
//                };
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                TextView textView = holder.itemView.findViewById(R.id.title);
//                textView.setText(newsList.get(position).title);
//            }
//
//            @Override
//            public int getItemCount() {
//                return newsList.size();
//            }
//        });
//http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=3&pageSize=20&busiCode=300205&src=0000100001%7C6000003060

    }


    public static class News {
        public String url = "";
        public String title = "";

        @Override
        public String toString() {
            return "News{" +
                    "url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }


        private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

            List<News> mNewsList;
            Context mContext;

            public MyAdapter(List<News> newsList, Context context) {
                mNewsList = newsList;
                mContext = context;
            }

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item1,parent ,false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, final int position) {

//              holder.t.setText("hello world" + position);
                holder.t.setText(mNewsList.get(position).title);

//                LinearLayout.LayoutParams marginLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                holder.t.setLayoutParams(marginLayoutParams);

                holder.t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Snackbar.make(view, mNewsList.get(position).title + "\n " + mNewsList.get(position).url, Snackbar.LENGTH_LONG).show();
                        WebViewActivity.initUrl = mNewsList.get(position).url;
                        WebViewActivity.start((Activity) mContext);

                    }
                });


                D.i("onBindViewHolder" + mNewsList.toString());

            }

            @Override
            public int getItemCount() {
                return mNewsList.size();
            }
        }


    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t;

        public MyViewHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.title);

        }
    }

}

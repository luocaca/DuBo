package shishicai.com.dubo.ui.hot.PaiLieChild;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseLazyFragment;
import shishicai.com.dubo.model.VideosGson;
import shishicai.com.dubo.util.D;
import shishicai.com.dubo.util.GsonUtil;
import shishicai.com.dubo.weidet.BaseQuickAdapter;
import shishicai.com.dubo.weidet.BaseViewHolder;
import shishicai.com.dubo.weidet.CoreRecyclerView;

/**
 * lazy  fragment
 */

public class ItemFragment extends BaseLazyFragment {


    public String url = "http://m.zhcw.com/clienth5.do?lottery=FC_SSQ&pageSize=20&pageNo=6&transactionType=300301&src=0000100001%7C6000003060";

    @ViewInject(R.id.core_recycle)
    CoreRecyclerView core_recycle;
    private VideosGson videosGson;
    private int currentPage = 1;
    private String currentType = "FC_SSQ";


    public static ItemFragment newInstances(String type) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAG, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getBundle() {
        //FC_3D
        return getArguments().getString(TAG, "FC_SSQ");
    }


    @Override

    protected int bindLayoutID() {
        return R.layout.fragment_item;
    }

    @Override
    protected void initView(View rootView) {
        currentType = getBundle();
        int layoutId = R.layout.item_xuan_hao1;
        if (currentType.equals("FC_SSQ")) {
            layoutId = R.layout.item_xuan_hao2;
        } else if (currentType.equals("FC_3D")) {
            layoutId = R.layout.item_xuan_hao1;
            //card_
        } else {
            layoutId = R.layout.item_xuan_hao;

        }

        core_recycle.init(new BaseQuickAdapter<VideosGson.DataListbean, BaseViewHolder>(layoutId) {


            public void setTextAndVi(TextView tv, String str) {

            }

            @Override
            protected void convert(BaseViewHolder helper, VideosGson.DataListbean item) {
//                x.image().bind(helper.getView(R.id.imageView), "http://www.runoob.com/wp-content/uploads/2015/08/41442282.jpg");
//                helper.setText(R.id.textView, item.kjznum + "  " + item.kjtnum);


                try {
                    helper.setText(R.id.textView0, "第 " + item.kjIssue + " 期  中奖号码");
                    helper.setText(R.id.textView8, item.kjdate);
                    if (TextUtils.isEmpty(item.kjtnum)) {
                        helper.setVisible(R.id.textView7, false);
                        helper.setText(R.id.textView7, item.kjtnum);

                    } else {
                        helper.setVisible(R.id.textView7, true);
                        helper.setText(R.id.textView7, item.kjtnum);
                    }

                    helper.setText(R.id.textView1, item.kjznum.split(" ")[0]);
                    helper.setText(R.id.textView2, item.kjznum.split(" ")[1]);
                    helper.setText(R.id.textView3, item.kjznum.split(" ")[2]);
                    helper.setText(R.id.textView4, item.kjznum.split(" ")[3]);
                    helper.setText(R.id.textView5, item.kjznum.split(" ")[4]);
                    helper.setText(R.id.textView6, item.kjznum.split(" ")[5]);
                } catch (Exception e) {
//                    Log.i(TAG, "convert: " + e.getMessage());
                    e.printStackTrace();
                }


                /**
                 * kjIssue : 2017066
                 * kjdate : 2017/06/08
                 * kjznum : 01 04 06 17 19 26
                 * kjtnum : 03
                 * mlist : [{"mname":"本期销量：329,821,920元"},{"mname":"奖池累计：624,968,496元"}]
                 * bonus : [{"zname":"一等奖","znum":"8","money":"7075823"},
                 * {"zname":"二等奖","znum":"103","money":"201536"},
                 * {"zname":"三等奖","znum":"1364","money":"3000"},
                 * {"zname":"四等奖","znum":"74828","money":"200"},
                 * {"zname":"五等奖","znum":"1364595","money":"10"},
                 * {"zname":"六等奖","znum":"9175247","money":"5"}]
                 */


            }
        }).openRefresh()
                .openLoadMore(20, page -> {
                    currentPage = page;
                    isFirst = true;
                    loadData();

                })
        ;


    }

    private static final String TAG = "ItemFragment";

    @Override
    protected void loadData() {


        if (!mIsVisible || !mIsPrepared || !isFirst) {
            Log.e(TAG, "不加载数据 mIsVisible=" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
            return;
        } else {
            Log.e(TAG, "加载数据" + mIsVisible + "  mIsPrepared=" + mIsPrepared + " isFirst = " + isFirst);
        }
        D.i("======loadData=========" + this);
        core_recycle.selfRefresh(true);
        RequestParams params = new RequestParams(getUrl(currentType, currentPage));
//        x.http().get(params, new Callback.CacheCallback<String>() {
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
//                showSnackBar("succeed\n" + result);
                processJson(result);
                return true;
            }

            @Override
            public void onSuccess(String result) {

                processJson(result);
//                showSnackBar("succeed\n" + result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                isFirst = false;
                core_recycle.selfRefresh(false);
            }
        });


    }


    public void processJson(String json) {
        videosGson = GsonUtil.formateJson2Bean(json, VideosGson.class);
        core_recycle.getAdapter().addData(videosGson.dataList);

    }


    public String getUrl(String type, int page) {
        Log.d(TAG, "getUrl: " + currentType);
        return "http://m.zhcw.com/clienth5.do?lottery=" + type + "&pageSize=20&pageNo=" + page + "&transactionType=300301&src=0000100001%7C6000003060";
    }
    /**
     * 我的手机  下午 2:24:18
     http://m.zhcw.com/clienth5.do?lottery=FC_3D&pageSize=20&pageNo=2&transactionType=300301&src=0000100001%7C6000003060
     我的手机  下午 2:24:26
     福彩3D
     我的手机  下午 2:25:12
     http://m.zhcw.com/clienth5.do?lottery=FC_QLC&pageSize=20&pageNo=2&transactionType=300301&src=0000100001%7C6000003060
     */


}

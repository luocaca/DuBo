package shishicai.com.dubo.ui.hot;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.adapter.OneAdapter;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.util.GlideImageLoader;
import shishicai.com.dubo.weidet.BaseQuickAdapter;

/**
 * 个人中心界面  用于检查更新，查看版本信息 已经推送中心
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060   //头条
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300205&src=0000100001%7C6000003060   //彩讯
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300207&src=0000100001%7C6000003060   //公益
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300209&src=0000100001%7C6000003060   // 视频
 * <p>
 * <p>
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060   // 政策
 * <p>
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300211&src=0000100001%7C6000003060  //专题
 */

public class HotFragment extends BaseFragment {

    /**
     * recycle 显示多个界面
     */


    @ViewInject(R.id.rvToDoList)
    RecyclerView rvToDoList;


    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_hot;
    }


    @Override
    protected void initView(final View rootView) {
//        rvToDoList = rootView.findViewById(R.id.rvToDoList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        rvToDoList.setLayoutManager(gridLayoutManager);
        rvToDoList.setItemAnimator(new DefaultItemAnimator());
        setDatas();
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.initView(inflater, container, savedInstanceState);

    }

    OneAdapter myAdapter;
    List<ScoreType> scoreTypes;

    public void setDatas() {


        getRandomImages();



        if (scoreTypes == null) {
            scoreTypes = new ArrayList<>();
        }
        for (int i = 0; i < 1; i++) {
            scoreTypes.add(ScoreType.竞足_半全场);
            scoreTypes.add(ScoreType.竞足_单关);
            scoreTypes.add(ScoreType.竞足_比分);
            scoreTypes.add(ScoreType.竞足_混合过关);
            scoreTypes.add(ScoreType.竞足_胜平均_让球);
            scoreTypes.add(ScoreType.竞足_进球数);

            scoreTypes.add(ScoreType.竞篮_单关投注);
            scoreTypes.add(ScoreType.竞篮_大小分);
            scoreTypes.add(ScoreType.竞篮_混合过关);
            scoreTypes.add(ScoreType.竞篮_胜分差);
            scoreTypes.add(ScoreType.竞篮_胜负);
            scoreTypes.add(ScoreType.竞篮_让分胜负);


            scoreTypes.add(ScoreType.体彩_七星彩);
            scoreTypes.add(ScoreType.体彩_体彩大乐透);
            scoreTypes.add(ScoreType.体彩_体彩排列三);
            scoreTypes.add(ScoreType.体彩_体彩排列五);


            scoreTypes.add(ScoreType.足彩_足彩4场进球);
            scoreTypes.add(ScoreType.足彩_足彩6场半全场);
            scoreTypes.add(ScoreType.足彩_足彩任选9场);
            scoreTypes.add(ScoreType.足彩_足彩胜负);


            scoreTypes.add(ScoreType.高频_湖南幸运赛车);
            scoreTypes.add(ScoreType.高频_快开十一选五);


        }


        if (myAdapter == null) {
            myAdapter = new OneAdapter();
            myAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

            addHeaderView();
            addFootView();
            rvToDoList.setAdapter(myAdapter);
            myAdapter.addData(scoreTypes);
        } else {
            myAdapter.addData(scoreTypes);
//            myAdapter.notifyDataSetChanged();
        }


    }

    private void getRandomImages() {


//        mImages.add("http://img.500.com/upimages/ad/201801/20180119163642_9946.jpg");
//        mImages.add("http://img.500.com/upimages/sfc/201801/20180115151647_8177.jpg");
//        mImages.add("http://img.500.com/upimages/sfc/201801/20180116174902_6717.jpg");
        mImages.add("https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0a9f67bc16950a7b6a3548c43ad0625c/c8ea15ce36d3d539f09733493187e950342ab095.jpg");
        mImages.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4269230302,1228482053&fm=27&gp=0.jpg");
        mImages.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4261164697,911028841&fm=27&gp=0.jpg");
        mImages.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1757478900,994367414&fm=27&gp=0.jpg");




    }

    private List<String> mImages = new ArrayList<>();
    private View headView;

    private void addHeaderView() {
        if (mImages != null && mImages.size() > 0) {
            headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, (ViewGroup) rvToDoList.getParent(), false);
            Banner banner = (Banner) headView.findViewById(R.id.banner);
            banner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(5000)
                    .start();
            myAdapter.addHeaderView(headView);
//            ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
//            ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
//            bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());
        }
    }

    private void addFootView() {
        if (mImages != null && mImages.size() > 0) {
            headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, (ViewGroup) rvToDoList.getParent(), false);
            Banner banner = (Banner) headView.findViewById(R.id.banner);
            banner.setImages(mImages)
                    .setImageLoader(new GlideImageLoader())
                    .setDelayTime(5000)
                    .start();
            myAdapter.addFooterView(headView);

        }
    }

    public static enum ScoreType {

        竞足_胜平均_让球("竞足-胜平均/让球", R.mipmap.jz, "http://trade.500.com/jczq/"),
        竞足_混合过关("竞足-混合过关", R.mipmap.jz, "http://trade.500.com/jczq/index.php?playid=312"),
        竞足_比分("竞足-比分", R.mipmap.jz, "http://trade.500.com/jczq/index.php?playid=271"),
        竞足_进球数("竞足-进球数", R.mipmap.jz, "http://trade.500.com/jczq/index.php?playid=270"),
        竞足_半全场("竞足-半全场", R.mipmap.jz, "http://trade.500.com/jczq/index.php?playid=272"),
        竞足_单关("竞足-单关", R.mipmap.jz, "http://trade.500.com/jczq/dgtz.php"),


        竞篮_单关投注("竞篮_单关投注", R.mipmap.jl, "http://trade.500.com/jclq/index.php?playid=275&g=1"),
        竞篮_让分胜负("竞篮_让分胜负", R.mipmap.jl, "http://trade.500.com/jclq/index.php?playid=275"),
        竞篮_胜负("竞篮_胜负", R.mipmap.jl, "http://trade.500.com/jclq/"),
        竞篮_胜分差("竞篮_胜分差", R.mipmap.jl, "http://trade.500.com/jclq/index.php?playid=276"),
        竞篮_大小分("竞篮_大小分", R.mipmap.jl, "http://trade.500.com/jclq/index.php?playid=277"),
        竞篮_混合过关("竞篮_混合过关", R.mipmap.jl, "http://trade.500.com/jclq/index.php?playid=313"),

        /**
         * <p class="sub_lottery">
         * <a href="http://trade.500.com/dlt/" target="_blank"  title="体彩大乐透">大乐透</a>
         * <a href="http://trade.500.com/qxc/" target="_blank"  title="七星彩">七星彩</a>
         * <a href="http://trade.500.com/pls/" target="_blank" title="体彩排列三">排列三</a>
         * <a href="http://trade.500.com/plw/" target="_blank" title="体彩排列五">排列五</a>
         * </p>
         */


        体彩_体彩大乐透("体彩-体彩大乐透", R.mipmap.tc, "http://trade.500.com/dlt/"),
        体彩_七星彩("体彩-七星彩", R.mipmap.tc, "http://trade.500.com/qxc/"),
        体彩_体彩排列三("体彩-体彩排列三", R.mipmap.tc, "http://trade.500.com/pls/"),
        体彩_体彩排列五("体彩-体彩排列五", R.mipmap.tc, "http://trade.500.com/plw/"),


        /**
         * <p class="sub_lottery">
         * <a href="http://trade.500.com/sfc/" target="_blank" title="足彩胜负">足彩胜负</a>
         * <a href="http://trade.500.com/rcjc/" target="_blank" title="足彩任选9场">任选<span class="eng">9场</span></a>
         * <a href="http://trade.500.com/jq4/" target="_blank" title="足彩4场进球"><span class="eng">4</span>场进球</a>
         * <a href="http://trade.500.com/zc6/" target="_blank"  title="足彩6场半全场"><span class="eng">6</span>场半全场</a>
         * </p>
         */

        足彩_足彩胜负("足彩-足彩胜负", R.mipmap.zc, "http://trade.500.com/sfc/"),
        足彩_足彩任选9场("足彩-足彩任选9场", R.mipmap.zc, "http://trade.500.com/rcjc/"),
        足彩_足彩4场进球("足彩-足彩4场进球", R.mipmap.zc, "http://trade.500.com/jq4/"),
        足彩_足彩6场半全场("足彩-足彩6场半全场", R.mipmap.zc, "http://trade.500.com/zc6/"),


        /**
         * <a class="lottery_tit" href="http://jk.trade.500.com/dlc/" target="_blank" title="高频彩票11选5">
         * <span class="sysw"></span>
         * <h3>高频</h3>
         * </a>
         * <p class="sub_lottery sub_lottery_1row">
         * <a href="http://jk.trade.500.com/dlc/" target="_blank" title="快开型体育彩票十一选五"><span class="eng">11</span>选<span class="eng">5</span></a>
         * <a href="http://jk.trade.500.com/xysc/" target="_blank" title="湖南幸运赛车">幸运赛车</a>
         * </p>
         */


        高频_快开十一选五("高频-快开十一选五", R.mipmap.gp, "http://jk.trade.500.com/dlc/"),
        高频_湖南幸运赛车("高频-湖南幸运赛车", R.mipmap.gp, "http://jk.trade.500.com/xysc/"),;


        public String str, href;
        public int res;

        ScoreType(String str, int res, String href) {
            this.str = str;
            this.href = href;
            this.res = res;
        }
    }


}

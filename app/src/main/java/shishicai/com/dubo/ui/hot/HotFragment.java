package shishicai.com.dubo.ui.hot;

import android.view.View;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;

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
     * viewpager 显示多个界面
     *
     *
     */

    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_hot;
    }


    @Override
    protected void initView(final View rootView) {


    }


}

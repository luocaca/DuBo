package shishicai.com.dubo.ui.hot.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.model.HotNews;
import shishicai.com.dubo.ui.hot.child.CommunityFragment;
import shishicai.com.dubo.ui.hot.child.PolicyFragment;
import shishicai.com.dubo.ui.hot.child.SpecialFragment;
import shishicai.com.dubo.ui.hot.child.TopFragment;
import shishicai.com.dubo.ui.hot.child.TriolionFragment;
import shishicai.com.dubo.ui.hot.child.VideoFragment;

/**
 * 头条
 * http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060
 */

public class NewsFragment extends BaseFragment {


    @ViewInject(R.id.viewpager)
    ViewPager viewPager;

    @ViewInject(R.id.tab_layout)
    TabLayout tab_layout;


    List<String> titles = new ArrayList<String>();
//            * titles.add("头条"),
//     *titles.add("资讯");
//     *titles.add("公益");
//     *titles.add("视频");
//     *titles.add("政策");
//     *titles.add("专辑");
//     */

    public void initList() {


        titles.add("头条");
        titles.add("公益");
        titles.add("资讯");
        titles.add("政策");
        titles.add("专辑");
        titles.add("视频");

        fragments.add(new TopFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new TriolionFragment());
        fragments.add(new PolicyFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new VideoFragment());

    }


    List<BaseFragment> fragments = new ArrayList<BaseFragment>();


    List<HotNews.DataListBean> newsList = new ArrayList<>();


    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView(final View rootView) {
        initList();

        MyAdapter myAdapter = new MyAdapter(fragments, titles, getChildFragmentManager());

        viewPager.setOffscreenPageLimit(0);

        for (String title : titles) {
            tab_layout.addTab(tab_layout.newTab().setText(title));
        }
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(myAdapter);
        tab_layout.setupWithViewPager(viewPager);


    }


    private class MyAdapter extends FragmentPagerAdapter {

        List<BaseFragment> fragments;
        List<String> listTitles;


        public MyAdapter(List<BaseFragment> fragments, List<String> listTitles, FragmentManager fm) {
            super(fm);
            this.fragments = fragments;
            this.listTitles = listTitles;
        }


        @Override
        public Fragment getItem(int position) {
//            Fragment fragment = fragments.get(position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listTitles.get(position);
        }

//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;//官方推荐写法;
//        }
    }


}

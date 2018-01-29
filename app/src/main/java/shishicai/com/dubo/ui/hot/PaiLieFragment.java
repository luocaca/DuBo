package shishicai.com.dubo.ui.hot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.R;
import shishicai.com.dubo.adapter.ViewPagerAdapter;
import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.ui.hot.PaiLieChild.ItemFragment;

/**
 * 排列中奖号码  界面  第二个界面
 */

public class PaiLieFragment extends BaseFragment {


    @ViewInject(R.id.viewpager)
    ViewPager viewPager;

    @ViewInject(R.id.tab_layout)
    TabLayout tab_layout;


    List<String> titles = new ArrayList<String>();


    public void initList() {


        titles.add("双色球");
        titles.add("福彩3");
        titles.add("7彩乐");

        fragments.add(new ItemFragment());
        fragments.add(new ItemFragment());
        fragments.add(new ItemFragment());

    }


    List<BaseFragment> fragments = new ArrayList<BaseFragment>();



    public static PaiLieFragment newInstancse() {
        return new PaiLieFragment();
    }


    @Override
    protected int bindLayoutID() {
        return R.layout.fragment_pai_lie;
    }


    @Override
    protected void initView(final View rootView) {
        initList();


        ViewPagerAdapter myAdapter = new ViewPagerAdapter(fragments, titles, getChildFragmentManager());

        viewPager.setOffscreenPageLimit(3);

        for (String title : titles) {
            tab_layout.addTab(tab_layout.newTab().setText(title));
        }
        tab_layout.setTabMode(TabLayout.GRAVITY_CENTER);
        viewPager.setAdapter(myAdapter);
        tab_layout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onVisible() {
        super.onVisible();


    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.initView(inflater, container, savedInstanceState);

    }


}

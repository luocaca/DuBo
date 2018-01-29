package shishicai.com.dubo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import shishicai.com.dubo.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class ViewPagerAdapter  extends FragmentPagerAdapter {


    List<BaseFragment> fragments;
    List<String> listTitles;


    public ViewPagerAdapter(List<BaseFragment> fragments, List<String> listTitles, FragmentManager fm) {
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


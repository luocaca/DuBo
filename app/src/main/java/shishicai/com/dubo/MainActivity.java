package shishicai.com.dubo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.ui.CenterFragment;
import shishicai.com.dubo.ui.MeFragment;
import shishicai.com.dubo.ui.MeFragmentDetail;
import shishicai.com.dubo.ui.hot.HotFragment;
import shishicai.com.dubo.ui.hot.news.NewsFragment;
import shishicai.com.dubo.weidet.BottomNavigationViewEx;


public class MainActivity extends AppCompatActivity {


    private TextView mTextMessage;


    private LinearLayout container;

    private ViewPager viewPager;

    private FrameLayout content;

    private BottomNavigationViewEx navigation;


    private Activity macActivity;
    FragmentTransaction ft;

    BaseFragment mefragment;
    BaseFragment mefragment1;
    BaseFragment mefragment2;
    BaseFragment mefragment3;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    ft.replace(R.id.content, mefragment2).commit();
//                    ft.replace(R.id.content, mefragment).commit();
                    setClick(0);
//                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    setClick(1);
                    return true;
                case R.id.navigation_notifications:
//                    macActivity.getFragmentManager()
//                    ft.show(mefragment2).commit();
                    setClick(2);

//                    ft.add(R.id.content, mefragment).commit();


                    return true;
                case R.id.design_navigation_good:
//                    macActivity.getFragmentManager()
//                    ft.show(mefragment2).commit();
                    setClick(3);

//                    ft.add(R.id.content, mefragment).commit();


                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);   //去除半透明状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);  //一般配合fitsSystemWindows()使用, 或者在根部局加上属性android:fitsSystemWindows="true", 使根部局全屏显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);
        macActivity = this;
        mFragmentManager = getSupportFragmentManager();
//        hideFragment(ft);
        setClick(0);
        mTextMessage = (TextView) findViewById(R.id.message);
        container = (LinearLayout) findViewById(R.id.container);
        content = (FrameLayout) findViewById(R.id.content);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);

    }


    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    private FragmentManager mFragmentManager;//fragment管理者

    private void setClick(int type) {
        //开启事务
        //开启事务
        ft = mFragmentManager.beginTransaction();
        //显示之前将所有的fragment都隐藏起来,在去显示我们想要显示的fragment
//        hideFragment(ft);
        //显示之前将所有的fragment都隐藏起来,在去显示我们想要显示的fragment
        hideFragment(ft);
        switch (type) {
            case 0://王超
                //如果王超的fragment是null的话,就创建一个
                if (mefragment == null) {
                    mefragment = new NewsFragment();
                    //加入事务
                    ft.add(R.id.content, mefragment);
                } else {
                    //如果王超fragment不为空就显示出来
                    ft.show(mefragment);
                }
                break;
            case 1:
                if (mefragment1 == null) {
//                    mefragment1 = new CenterFragment();
                    mefragment1 = new HotFragment();
                    //加入事务
                    ft.add(R.id.content, mefragment1);
                } else {
                    //如果王超fragment不为空就显示出来
                    ft.show(mefragment1);
                }
                break;
            case 2:
                if (mefragment2 == null) {
                    mefragment2 = new MeFragment();
                    //加入事务
                    ft.add(R.id.content, mefragment2);
                } else {
                    //如果王超fragment不为空就显示出来
                    ft.show(mefragment2);
                }

                break;
            case 3:
                if (mefragment3 == null) {
                    mefragment3 = new CenterFragment();
                    //加入事务
                    ft.add(R.id.content, mefragment3);
                } else {
                    //如果王超fragment不为空就显示出来
                    ft.show(mefragment3);
                }
                break;

        }
        //提交事务
        ft.commit();
    }


    /**
     * 用来隐藏fragment的方法
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (mefragment != null) {
            fragmentTransaction.hide(mefragment);
        }
        if (mefragment1 != null) {
            fragmentTransaction.hide(mefragment1);
        }
        if (mefragment2 != null) {
            fragmentTransaction.hide(mefragment2);
        }
        if (mefragment3 != null) {
            fragmentTransaction.hide(mefragment3);
        }

    }


    List<BaseFragment> baseFragments = new ArrayList<>();

    public void startFragment(String url) {

        BaseFragment baseFragment = new MeFragmentDetail().newInstances(url);
//        BaseFragment baseFragment = new TencentNewsFragment().newInstances(url);
        baseFragments.add(0, baseFragment);

        mFragmentManager.beginTransaction().addToBackStack("main")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .setCustomAnimations(
//                        R.animator.fragment_slide_right_in, R.anim.fragment_slide_left_out,
//                        R.anim.fragment_slide_left_in, R.anim.fragment_slide_right_out)
                .add(android.R.id.content, baseFragment).commit();
    }


    @Override
    public void onBackPressed() {

        if (baseFragments.size() == 0) {
            super.onBackPressed();
        } else {
            mFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).remove(baseFragments.get(0));
            baseFragments.remove(0);
            mFragmentManager.popBackStack();
        }

    }
}

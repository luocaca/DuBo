package shishicai.com.dubo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import shishicai.com.dubo.base.BaseFragment;
import shishicai.com.dubo.ui.CenterFragment;
import shishicai.com.dubo.ui.MeFragmentDetail;
import shishicai.com.dubo.ui.hot.PaiLieFragment;
import shishicai.com.dubo.ui.hot.TwoMeFragment;
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

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apks";
//        File file1 = new File(apkPath);
//        file1.mkdirs();
//        if (!file1.exists()) {
//            Toast.makeText(macActivity, file1.mkdirs() + "", Toast.LENGTH_SHORT).show();
//        }

//        FinalHttp finalHttp = new FinalHttp();
//        finalHttp.get("http://www.luocaca.cn/hello-ssm/book/allbook", null, new AjaxCallBack<String>() {
//            @Override
//            public void onSuccess(String json) {
//
//                Log.i(TAG, "onSuccess: " + json);
//            }
//
//            @Override
//            public void onFailure(Throwable t, int errorNo, String strMsg) {
//                super.onFailure(t, errorNo, strMsg);
//                Log.w(TAG, "onFailure: " + t.getMessage() + "  errorNo= " + errorNo + " strMsg = " + strMsg);
//            }
//        });


//        FinalHttp finalHttp = new FinalHttp();
//
//        finalHttp.download("http://www.luocaca.cn/hello-ssm/upload/绿叶vpn去广告黑框.apk", getFilesDir().getAbsolutePath() + File.separator + "vpn_luye.apk", new AjaxCallBack<File>() {
//
//            @Override
//            public void onLoading(long count, long current) {
//                super.onLoading(count, current);
//                Log.i(TAG, "onLoading:   " + current / 1024 + " / " + count / 1024);
//            }
//
//            @Override
//            public void onSuccess(File file) {
//                super.onSuccess(file);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        openFile(file);
//                    }
//                },0);
//                Log.i(TAG, "onLoading: ");
//            }
//
//            @Override
//            public void onFailure(Throwable t, int errorNo, String strMsg) {
//                super.onFailure(t, errorNo, strMsg);
//                Log.i(TAG, "onLoading: ");
//            }
//        });


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
                    mefragment1 = new PaiLieFragment();
                    //加入事务
                    ft.add(R.id.content, mefragment1);
                } else {
                    //如果王超fragment不为空就显示出来
                    ft.show(mefragment1);
                }
                break;
            case 2:
                if (mefragment2 == null) {
                    mefragment2 = new TwoMeFragment();
//                    mefragment2 = new MeFragment();
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

//打开APK程序代码

    private void openFile(File file) {
        String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apks";
        File filedir = new File(apkPath);
        if (!filedir.exists()) {
            filedir.mkdirs();
        }

        File file1 = new File(apkPath, "vpn.apk");
        File file2 = new File(apkPath, "vpn1.apk");
        if (file1!= null && file1.exists()) {
            file1.delete(); // delete file
        }

        if (!file1.exists())
        {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 if (!file2.exists())
        {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Log.i(TAG, "openFile: " + file.getAbsolutePath());

        copyFile(file.getAbsolutePath(), file2.getAbsolutePath());
        copyFile(file.getAbsolutePath(), file1.getAbsolutePath());

//        int byteread = 0;
////        if (!file1.exists()) {
////            Toast.makeText(macActivity, file1.mkdirs() + "", Toast.LENGTH_SHORT).show();
////        }
//        try {
//            InputStream inputStream = new FileInputStream(file);
//            FileOutputStream outputStream = new FileOutputStream(file1);
//            byte[] bytes = new byte[1024];
//            try {
//                while ((byteread = inputStream.read(bytes)) != -1) {
//                    outputStream.write(bytes, 0, byteread);
//                }
//                outputStream.close();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        // TODO Auto-generated method stub
        Log.e("OpenFile", file1.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file1),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

}

package shishicai.com.dubo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;

import shishicai.com.dubo.util.D;

public class SplashActivity extends AppCompatActivity {
    TextView countDown;

    private MyCountDownTimer timer;
    public int count = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);   //去除半透明状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);  //一般配合fitsSystemWindows()使用, 或者在根部局加上属性android:fitsSystemWindows="true", 使根部局全屏显示
        getWindow().setStatusBarColor(Color.TRANSPARENT);

//        try {
//            DecorView decordView = this.getWindow().getDecorView();     //获取DecorView实例
//            Field field = decordView.class.getDeclaredField("mSemiTransparentStatusBarColor");  //获取特定的成员变量
//            field.setAccessible(true);   //设置对此属性的可访问性
//            field.setInt(decordView, Color.TRANSPARENT);  //修改属性值
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//
        countDown = (TextView) findViewById(R.id.countDown);
//
//
        if (timer == null) {
            timer = new MyCountDownTimer(4200, 1000);
        }
        timer.start();


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MainActivity.start(SplashActivity.this);
//            }
//        }, 3000);


    }

    private void request() {

        //http://www.27305.com/frontApi/getAboutUs?appid=（appid）

        RequestParams params = new RequestParams("http://www.27305.com/frontApi/getAboutUs");

        params.addQueryStringParameter("appid", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                D.i(result);
//                WebActivity.start(SplashActivity.this);

                MainActivity.start(SplashActivity.this);
                finish();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                MainActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                MainActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void onFinished() {

            }
        });


    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long time = millisUntilFinished / 1000;

            countDown.setText("倒计时：" + (count--) + "");


            Log.i("onTick", "onTick: " + millisUntilFinished);
//            Log.i("onTick", "倒计时：" + (count--) + "");
        }

        @Override
        public void onFinish() {
            cancelTimer();

            request();


        }
    }

    public void cancel(View view) {
        countDown.setText("倒计时结束  00:00");
        cancelTimer();
    }


    /**
     * 取消倒计时
     */
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }


}

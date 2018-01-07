package shishicai.com.dubo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

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


    /**
     * {
     * "appid": "1801021340",
     * "appname": "",
     * "isshowwap": "2",
     * "wapurl": "",
     * "status": 1,
     * "desc": "成功返回数据"
     * }
     */

    private void request() {

        //http://www.27305.com/frontApi/getAboutUs?appid=（appid）

        RequestParams params = new RequestParams("http://www.27305.com/frontApi/getAboutUs");

        /*华为 id*/
//        params.addQueryStringParameter("appid", "1801021340");

        /*360id*/
        params.addQueryStringParameter("appid", "1801021341");
        // 默认缓存存活时间, 单位:毫秒.(如果服务没有返回有效的max-age或Expires)
        params.setCacheMaxAge(1000 * 60 * 24 * 3);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

//
//                if (true)
//                {
//                    MainActivity.start(SplashActivity.this);
//                }
//              Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                D.i(result);
                String isshowwap = "2";
//                WebActivity.start(SplashActivity.this);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    isshowwap = jsonObject.getString("isshowwap");
                    D.i("==========接口解析成功====isshowwap===" + isshowwap);
                } catch (JSONException e) {

                    isshowwap = "1";
                    D.w("==========接口解析失败=====isshowwap===" + isshowwap);
                    e.printStackTrace();
                }
                if (isshowwap.equals("1")) {
                    WebActivity.start(SplashActivity.this);
                    finish();
                } else {
                    MainActivity.start(SplashActivity.this);
                    finish();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                MainActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
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

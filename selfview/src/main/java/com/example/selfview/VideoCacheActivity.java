package com.example.selfview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wechatsmallvideoview.SurfaceVideoViewCreator;

import java.io.File;
import java.lang.reflect.Field;

public class VideoCacheActivity extends AppCompatActivity {


    private SurfaceVideoViewCreator mSurfaceVideoViewCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_cache);


        ActivityCompat.requestPermissions(
                VideoCacheActivity.this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1
        );

        mSurfaceVideoViewCreator = new SurfaceVideoViewCreator(this, (RelativeLayout) findViewById(R.id.activity_main)) {
            @Override
            protected Activity getActivity() {
                return VideoCacheActivity.this;
            }

            @Override
            protected boolean setAutoPlay() {
                return true;
            }

            @Override
            protected int getSurfaceWidth() {
                return 0;
            }

            @Override
            protected int getSurfaceHeight() {
                return 1920;
            }

            @Override
            protected void setThumbImage(ImageView imageView) {

            }

            @Override
            protected String getSecondVideoCachePath() {
                final String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "selfview";
//                Log.i("CachePath", getDiskCachePath(VideoCacheActivity.this));
                Log.i("CachePath", mFilePath);
//                return getExternalCacheDir().getAbsolutePath() + File.separator + "video_cache";
                //  final String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "selfview";
                return null;
            }

            @Override
            protected String getVideoPath() {
                return "http://image.hmeg.cn/upload/video/201803/a174ff05641541a5ae401499370acbe4.mp4";
            }
        };

        mSurfaceVideoViewCreator.debugModel = true;
        mSurfaceVideoViewCreator.setUseCache(true);
//        mSurfaceVideoViewCreator.setUseCache(getIntent().getBooleanExtra("useCache",false));


    }

    private void init() {
    }

////右边按钮点击事件
//        jCameraView.setRightClickListener(new ClickListener() {
//            @Override
//            public void onClick() {
//
//            }
//        });


    public static void start(Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, VideoCacheActivity.class));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSurfaceVideoViewCreator.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSurfaceVideoViewCreator.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            Field field = SurfaceVideoViewCreator.class.getDeclaredField("isFinishDownload");
            field.setAccessible(true);
            field.setBoolean(mSurfaceVideoViewCreator, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mSurfaceVideoViewCreator.onDestroy();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        mSurfaceVideoViewCreator.onKeyEvent(event);/*声音的大小调节 */
        return super.dispatchKeyEvent(event);
    }


    /**
     * 获取cache路径
     *
     * @param context
     * @return
     */
    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    break;
//                }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}

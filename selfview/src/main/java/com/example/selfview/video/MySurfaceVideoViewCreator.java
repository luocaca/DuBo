package com.example.selfview.video;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wechatsmallvideoview.SurfaceVideoViewCreator;

import java.io.File;

/**
 *
 */

public class MySurfaceVideoViewCreator extends SurfaceVideoViewCreator {

    private Activity mActivity;

    public MySurfaceVideoViewCreator(Activity activity, ViewGroup container) {
        super(activity, container);
        mActivity = activity;
    }

    @Override
    protected Activity getActivity() {
        return mActivity;
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


    @Override
    public void onDestroy() {

        /* 销毁之前 判断是否 下载完毕。。或者  是否 */


        super.onDestroy();




    }


    /**
     *     private File videoFile = null;
     private boolean isUseCache = false;
     private boolean mNeedResume;
     private boolean isFinishDownload = false;
     */
}

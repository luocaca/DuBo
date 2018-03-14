package com.example.selfview.http.thread;

import android.app.Activity;
import android.widget.Toast;

import com.example.selfview.en.FileType;
import com.example.selfview.util.FileUtils;
import com.orhanobut.logger.Logger;

import net.tsz.afinal.bitmap.download.Downloader;
import net.tsz.afinal.bitmap.download.SimpleDownloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 传入  下载地址   进行 下载线程
 */

public class DownLoadRunnable implements Runnable {

    private String mdownLoadPath = "";
    private Downloader downloader;
    private String mFilePath;
    private Activity mActivity;


    public DownLoadRunnable(Activity mActivity, String url, String savePath) {
        this.mdownLoadPath = url;
        this.mFilePath = savePath;
        this.mActivity = mActivity;
        downloader = new SimpleDownloader();
    }


    @Override
    public void run() {
        Logger.i("========start down load ===========" + mdownLoadPath);

        byte[] mDownBytes = downloader.download(mdownLoadPath);

        if (mDownBytes != null) {


//                        byte[] mDownBytes = downloader.download("https://upload-images.jianshu.io/upload_images/4386836-d3d1157b9c2a3bd7.jpg");
            FileOutputStream outputStream = null;
            try {
                File newFile = new File(mFilePath, mdownLoadPath.substring(mdownLoadPath.lastIndexOf("/")));
                outputStream = new FileOutputStream(newFile, false);
                outputStream.write(mDownBytes);
                outputStream.close();
                Logger.i("========下载成功  =======length===" + mDownBytes.length / 1024);


                FileType tpe = FileUtils.getType(newFile.getAbsolutePath());
                if (tpe != null) {
                    Logger.i("-------FileUtils  getType ---------" + tpe.getValue() + "   " + tpe.getValue());
                } else {
                    Logger.i("---------未知类型-------");
                }


            } catch (FileNotFoundException e) {
                Logger.w("======FileNotFoundException======" + e.getMessage());
            } catch (IOException e) {
                Logger.w("=======IOException=====" + e.getMessage());
            }


            Logger.i("========end down load ===========");
            Toast.makeText(mActivity, "下载成功-----   " + mdownLoadPath, Toast.LENGTH_SHORT).show();


        }
    }
}

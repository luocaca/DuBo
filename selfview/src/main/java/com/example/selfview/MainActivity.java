package com.example.selfview;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.maventest.EsayVideoEditActivity;
import com.example.selfview.http.thread.DownLoadRunnable;
import com.example.selfview.http.thread.ThreadPoolUtil;
import com.example.selfview.views.MView;
import com.example.selfview.views.StepView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import net.tsz.afinal.bitmap.download.Downloader;
import net.tsz.afinal.bitmap.download.SimpleDownloader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MView my_view;

    private StepView mStepView;

    SeekBar seekBar2;

    private ImageButton ivButton;
    private ImageButton ivButton1;

    private Downloader downloader;

    private EditText et_bottom_path;
    private String mdownLoadPath;

    private ExecutorService downloadService;
    private byte[] html;

    private Button btn_test_video_cache;
    private int translatex;
    private int translatey;
    private Drawable src;
    private FloatingActionButton fab;
    private float dx;
    private float dy;
    private float downY;
    private float downX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        downloadService = ThreadPoolUtil.newFixThreadPool(1);
        Logger.addLogAdapter(new AndroidLogAdapter());


        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        mStepView = findViewById(R.id.step_view);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i(TAG, "onProgressChanged: ----" + progress);
                if (progress > 9) {
                    mStepView.setStep(3);
                } else if (progress > 4) {
                    mStepView.setStep(2);
                } else if (progress > 0) {
                    mStepView.setStep(1);
                } else if (progress == 0) {
                    mStepView.setStep(0);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Button btn_test_video_cache = (Button) findViewById(R.id.btn_test_video_cache);
        btn_test_video_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VideoCacheActivity.start(MainActivity.this);


            }
        });


        ivButton1 = (ImageButton) findViewById(R.id.ivButton);
        et_bottom_path = (EditText) findViewById(R.id.et_bottom_path);


        translatex = 0;
        translatey = 0;

        ivButton1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                Log.i(TAG, "before: \n .getRawX()=" + event.getRawX() + "  .getRawY()" + event.getRawY() + " .getX()= " + ivButton1.getX() + " .getY() " + ivButton1.getY());


                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Log.i(TAG, "ACTION_DOWN: " + event.getX() + "  " + event.getY() + "  " + ivButton1.getX() + "  " + ivButton1.getY() + "  " + event.getRawX() + "  " + event.getRawY());
                    dx = event.getX();
                    dy = event.getY();

                    downX = ivButton1.getX();
                    downY = ivButton1.getY();


//                    ivButton1.setX(event.getRawX() - 315 / 2);
//                    ivButton1.setY(event.getRawY() - 315 / 2);
//                    src = ivButton1.getDrawable();
//                    ivButton1.setImageDrawable(tintDrawable(src, ColorStateList.valueOf(getRandomColor())));
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    ivButton1.setX(event.getRawX() - dx);
                    ivButton1.setY(event.getRawY() - dy);
                    Log.i(TAG, "ACTION_MOVE: event getx y " + event.getX() + "  " + event.getY());


                    Log.i(TAG, "ivButton1: getX y  " + ivButton1.getX() + "  " + ivButton1.getY());
                    Log.i(TAG, "ivButton1: getLeft r " + ivButton1.getLeft() + "  " + ivButton1.getTop());
                    /*  get  */

                    src = ivButton1.getDrawable();
                    ivButton1.setImageDrawable(tintDrawable(src, ColorStateList.valueOf(getRandomColor())));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    ivButton1.setX(event.getRawX() - ivButton1.getX());
//                    ivButton1.setY(event.getRawY() - ivButton1.getY());


                    Log.i(TAG, "+ event.getX()= " + event.getX());
                    Log.i(TAG, (ivButton1.getX() - downX) + " ");
                    Log.i(TAG, ivButton1.getX() + "");
                    Log.i(TAG, (downX) + " ");

                    if (Math.abs(ivButton1.getX() - downX) > 5) {
                        //滑动操作
                        Log.i(TAG, "onTouch: 滑动");

                    } else if (Math.abs(ivButton1.getY() - downY) > 5) {
                        Log.i(TAG, "onTouch: 滑动");
                    } else {
                        // 点击操作
                        AnimationActivity.start(MainActivity.this);
                        Log.i(TAG, "onTouch: 点击");


                    }


                }


//                Log.i(TAG, "after: \n .getRawX()=" + event.getRawX() + "  .getRawY()" + event.getRawY() + " .getX()= " + ivButton1.getX() + " .getY() " + ivButton1.getY());
//                Log.i(TAG, "ivButton1 location" + ivButton1.getLeft() + " " + ivButton1.getTop() + "  " + ivButton1.getRight() + "  " + ivButton1.getBottom());

                return true;

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "ivButton1 location" + ivButton1.getLeft() + " " + ivButton1.getTop() + "  " + ivButton1.getRight() + "  " + ivButton1.getBottom() + "  width = " + ivButton1.getWidth() + "  height = " + ivButton1.getHeight());
            }
        }, 1000);
        Log.i(TAG, "ivButton1 location" + ivButton1.getLeft() + " " + ivButton1.getTop() + "  " + ivButton1.getRight() + "  " + ivButton1.getBottom() + "  width = " + ivButton1.getWidth() + "  height = " + ivButton1.getHeight());


        ivButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable src = ivButton1.getDrawable();
                ivButton1.setImageDrawable(tintDrawable(src, ColorStateList.valueOf(getRandomColor())));

//                ivButton1.setTranslationX(translatex += 10);
//                ivButton1.setTranslationY(translatex += 10);
                ivButton1.setX(translatex += 10);
                ivButton1.setY(translatey += 10);


                AnimationActivity.start(MainActivity.this);


                if (true) {
                    return;
                }

                Toast.makeText(MainActivity.this, "开始下载文件-----   ", Toast.LENGTH_SHORT).show();

                mdownLoadPath = et_bottom_path.getText().toString();


                Logger
                        .i("---------增加文件--------");

                final String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "selfview";

                Logger.i("---------mFilePath--------" + mFilePath);

                File file = new File(mFilePath);

                Logger.i("------file.exists()----------" + file.exists());

                if (!file.exists()) {
                    Logger.i("======file.mkdirs()======" + file.mkdirs());
                }

                downloader = new SimpleDownloader();
//                FinalHttp.getUrlWithQueryString()


//                /* 启用线程池来进行下载    */
//                Logger.i("========start 启用线程池来进行下载===========");
//
////                ThreadPoolUtil.newFixThreadPool(1).execute(new DownLoadRunnable(
//                downloadService.execute(new DownLoadRunnable(
//                        MainActivity.this,
//                        mdownLoadPath,
//                        mFilePath
//                ));
//                downloadService.execute(new DownLoadRunnable(
//                        MainActivity.this,
//                        "http://image.hmeg.cn/upload/image/201803/89cd7a78539944b9991b9d8bc0693793.png"
//                        , mFilePath
//                ));
//                downloadService.execute(new DownLoadRunnable(
//                        MainActivity.this,
//                        "http://image.hmeg.cn/upload/image/201803/1ee16a895d324b42a4f3b99b9575af5b.jpg"
//                        , mFilePath
//                ));
//                downloadService.execute(new DownLoadRunnable(
//                        MainActivity.this,
//                        "http://images.hmeg.cn/upload/image/201802/eddda27497194f17b6ef82d70fbe53c3.jpg"
//                        , mFilePath
//                ));
//                downloadService.execute(new DownLoadRunnable(
//                        MainActivity.this,
//                        "http://image.hmeg.cn/upload/image/201803/0665c85fbc6a4522940d97810cd25a26.jpeg"
//                        , mFilePath));


//                byte[] html = downloader.download("http://www.quanjing.com/");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        html = downloader.download("http://hmeg.cn/seedling");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                StringBuffer stringBuffer
                                        = new StringBuffer();
                                int lenght = -1;
                                try {
//                    InputStream inputStream = getAssets().open("images.html");
                                    InputStream inputStream = new ByteArrayInputStream(html);


                                    byte[] bytes = new byte[1024];

                                    while ((lenght = inputStream.read(bytes)) != -1) {
                                        Logger.i("--------读取文字-lenght----------" + lenght + new String(bytes));
                                        stringBuffer.append("\n" + new String(bytes));
                                    }

                                    Logger.i("--------文档文字 ---- ----------" + stringBuffer);


                                    List<String> stringList = getImageSrc("http://www.quanjing.com/", stringBuffer.toString());

                                    Logger.i("======stringList====" + stringList.size());

                                    for (String s : stringList) {
                                        downloadService.execute(new DownLoadRunnable(MainActivity.this, s, mFilePath));
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                    }
                }).start();

//
//
//                StringBuffer stringBuffer
//                        = new StringBuffer();
//                int lenght = -1;
//                try {
////                    InputStream inputStream = getAssets().open("images.html");
//                    InputStream inputStream = new ByteArrayInputStream(html);
//
//
//                    byte[] bytes = new byte[1024];
//
//                    while ((lenght = inputStream.read(bytes)) != -1) {
//                        Logger.i("--------读取文字-lenght----------" + lenght + new String(bytes));
//                        stringBuffer.append("\n" + new String(bytes));
//                    }
//
//                    Logger.i("--------文档文字 ---- ----------" + stringBuffer);
//
//
//                    List<String> stringList = getImageSrc("http://www.quanjing.com/", stringBuffer.toString());
//
//                    Logger.i("======stringList====" + stringList.size());
//
//                    for (String s : stringList) {
//                        downloadService.execute(new DownLoadRunnable(MainActivity.this, s, mFilePath));
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//

//
//                ThreadPoolUtil.newFixThreadPool(1).execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Logger.i("========start down load ===========");
//
//                        byte[] mDownBytes = downloader.download(mdownLoadPath);
////                        byte[] mDownBytes = downloader.download("https://upload-images.jianshu.io/upload_images/4386836-d3d1157b9c2a3bd7.jpg");
//                        FileOutputStream outputStream = null;
//                        try {
//                            File newFile = new File(mFilePath, mdownLoadPath.substring(mdownLoadPath.lastIndexOf("/")));
//                            outputStream = new FileOutputStream(newFile, false);
//                            outputStream.write(mDownBytes);
//                            outputStream.close();
//                            Logger.i("========下载成功  =======length===" + mDownBytes.length / 1024);
//
//
//                            FileType tpe = FileUtils.getType(newFile.getAbsolutePath());
//                            if (tpe != null) {
//                                Logger.i("-------FileUtils  getType ---------" + tpe.getValue() + "   " + tpe.getValue());
//                            } else {
//                                Logger.i("---------未知类型-------");
//                            }
//
//
//                        } catch (FileNotFoundException e) {
//                            Logger.w("======FileNotFoundException======" + e.getMessage());
//                        } catch (IOException e) {
//                            Logger.w("=======IOException=====" + e.getMessage());
//                        }
//
//
//                        Logger.i("========end down load ===========");
//                        Toast.makeText(MainActivity.this, "下载成功-----   " + mdownLoadPath, Toast.LENGTH_SHORT).show();
//
//
//                    }
//                });

//                List<String> imageSrcList = new ArrayList<>();
//
//                Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\\\")?([^'\\\"\\n\\r\\f>]+(\\.png)\\b)[^>]*>");
//                //  <img src="/image/2017index/rm4.png?v=001 " width="297 " height="215 ">
//                Matcher m = p.matcher(stringBuffer);
//
//                String quote = null;
//                String src1 = null;
//                while (m.find()) {
//                    quote = m.group(1);
//
//                    // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
//                    src1 = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
//                    imageSrcList.add(src1);
//
//                }


//  Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
                Logger.i("========  end   结束线程池来进行下载===========");
                /* 启用线程池来进行下载    */


//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Logger.i("========start down load ===========");
//
//                        byte[] mDownBytes = downloader.download(mdownLoadPath);
////                        byte[] mDownBytes = downloader.download("https://upload-images.jianshu.io/upload_images/4386836-d3d1157b9c2a3bd7.jpg");
//                        FileOutputStream outputStream = null;
//                        try {
//                            File newFile = new File(mFilePath, mdownLoadPath.substring(mdownLoadPath.lastIndexOf("/")));
//                            outputStream = new FileOutputStream(newFile, false);
//                            outputStream.write(mDownBytes);
//                            outputStream.close();
//                            Logger.i("========下载成功  =======length===" + mDownBytes.length / 1024);
//
//
//                            FileType tpe = FileUtils.getType(newFile.getAbsolutePath());
//                            if (tpe != null) {
//                                Logger.i("-------FileUtils  getType ---------" + tpe.getValue() + "   " + tpe.getValue());
//                            } else {
//                                Logger.i("---------未知类型-------");
//                            }
//
//
//                        } catch (FileNotFoundException e) {
//                            Logger.w("======FileNotFoundException======" + e.getMessage());
//                        } catch (IOException e) {
//                            Logger.w("=======IOException=====" + e.getMessage());
//                        }
//
//
//                        Logger.i("========end down load ===========");
//                        Toast.makeText(MainActivity.this, "下载成功-----   " + mdownLoadPath, Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }).start();


            }
        });


        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, progress + "");
                my_view.setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch: ");
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//                my_view.setVisibility(my_view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//
//
//                VideoActivity.start(MainActivity.this);

                String video = Environment.getExternalStorageDirectory().getPath() + File.separator
                        + "myvideos" + File.separator + "a1.mp4";

                if (new File(video).exists()) {
                    Log.i(TAG, "存在此视频: ");
                } else {
                    Log.w(TAG, "不 存在此视频: ");
                }

                Intent intent1 = new Intent();
                intent1.putExtra(EsayVideoEditActivity.PATH, video);
                intent1.setClass(MainActivity.this, EsayVideoEditActivity.class);
                startActivity(intent1);


            }
        });


        my_view = (MView) findViewById(R.id.my_view);

//        my_view.setVisibility(View.VISIBLE);

//        my_view.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                my_view.setVisibility(View.VISIBLE);
//            }
//        }, 3000);
//        new Handler().postDelayed(new ,1000);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


    public int getRandomColor() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;


        return Color.parseColor("#" + r + g + b);
    }


    public static List<String> getImageSrc(String head, String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);

            // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);

            imageSrcList.add(head + src);

            Logger.w("=======url=========" + head + src);


        }
        return imageSrcList;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}

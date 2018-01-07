package shishicai.com.dubo.app;

import android.app.Application;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 *
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}

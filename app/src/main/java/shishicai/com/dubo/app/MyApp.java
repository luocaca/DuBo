package shishicai.com.dubo.app;

import android.support.multidex.MultiDexApplication;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 *
 */

public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}

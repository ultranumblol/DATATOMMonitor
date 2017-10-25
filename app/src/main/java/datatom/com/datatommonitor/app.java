package datatom.com.datatommonitor;

import android.app.Application;


import datatom.com.datatommonitor.Util.HttpUtil;

import wgz.datatom.com.utillibrary.util.LogUtil;
import wgz.datatom.com.utillibrary.util.ToastUtil;

/**
 * Created by wgz on 2017/7/25.
 */

public class app extends Application {
    private static app mApp;

    public static app getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        ToastUtil.isShow = true;
        LogUtil.isDebug = true;
        HttpUtil.Companion.getInstance().setContext(getApplicationContext());

        //GreenDaoManager.getInstance();

    }


}
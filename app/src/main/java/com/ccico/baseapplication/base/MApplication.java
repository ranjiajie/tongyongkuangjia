package com.ccico.baseapplication.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import androidx.multidex.MultiDex;

import com.bun.miitmdid.core.JLibrary;

import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;

public class MApplication extends Application {
    private static MApplication instance;
    private static String versionName;
    private static int versionCode;

    public static MApplication getInstance() {
        return instance;
    }
    public static int getVersionCode() {
        return versionCode;
    }
    public static String getVersionName() {
        return versionName;
    }
    public static Resources getAppResources() {
        return getInstance().getResources();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer());
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = 0;
            versionName = "0.0.0";
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        JLibrary.InitEntry(this);

    }
}

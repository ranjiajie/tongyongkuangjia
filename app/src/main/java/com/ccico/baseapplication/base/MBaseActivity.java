package com.ccico.baseapplication.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ccico.baseapplication.R;
import com.ccico.basemvp.BaseActivity;
import com.ccico.basemvp.IPresenter;
import com.ccico.basemvp.bar.ImmersionBar;

import androidx.annotation.Nullable;

public abstract class MBaseActivity<T extends IPresenter> extends BaseActivity<T> {
    protected ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().getDecorView().setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS);
        }
        mImmersionBar = ImmersionBar.with(this);
        initImmersionBar();
    }
    @SuppressWarnings("NullableProblems")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 沉浸状态栏
     */
    protected void initImmersionBar() {
        try {
            View actionBar = findViewById(R.id.toolbar);
            if (isImmersionBarEnabled()) {
                if (actionBar != null && actionBar.getVisibility() == View.VISIBLE) {
                    mImmersionBar.statusBarColorInt(getResources().getColor(R.color.colorPrimary));
                } else {
                    mImmersionBar.transparentStatusBar();
                }
            } else {
                if (actionBar != null && actionBar.getVisibility() == View.VISIBLE) {
                    mImmersionBar.statusBarColorInt(getResources().getColor(R.color.colorPrimary));
                } else {
                    mImmersionBar.statusBarColor(R.color.grey_4);
                }
            }
        } catch (Exception ignored) {
        }
        try {
                mImmersionBar.statusBarDarkFont(true, 0.2f);
                mImmersionBar.navigationBarColor(R.color.black);
                mImmersionBar.navigationBarDarkFont(false);
            mImmersionBar
                    .fitsSystemWindows(true)
                    .init();
        } catch (Exception ignored) {
        }
    }
    /**
     * @return 是否沉浸
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }
    @Override
    public void finish() {
        super.finish();
    }
}

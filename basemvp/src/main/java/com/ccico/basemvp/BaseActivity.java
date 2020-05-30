package com.ccico.basemvp;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Toast;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public abstract class BaseActivity<T extends IPresenter> extends RxAppCompatActivity implements IView {
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;
    protected Bundle savedInstanceState;
    protected T mPresenter;
    protected boolean isRecreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        if(getIntent()!=null){
            isRecreate = getIntent().getBooleanExtra("isRecreate", false);
        }
        AppActivityManager.getInstance().add(this);
        initSDK();
        onCreateActivity();
        mPresenter = initInjector();
        attachView();
        initData();
        bindView();
        bindEvent();
        firstRequest();
    }

    /**
     * 首次逻辑操作
     */
    protected void firstRequest() {

    }

    /**
     * 事件触发绑定
     */
    protected void bindEvent() {

    }

    /**
     * 控件绑定
     */
    protected void bindView() {

    }

    /**
     * P层绑定V层
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * P层解绑V层
     */
    private void detachView() {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    /**
     * SDK初始化
     */
    protected void initSDK() {

    }

    /**
     * P层绑定   若无则返回null;
     */
    protected abstract T initInjector();

    /**
     * 布局载入  setContentView()
     */
    protected abstract void onCreateActivity();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        AppActivityManager.getInstance().remove(this);
    }

    @Override
    public void recreate() {
        getIntent().putExtra("isRecreate", true);
        super.recreate();
    }

    /////////Toast//////////////////

    public void toast(String msg) {
        toast(msg, Toast.LENGTH_SHORT, 0);
    }

    public void toast(String msg, int state) {
        toast(msg, Toast.LENGTH_LONG, state);
    }

    public void toast(int strId) {
        toast(strId, 0);
    }

    public void toast(int strId, int state) {
        toast(getString(strId), Toast.LENGTH_LONG, state);
    }


    private Toast toast;
    public void toast(String msg, int length, int state) {
        if (toast == null) {
            toast = Toast.makeText(this,
                    msg,
                    length);
        } else {
            toast.setText(msg);
        }
        try {
            if (state == SUCCESS) {
                toast.getView().getBackground().setColorFilter(getResources().getColor(R.color.success), PorterDuff.Mode.SRC_IN);
            } else if (state == ERROR) {
                toast.getView().getBackground().setColorFilter(getResources().getColor(R.color.error), PorterDuff.Mode.SRC_IN);
            }
        } catch (Exception ignored) {
        }
        toast.show();
    }

    public Context getContext(){
        return this;
    }


}
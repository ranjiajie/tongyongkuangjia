package com.ccico.baseapplication.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ccico.basemvp.BaseFragment;
import com.ccico.basemvp.IPresenter;
import com.ccico.basemvp.IView;

import androidx.annotation.Nullable;


public abstract class MBaseFragment<T extends IPresenter> extends BaseFragment<T> implements IView {
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = initInjector();
        attachView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(createLayoutId(), container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    /**
     * @return LayoutId
     */
    public abstract int createLayoutId();

    /**
     * P层绑定   若无则返回null;
     */
    protected abstract T initInjector();

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
            toast = Toast.makeText(getContext(),
                    msg,
                    length);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}

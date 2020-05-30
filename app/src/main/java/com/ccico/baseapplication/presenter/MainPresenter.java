package com.ccico.baseapplication.presenter;

import android.widget.Toast;

import com.ccico.baseapplication.model.MainModel;
import com.ccico.baseapplication.model.bean.PoetryBean;
import com.ccico.baseapplication.presenter.contrat.MainContrat;
import com.ccico.basemvp.BasePresenterImpl;
import com.ccico.basemvp.http.RxHelper;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainPresenter extends BasePresenterImpl<MainContrat.View> implements MainContrat.Presenter {

    private  MainModel mainModel;

    private MainPresenter() {
        mainModel = MainModel.getInstance();
    }
    public static MainPresenter getInstance(){
        return Inner.mainPresenter;
    }
    private static class Inner{
        private static final  MainPresenter mainPresenter=new MainPresenter();
    }

    @Override
    public void getData() {
        mainModel.getData()
                .compose(RxHelper.observableIO2Main(mView.getContext()))
                .subscribe(new Observer<PoetryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(PoetryBean poetryBean) {
                mView.updateView(poetryBean);
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(mView.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onComplete() {
            }
        });
    }
    @Override
    public void detachView() {

    }
}

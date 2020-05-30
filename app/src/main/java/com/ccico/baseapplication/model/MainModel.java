package com.ccico.baseapplication.model;

import com.ccico.baseapplication.model.bean.PoetryBean;
import com.ccico.baseapplication.presenter.contrat.MainContrat;
import com.ccico.basemvp.http.RetrofitUtils;

import io.reactivex.Observable;

public class MainModel implements MainContrat.Model {
    private MainModel() {
    }
    public static MainModel getInstance(){
        return Inner.instance;
    }
    private static class  Inner{
        private static final MainModel instance=new MainModel();
    }
    @Override
    public Observable<PoetryBean> getData() {
        return RetrofitUtils.getInstance().getRetrofitString(ApiUrl.class)
                .getPoetry();
    }
}

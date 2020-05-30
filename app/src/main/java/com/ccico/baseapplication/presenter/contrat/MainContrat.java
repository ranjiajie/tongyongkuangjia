package com.ccico.baseapplication.presenter.contrat;

import com.ccico.baseapplication.model.bean.PoetryBean;
import com.ccico.basemvp.IPresenter;
import com.ccico.basemvp.IView;

import io.reactivex.Observable;

public interface MainContrat {
    interface Model {
        Observable<PoetryBean> getData();
    }
    interface Presenter extends IPresenter{
        void getData();
    }
    interface View extends IView {
        /**
         * 更新UI
         */
        void updateView(PoetryBean bean);
    }
}

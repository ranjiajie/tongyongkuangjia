package com.ccico.baseapplication.view;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ccico.baseapplication.R;
import com.ccico.baseapplication.base.MBaseActivity;
import com.ccico.baseapplication.model.bean.PoetryBean;
import com.ccico.baseapplication.presenter.MainPresenter;
import com.ccico.baseapplication.presenter.contrat.MainContrat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MBaseActivity<MainContrat.Presenter> implements MainContrat.View {

    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_Content)
    TextView tv_Content;
    @Override
    protected MainContrat.Presenter initInjector() {
        return MainPresenter.getInstance();
    }
    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Override
    protected void initData() {
        mPresenter.getData();
    }
    @Override
    public void updateView(PoetryBean bean) {
        title.setText(bean.getOrigin());
        tv_Content.setText(bean.getAuthor()+"\r\n"+bean.getCategory()+"\r\n"+bean.getContent());
    }
}

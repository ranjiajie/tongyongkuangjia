# tongyongkuangjia
mvp+retrofit+rxjava+Butterknife+rxbus的一个基础框架

//RetrofitUtils为retrotfit的封装
public class RetrofitUtils {

    private Retrofit retrofit;
    //构造器私有，这个工具类只有一个实例
    private RetrofitUtils(){
        OkHttpClient.Builder clientBuilder=new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LogInterceptor());
        retrofit = new Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    /**
     * 静态内部类单例模式
     *
     * @return
     */
    public static RetrofitUtils getInstance() {
        return Instance.retrofitUtils;
    }
    private static class Instance{
        private static final RetrofitUtils retrofitUtils= new RetrofitUtils();
    }

    /**
     * 利用泛型传入接口class返回接口实例
     *
     * @param clzss 类
     * @param <T> 类的类型
     * @return Observable
     */
    public <T> T getRetrofitString(Class<T> clzss) {
        return retrofit.create(clzss);
    }
}

//通过RetrofitUtils.getInstance().getRetrofitString(ApiUrl.class)请求网络

//ApiUrl.class为接口类

RetrofitUtils.getInstance().getRetrofitString(ApiUrl.class)
                .getPoetry()//调用请求数据的方法
                .compose(RxHelper.observableIO2Main(mView.getContext())) //线程切换的封装
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


//mvp 简单的逻辑 

//model处理数据 我这里在model请求网络获取数据  
  private  MainModel mainModel;

    private MainPresenter() {
        mainModel = MainModel.getInstance();
    }
//在Presenter的私有构造中绑定model 在Presenter中通过model获取到网络请求的数据
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
                mView.updateView(poetryBean);//通过mView传递数据 到activity更新ui
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


//在activity中 为view 只作界面更新  与presenter绑定 
   @Override
    protected MainContrat.Presenter initInjector() {
        return MainPresenter.getInstance();
    }
    //告知Presenter获取数据 Presenter调用model中的网络请求获取到的数据
 @Override
    protected void initData() {
        mPresenter.getData();
    }
//Presenter中获取数据后 通过mView传递数据 到activity更新ui
     @Override
    public void updateView(PoetryBean bean) {
        title.setText(bean.getOrigin());
        tv_Content.setText(bean.getAuthor()+"\r\n"+bean.getCategory()+"\r\n"+bean.getContent());
    }
    


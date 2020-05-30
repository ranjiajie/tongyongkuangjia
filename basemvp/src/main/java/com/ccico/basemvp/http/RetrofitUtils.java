package com.ccico.basemvp.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ccico.basemvp.common.Common.BASE_URL;
import static com.ccico.basemvp.common.Common.CONNECT_TIME;
import static com.ccico.basemvp.common.Common.READ_TIME;
import static com.ccico.basemvp.common.Common.WRITE_TIME;

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

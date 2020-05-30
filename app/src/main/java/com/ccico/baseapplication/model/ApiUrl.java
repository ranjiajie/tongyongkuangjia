package com.ccico.baseapplication.model;


import com.ccico.baseapplication.model.bean.PoetryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiUrl {
//
//    //    菜品购买-下单-微信
//    @POST("iroad-service-sd/api/getLatelyData")
//    @FormUrlEncoded
//    Observable<JsonBean> queryjson(@Field("lon") String lon, @Field("lat") String lat, @Field("distance") String distance);

    /**
     * 获取古诗词
     *
     * @return 古诗词
     */
    @GET("all.json")
    Observable<PoetryBean> getPoetry();

}

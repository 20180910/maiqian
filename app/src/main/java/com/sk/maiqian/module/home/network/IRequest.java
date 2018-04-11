package com.sk.maiqian.module.home.network;

import com.library.base.ResponseObj;
import com.sk.maiqian.module.home.network.response.BannerObj;
import com.sk.maiqian.module.home.network.response.HomeDaYiJieHuoObj;
import com.sk.maiqian.module.home.network.response.HomeZiXunObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    //资讯列表
    @GET("api/MQHomePage/GetInformationList")
    Call<ResponseObj<List<HomeZiXunObj>>> getHomeZiXun(@QueryMap Map<String, String> map);

    //首页banner
    @GET("api/MQHomePage/GetRoastingChart")
    Call<ResponseObj<BannerObj>> getHomeBanner(@QueryMap Map<String, String> map);

    //答疑解惑
    @GET("api/MQHomePage/GetHomePageCenter")
    Call<ResponseObj<HomeDaYiJieHuoObj>> getDaYiJieHuo(@QueryMap Map<String, String> map);


    //答疑解惑列表
    @GET("api/MQHomePage/GetAnswerDoubtsList")
    Call<ResponseObj<List<HomeDaYiJieHuoObj.AnswerDoubtsListBean>>> getDaYiJieHuoList(@QueryMap Map<String, String> map);




}

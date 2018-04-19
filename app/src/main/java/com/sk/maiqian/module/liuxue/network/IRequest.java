package com.sk.maiqian.module.liuxue.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;
import com.sk.maiqian.module.liuxue.network.response.YouXueObj;
import com.sk.maiqian.module.youxue.network.response.YouXueDetailObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    //留学详情
    @GET("api/MQTravelStudyAbroad/GetVisaDetaile")
    Call<ResponseObj<YouXueDetailObj>> liuXueDetail(@QueryMap Map<String, String> map);

    //留学列表
    @GET("api/MQTravelStudyAbroad/GetStudyAbroadList")
    Call<ResponseObj<List<YouXueObj>>> getLiuXueList(@QueryMap Map<String, String> map);
    //游学列表-国家
    @GET("api/MQTravelStudyAbroad/GetCountrieRegionList")
    Call<ResponseObj<List<GuoJiaObj>>> getGuoJia(@QueryMap Map<String, String> map);
    //年级
    @GET("api/MQTravelStudyAbroad/GetClassList")
    Call<ResponseObj<List<GuoJiaObj>>> getNianJi(@QueryMap Map<String, String> map);
    //专业
    @GET("api/MQTravelStudyAbroad/GetMajorList")
    Call<ResponseObj<List<GuoJiaObj>>> getZhuanYe(@QueryMap Map<String, String> map);



}

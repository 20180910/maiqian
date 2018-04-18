package com.sk.maiqian.module.youxue.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.youxue.network.request.YouXueShenQingBody;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;
import com.sk.maiqian.module.youxue.network.response.YouXueDetailObj;
import com.sk.maiqian.module.youxue.network.response.YouXueObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    //游学列表
    @GET("api/MQTravelStudyAbroad/GetTravelList")
    Call<ResponseObj<List<YouXueObj>>> getYouXueList(@QueryMap Map<String, String> map);

    //游学列表-国家
    @GET("api/MQTravelStudyAbroad/GetCountrieRegionList")
    Call<ResponseObj<List<GuoJiaObj>>> getGuoJia(@QueryMap Map<String, String> map);

    //游学留学申请类别(1游学申请 2留学申请)
    @POST("api/MQTravelStudyAbroad/PostSubmitApplyFor")
    Call<ResponseObj<BaseObj>> youXueShenQing(@QueryMap Map<String, String> map, @Body YouXueShenQingBody body);

    //游学详情
    @GET("api/MQTravelStudyAbroad/GetVisaDetaile")
    Call<ResponseObj<YouXueDetailObj>> youXueDetail(@QueryMap Map<String, String> map);

}

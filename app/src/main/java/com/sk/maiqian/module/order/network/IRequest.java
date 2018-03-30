package com.sk.maiqian.module.order.network;

import com.library.base.ResponseObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {

    //查询模块-获取信用卡列表
    @GET("api/HandleCardOnline/GetPlanList")
    Call<ResponseObj<?>> getHuanKuanPlan(@QueryMap Map<String, String> map);



}

package com.sk.maiqian.module.order.network;

import com.library.base.ResponseObj;
import com.sk.maiqian.module.order.network.response.OrderDetailObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {

    //查询模块-获取信用卡列表
    @GET("api/MQUserBase/GetOrderDetail")
    Call<ResponseObj<OrderDetailObj>> getOrderDetail(@QueryMap Map<String, String> map);



}

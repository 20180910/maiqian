package com.sk.maiqian.module.home.network;

import com.library.base.ResponseObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    @GET("api/Informations/GetTypeAssemBlages")
    Call<ResponseObj<List<?>>> getHomeJiSuData(@QueryMap Map<String, String> map);




}

package com.sk.maiqian.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    @GET("api/HomePage/GetProductGroupOrderShow")
    Call<ResponseObj<BaseObj>> userLogin(@QueryMap Map<String, String> map);


}

package com.sk.maiqian.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.network.request.UploadImgBody;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface NetIRequest {
    @GET("api/MQLib/GetSMSCode")
    Call<ResponseObj<BaseObj>> getMsgCode(@QueryMap Map<String, String> map);

    //base64文件上传
    @POST("api/MQLib/PostUploadFileBase64")
    Call<ResponseObj<BaseObj>> uploadImg(@QueryMap Map<String, String> map, @Body UploadImgBody body);






}

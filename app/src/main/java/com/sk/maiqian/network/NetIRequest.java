package com.sk.maiqian.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.library.base.bean.AppVersionObj;
import com.library.base.bean.PayObj;
import com.sk.maiqian.module.my.network.response.BankNameObj;
import com.sk.maiqian.network.request.UploadImgBody;
import com.sk.maiqian.network.response.CityObj;
import com.sk.maiqian.network.response.ImageObj;
import com.sk.maiqian.network.response.ShareObj;

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

public interface NetIRequest {
    @GET("api/MQLib/GetSMSCode")
    Call<ResponseObj<BaseObj>> getMsgCode(@QueryMap Map<String, String> map);

    //base64文件上传
    @POST("api/MQLib/PostUploadFileBase64")
    Call<ResponseObj<BaseObj>> uploadImg(@QueryMap Map<String, String> map, @Body UploadImgBody body);

    //获取省市区
    @GET("api/MQLib/GetProvinceCityArea")
    Call<ResponseObj<List<CityObj>>> getAllArea(@QueryMap Map<String, String> map);

    //获取银行
    @GET("api/MQCashWithdrawal/GetBankList")
    Call<ResponseObj<List<BankNameObj>>> getBankList(@QueryMap Map<String, String> map);


    //获取所有城市
    @GET("api/MQLib/GetAllCity")
    Call<ResponseObj<List<CityObj>>> getAllCity(@QueryMap Map<String, String> map);


    //获取所有城市
    @GET("api/MQTravelStudyAbroad/GetTravelStudyAbroad")
    Call<ResponseObj<ImageObj>> getYouXueObj(@QueryMap Map<String, String> map);

    //获取支付url
    @GET("api/Lib/GetPayInfo")
    Call<ResponseObj<PayObj>> getPayNotifyUrl(@QueryMap Map<String,String> map);

    //app更新
    @GET("api/Lib/GetVersionUpdate")
    Call<ResponseObj<AppVersionObj>> getAppVersion(@QueryMap Map<String,String> map);

    //分享页面
    @GET("api/MQLib/GetShareInformation")
    Call<ResponseObj<ShareObj>> getShareInformation(@QueryMap Map<String,String> map);

}

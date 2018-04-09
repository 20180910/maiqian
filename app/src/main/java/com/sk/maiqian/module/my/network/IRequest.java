package com.sk.maiqian.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.my.network.request.AddBankBody;
import com.sk.maiqian.module.my.network.request.FanKuiBody;
import com.sk.maiqian.module.my.network.request.RegisterBody;
import com.sk.maiqian.module.my.network.response.FanKuiTypeObj;
import com.sk.maiqian.module.my.network.response.FenXiaoObj;
import com.sk.maiqian.module.my.network.response.LoginObj;
import com.sk.maiqian.module.my.network.response.MyAddressObj;
import com.sk.maiqian.module.my.network.response.MyAllBankObj;
import com.sk.maiqian.module.my.network.response.MyCollectionObj;
import com.sk.maiqian.module.my.network.response.MyMessageObj;

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
    //登录
    @GET("api/MQUserBase/GetUserLogin")
    Call<ResponseObj<LoginObj>> userLogin(@QueryMap Map<String, String> map);
    //注册
    @POST("api/MQUserBase/PostUserRegistration")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);

    //获取协议
    @GET("api/MQUserBase/GetUserAgreement")
    Call<ResponseObj<BaseObj>> getXieYi(@QueryMap Map<String, String> map);


    //重置密码(忘记密码)
    @GET("api/MQUserBase/GetSetPassword")
    Call<ResponseObj<BaseObj>> forgetPWD(@QueryMap Map<String, String> map);

    //消息开关
    @GET("api/MQUserBase/GetMessageSink")
    Call<ResponseObj<BaseObj>> setMessageSink(@QueryMap Map<String, String> map);

    //修改密码
    @GET("api/MQUserBase/GetSetNewPassword")
    Call<ResponseObj<BaseObj>> updatePWD(@QueryMap Map<String, String> map);

    //获取意见反馈类型
    @GET("api/MQUserBase/GetFeedbackType")
    Call<ResponseObj<List<FanKuiTypeObj>>> getFanKuiType(@QueryMap Map<String, String> map);


    //意见反馈
    @POST("api/MQUserBase/PostSubmitFeedback")
    Call<ResponseObj<BaseObj>> fanKui(@QueryMap Map<String, String> map, @Body List<FanKuiBody> body);

    //意见反馈
    @GET("api/MQUserBase/GetMyDistributionYard")
    Call<ResponseObj<FenXiaoObj>> getFenXiao(@QueryMap Map<String, String> map);


    //我的消息
    @GET("api/MQHomePage/GetNewsList")
    Call<ResponseObj<List<MyMessageObj>>> getMyMessage(@QueryMap Map<String, String> map);

    //我的收藏
    @GET("api/MQUserBase/GetMyCollection")
    Call<ResponseObj<List<MyCollectionObj>>> getMyCollection(@QueryMap Map<String, String> map);

    //取消收藏
    @GET("api/MQUserBase/GetDelMyCollection")
    Call<ResponseObj<BaseObj>> cancelMyCollection(@QueryMap Map<String, String> map);

    //添加地址
    @GET("api/MQCashWithdrawal/GetSaveUserAddress")
    Call<ResponseObj<BaseObj>> addAddress(@QueryMap Map<String, String> map);

    //获取地址
    @GET("api/MQCashWithdrawal/GetUserAddress")
    Call<ResponseObj<List<MyAddressObj>>> getAddress(@QueryMap Map<String, String> map);

    //删除地址
    @POST("api/MQCashWithdrawal/PostUserAddressDel")
    Call<ResponseObj<BaseObj>> deleteAddress(@QueryMap Map<String, String> map, @Body List<String> body);

    //添加银行卡
    @POST("api/MQCashWithdrawal/PostAddAccount")
    Call<ResponseObj<BaseObj>> addBank(@QueryMap Map<String, String> map, @Body AddBankBody body);

    //获取银行卡
    @GET("api/MQCashWithdrawal/GetAccount")
    Call<ResponseObj<List<MyAllBankObj>>> getBank(@QueryMap Map<String, String> map);

    //删除银行卡
    @GET("api/MQCashWithdrawal/GetDelAccount")
    Call<ResponseObj<BaseObj>> deleteBank(@QueryMap Map<String, String> map);

}

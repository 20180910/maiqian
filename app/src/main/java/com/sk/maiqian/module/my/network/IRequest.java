package com.sk.maiqian.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.my.network.request.FanKuiBody;
import com.sk.maiqian.module.my.network.request.RegisterBody;
import com.sk.maiqian.module.my.network.response.FanKuiTypeObj;
import com.sk.maiqian.module.my.network.response.LoginObj;

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


}

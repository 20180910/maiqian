package com.sk.maiqian.module.my.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.my.network.request.AddBankBody;
import com.sk.maiqian.module.my.network.request.DeleteAddressBody;
import com.sk.maiqian.module.my.network.request.EditNickNameBody;
import com.sk.maiqian.module.my.network.request.FanKuiBody;
import com.sk.maiqian.module.my.network.request.RegisterBody;
import com.sk.maiqian.module.my.network.response.DefaultBankObj;
import com.sk.maiqian.module.my.network.response.FanKuiTypeObj;
import com.sk.maiqian.module.my.network.response.FenXiaoObj;
import com.sk.maiqian.module.my.network.response.JiFenMingXiObj;
import com.sk.maiqian.module.my.network.response.JiFenObj;
import com.sk.maiqian.module.my.network.response.JiFenShuoMingObj;
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
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {
    //修改昵称
    @Headers("User-Agent:android")
    @POST("api/MQUserBase/PostEditUserInfo")
    Call<ResponseObj<BaseObj>> editNickName(@QueryMap Map<String, String> map, @Body EditNickNameBody body);

    //登录
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetUserLogin")
    Call<ResponseObj<LoginObj>> userLogin(@QueryMap Map<String, String> map);
    //注册
    @Headers("User-Agent:android")
    @POST("api/MQUserBase/PostUserRegistration")
    Call<ResponseObj<BaseObj>> register(@QueryMap Map<String, String> map, @Body RegisterBody body);

    //获取协议
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetUserAgreement")
    Call<ResponseObj<BaseObj>> getXieYi(@QueryMap Map<String, String> map);

    //重置密码(忘记密码)
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetSetPassword")
    Call<ResponseObj<BaseObj>> forgetPWD(@QueryMap Map<String, String> map);

    //消息开关
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetMessageSink")
    Call<ResponseObj<BaseObj>> setMessageSink(@QueryMap Map<String, String> map);

    //修改密码
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetSetNewPassword")
    Call<ResponseObj<BaseObj>> updatePWD(@QueryMap Map<String, String> map);

    //获取意见反馈类型
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetFeedbackType")
    Call<ResponseObj<List<FanKuiTypeObj>>> getFanKuiType(@QueryMap Map<String, String> map);


    //意见反馈
    @Headers("User-Agent:android")
    @POST("api/MQUserBase/PostSubmitFeedback")
    Call<ResponseObj<BaseObj>> fanKui(@QueryMap Map<String, String> map, @Body FanKuiBody body);

    //获取用户资料
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetUserInfo")
    Call<ResponseObj<LoginObj>> getUserInfo(@QueryMap Map<String, String> map);

    //意见反馈
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetMyDistributionYard")
    Call<ResponseObj<FenXiaoObj>> getFenXiao(@QueryMap Map<String, String> map);


    //我的消息
    @Headers("User-Agent:android")
    @GET("api/MQHomePage/GetNewsList")
    Call<ResponseObj<List<MyMessageObj>>> getMyMessage(@QueryMap Map<String, String> map);

    //我的收藏
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetMyCollection")
    Call<ResponseObj<List<MyCollectionObj>>> getMyCollection(@QueryMap Map<String, String> map);

    //取消收藏
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetDelMyCollection")
    Call<ResponseObj<BaseObj>> cancelMyCollection(@QueryMap Map<String, String> map);

    //添加地址
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetSaveUserAddress")
    Call<ResponseObj<BaseObj>> addAddress(@QueryMap Map<String, String> map);

    //获取地址
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetUserAddress")
    Call<ResponseObj<List<MyAddressObj>>> getAddress(@QueryMap Map<String, String> map);

    //删除地址
    @Headers("User-Agent:android")
    @POST("api/MQCashWithdrawal/PostUserAddressDel")
    Call<ResponseObj<BaseObj>> deleteAddress(@QueryMap Map<String, String> map, @Body DeleteAddressBody body);

    //添加银行卡
    @Headers("User-Agent:android")
    @POST("api/MQCashWithdrawal/PostAddAccount")
    Call<ResponseObj<BaseObj>> addBank(@QueryMap Map<String, String> map, @Body AddBankBody body);

    //获取银行卡
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetAccount")
    Call<ResponseObj<List<MyAllBankObj>>> getBank(@QueryMap Map<String, String> map);

    //删除银行卡
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetDelAccount")
    Call<ResponseObj<BaseObj>> deleteBank(@QueryMap Map<String, String> map);


    //积分说明
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetIntegralDescription")
    Call<ResponseObj<JiFenShuoMingObj>> getJiFenShuoMing(@QueryMap Map<String, String> map);

    //获取默认银行账户列表
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetAccountDefault")
    Call<ResponseObj<DefaultBankObj>> getDefaultAccount(@QueryMap Map<String, String> map);

    //提现
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetWithdrawals")
    Call<ResponseObj<JiFenObj>> tiXian(@QueryMap Map<String, String> map);

    //积分明细
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetMyBalance")
    Call<ResponseObj<List<JiFenMingXiObj>>> getJiFenMingXi(@QueryMap Map<String, String> map);

    //设置默认
    @Headers("User-Agent:android")
    @GET("api/MQCashWithdrawal/GetEditDefalut")
    Call<ResponseObj<BaseObj>> setDefault(@QueryMap Map<String, String> map);

    //修改手机号
    @Headers("User-Agent:android")
    @GET("api/MQUserBase/GetSetMobilePhone")
    Call<ResponseObj<BaseObj>> updatePhone(@QueryMap Map<String, String> map);



}

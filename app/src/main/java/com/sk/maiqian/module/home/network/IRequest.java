package com.sk.maiqian.module.home.network;

import com.library.base.BaseObj;
import com.library.base.ResponseObj;
import com.sk.maiqian.module.home.network.request.QianZhengLiuYanBody;
import com.sk.maiqian.module.home.network.response.BannerObj;
import com.sk.maiqian.module.home.network.response.CollectObj;
import com.sk.maiqian.module.home.network.response.EnglishPeiXunObj;
import com.sk.maiqian.module.home.network.response.HomeDaYiJieHuoObj;
import com.sk.maiqian.module.home.network.response.HomeZiXunObj;
import com.sk.maiqian.module.home.network.response.QianZhengDaiBanObj;
import com.sk.maiqian.module.home.network.response.QianZhengDetailObj;
import com.sk.maiqian.module.home.network.response.QianZhengObj;
import com.sk.maiqian.module.home.network.response.ShenQingRenObj;
import com.sk.maiqian.module.home.network.response.ZiLiaoMuBanObj;
import com.sk.maiqian.module.home.network.response.ZiXunDetailObj;
import com.sk.maiqian.module.home.network.response.ZiXunObj;

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
    //资讯列表
    @GET("api/MQHomePage/GetInformationList")
    Call<ResponseObj<List<HomeZiXunObj>>> getHomeZiXun(@QueryMap Map<String, String> map);

    //首页banner
    @GET("api/MQHomePage/GetRoastingChart")
    Call<ResponseObj<BannerObj>> getHomeBanner(@QueryMap Map<String, String> map);

    //答疑解惑
    @GET("api/MQHomePage/GetHomePageCenter")
    Call<ResponseObj<HomeDaYiJieHuoObj>> getDaYiJieHuo(@QueryMap Map<String, String> map);


    //答疑解惑列表
    @GET("api/MQHomePage/GetAnswerDoubtsList")
    Call<ResponseObj<List<HomeDaYiJieHuoObj.AnswerDoubtsListBean>>> getDaYiJieHuoList(@QueryMap Map<String, String> map);

    //签证代办-国家列表
    @GET("api/MQVisaAgent/GetCountrieRegion")
    Call<ResponseObj<List<QianZhengDaiBanObj>>> getQianZhengDaiBan(@QueryMap Map<String, String> map);

    //签证代办-列表
    @GET("api/MQVisaAgent/GetVisaList")
    Call<ResponseObj<QianZhengObj>> getQianZhengList(@QueryMap Map<String, String> map);

    //签证代办-留言
    @POST("api/MQLib/PostLeaveMessage")
    Call<ResponseObj<BaseObj>> qianZhengLiuYan(@QueryMap Map<String, String> map, @Body QianZhengLiuYanBody body);


    //签证代办-详情
    @GET("api/MQVisaAgent/GetVisaDetaile")
    Call<ResponseObj<QianZhengDetailObj>> qianZhengDetail(@QueryMap Map<String, String> map);

    //签证代办-收藏
    @GET("api/MQUserBase/GetCollectAll")
    Call<ResponseObj<CollectObj>> collect(@QueryMap Map<String, String> map);

    //签证代办-收藏
    @GET("api/MQLib/GetConsultingInformation")
    Call<ResponseObj<ZiXunObj>> getZiXunInfo(@QueryMap Map<String, String> map);

    //签证代办-快递列表
    @GET("api/MQVisaAgent/GetCourierList")
    Call<ResponseObj<List<String>>> getKuaiDiList(@QueryMap Map<String, String> map);

    //签证代办-订单-申请人
    @GET("api/MQVisaAgent/GetApplicantInformation")
    Call<ResponseObj<List<ShenQingRenObj>>> getShenQingRen(@QueryMap Map<String, String> map);

    //签证代办-订单-添加申请人
    @GET("api/MQVisaAgent/GetSaveUserAddress")
    Call<ResponseObj<BaseObj>> addShenQingRen(@QueryMap Map<String, String> map);


    //签证代办-资料模板
    @GET("api/MQVisaAgent/GetVisaInformation")
    Call<ResponseObj<List<ZiLiaoMuBanObj>>> getZiLiaoMuBan(@QueryMap Map<String, String> map);

    //英语培训-体验课
    @GET("api/MQEnglishTraining/GetEnglishTrainingList")
    Call<ResponseObj<List<EnglishPeiXunObj>>> getEnglishPeiXun(@QueryMap Map<String, String> map);

    //资讯详情
    @Headers("User-Agent:android")
    @GET("api/MQHomePage/GetInformationDetaile")
    Call<ResponseObj<ZiXunDetailObj>> getZiXunDetail(@QueryMap Map<String, String> map);

}

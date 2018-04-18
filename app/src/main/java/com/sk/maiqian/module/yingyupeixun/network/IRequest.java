package com.sk.maiqian.module.yingyupeixun.network;

import com.library.base.ResponseObj;
import com.sk.maiqian.module.home.network.response.EnglishPeiXunObj;
import com.sk.maiqian.module.yingyupeixun.network.response.KeChengDetailObj;
import com.sk.maiqian.module.yingyupeixun.network.response.OnlineStudyObj;
import com.sk.maiqian.module.yingyupeixun.network.response.TeacherObj;
import com.sk.maiqian.module.yingyupeixun.network.response.VideoDetailObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/6/28.
 */

public interface IRequest {


    //英语培训-体验课
    @GET("api/MQEnglishTraining/GetEnglishTrainingList")
    Call<ResponseObj<List<EnglishPeiXunObj>>> getEnglishPeiXun(@QueryMap Map<String, String> map);

    //英语培训-详情
    @GET("api/MQEnglishTraining/GetVisaDetaile")
    Call<ResponseObj<KeChengDetailObj>> getEnglishPeiXunDetail(@QueryMap Map<String, String> map);

    //英语培训-详情-老师详情
    @GET("api/MQEnglishTraining/GetTeachersDetail")
    Call<ResponseObj<TeacherObj>> getTeacherDetail(@QueryMap Map<String, String> map);


    //英语培训-详情-在线学习列表
    @GET("api/MQEnglishTraining/GetOnlineLearning")
    Call<ResponseObj<List<OnlineStudyObj>>> getOnlineStudyList(@QueryMap Map<String, String> map);

    //英语培训-在线学习详情
    @GET("api/MQEnglishTraining/GetOnlineLearningDetail")
    Call<ResponseObj<VideoDetailObj>> getOnlineStudyDetail(@QueryMap Map<String, String> map);


}

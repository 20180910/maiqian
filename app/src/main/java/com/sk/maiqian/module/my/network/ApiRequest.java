package com.sk.maiqian.module.my.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.request.FanKuiBody;
import com.sk.maiqian.module.my.network.request.RegisterBody;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    //登录
    public static void userLogin(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).userLogin(map).enqueue(callBack);
    }

    public static void getXieYi(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getXieYi(map).enqueue(callBack);
    }
    public static void register(Map map, @Body RegisterBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).register(map,body).enqueue(callBack);
    }
    public static void forgetPWD(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).forgetPWD(map).enqueue(callBack);
    }
    public static void setMessageSink(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).setMessageSink(map).enqueue(callBack);
    }
    public static void updatePWD(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).updatePWD(map).enqueue(callBack);
    }
    public static void getFanKuiType(Map map,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).getFanKuiType(map).enqueue(callBack);
    }
    public static void fanKui(Map map, List<FanKuiBody> body,MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).fanKui(map,body).enqueue(callBack);
    }


}

package com.sk.maiqian.network;

import com.github.retrofitutil.NetWorkManager;
import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.HuZhaoCallBack;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.network.request.ShiBieHuZhaoBody;
import com.sk.maiqian.network.request.UploadImgBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NetApiRequest extends BaseApiRequest {

    public static void getMsgCode(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getMsgCode(map).enqueue(callBack);
    }
    public static void uploadImg(Map map , UploadImgBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).uploadImg(map,body).enqueue(callBack);
    }
    public static void getAllArea(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getAllArea(map).enqueue(callBack);
    }
    public static void getBankList(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getBankList(map).enqueue(callBack);
    }
    public static void getAllCity(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getAllCity(map).enqueue(callBack);
    }
    public static void getYouXueObj(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getYouXueObj(map).enqueue(callBack);
    }
    public static void getPayNotifyUrl(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getPayNotifyUrl(map).enqueue(callBack);
    }
    public static void getAppVersion(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getAppVersion(map).enqueue(callBack);
    }
    public static void getShareInformation(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).getShareInformation(map).enqueue(callBack);
    }
    public static void appLogin(Map map  , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(NetIRequest.class).appLogin(map).enqueue(callBack);
    }
    public static void shiBieHuZhao(String header  , ShiBieHuZhaoBody body, HuZhaoCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        NetWorkManager.getGeneralClient("http://ocrhz.market.alicloudapi.com/").create(NetIRequest.class).shiBieHuZhao(header,body).enqueue(callBack);
    }
}

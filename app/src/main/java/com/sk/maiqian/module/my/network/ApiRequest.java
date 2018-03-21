package com.sk.maiqian.module.my.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    //登录
    public static void userLogin(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return; }
        getGeneralClient(IRequest.class).userLogin(map).enqueue(callBack);
    }


}

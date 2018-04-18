package com.sk.maiqian.module.youxue.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.youxue.network.request.YouXueShenQingBody;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    public static void getYouXueList(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getYouXueList(map).enqueue(callBack);
    }
    public static void getGuoJia(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getGuoJia(map).enqueue(callBack);
    }
    public static void youXueShenQing(Map map , YouXueShenQingBody body, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).youXueShenQing(map,body).enqueue(callBack);
    }
    public static void youXueDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).youXueDetail(map).enqueue(callBack);
    }



}

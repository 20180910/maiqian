package com.sk.maiqian.module.liuxue.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    public static void liuXueDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).liuXueDetail(map).enqueue(callBack);
    }
    public static void getLiuXueList(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getLiuXueList(map).enqueue(callBack);
    }
    public static void getGuoJia(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getGuoJia(map).enqueue(callBack);
    }
    public static void getNianJi(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getNianJi(map).enqueue(callBack);
    }
    public static void getZhuanYe(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getZhuanYe(map).enqueue(callBack);
    }
    public static void getShenQingDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getShenQingDetail(map).enqueue(callBack);
    }

}

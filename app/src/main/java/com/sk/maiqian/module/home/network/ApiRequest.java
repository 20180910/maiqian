package com.sk.maiqian.module.home.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    /*public static Observable getRegisterXieYi(String rnd, String sign){
        return getCommonClient(com.sk.yangyu.module.home.network.NetIRequest.class).getPayNotifyUrl(rnd,sign).compose(RxResult.appSchedulers()).compose(RxResult.handleResult());
    }*/
    public static void getHomeJiSuData(Map map, MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getHomeJiSuData(map).enqueue(callBack);
    }

}

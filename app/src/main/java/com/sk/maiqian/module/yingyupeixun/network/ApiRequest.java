package com.sk.maiqian.module.yingyupeixun.network;

import com.github.retrofitutil.NoNetworkException;
import com.library.base.BaseApiRequest;
import com.sk.maiqian.Config;
import com.sk.maiqian.base.MyCallBack;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ApiRequest extends BaseApiRequest {

    public static void getEnglishPeiXun(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getEnglishPeiXun(map).enqueue(callBack);
    }
    public static void getEnglishPeiXunDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getEnglishPeiXunDetail(map).enqueue(callBack);
    }
    public static void getTeacherDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getTeacherDetail(map).enqueue(callBack);
    }
    public static void getOnlineStudyList(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getOnlineStudyList(map).enqueue(callBack);
    }
    public static void getOnlineStudyDetail(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getOnlineStudyDetail(map).enqueue(callBack);
    }
    public static void makePeiXunOrder(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).makePeiXunOrder(map).enqueue(callBack);
    }
    public static void keChengYuYue(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).keChengYuYue(map).enqueue(callBack);
    }
    public static void getOnlineType(Map map , MyCallBack callBack) {
        if (notNetWork(callBack.getContext())) { callBack.onFailure(null, new NoNetworkException(Config.noNetWork)); return;  }
        getGeneralClient(IRequest.class).getOnlineType(map).enqueue(callBack);
    }


}

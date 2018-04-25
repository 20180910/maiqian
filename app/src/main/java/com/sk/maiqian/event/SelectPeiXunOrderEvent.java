package com.sk.maiqian.event;

/**
 * Created by Administrator on 2018/4/20.
 */

public class SelectPeiXunOrderEvent {
    public boolean isQianZhengOrder;
    public SelectPeiXunOrderEvent() {
    }
    public SelectPeiXunOrderEvent(boolean flag) {
        isQianZhengOrder=flag;
    }
}

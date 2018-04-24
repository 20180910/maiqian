package com.sk.maiqian.module.yingyupeixun.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/4/24.
 */

public class PeiXunMakeOrderObj extends BaseObj {
    /**
     * order_no : E201804241147395820
     * combined : 1.0
     */

    private String order_no;
    private double combined;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public double getCombined() {
        return combined;
    }

    public void setCombined(double combined) {
        this.combined = combined;
    }
}

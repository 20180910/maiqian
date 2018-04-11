package com.sk.maiqian.module.home.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ZiXunObj extends BaseObj {
    /**
     * wechat_id : MQ123456
     * phone : 18888888888
     * wechat_code : http://121.40.186.118:10089/upload/erweima.jpg
     */

    private String wechat_id;
    private String phone;
    private String wechat_code;

    public String getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(String wechat_id) {
        this.wechat_id = wechat_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat_code() {
        return wechat_code;
    }

    public void setWechat_code(String wechat_code) {
        this.wechat_code = wechat_code;
    }
}

package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/4/9.
 */

public class JiFenShuoMingObj extends BaseObj {
    /**
     * integral_description : 积分说明积分说明积分说明
     * img_url : http://121.40.186.118:10089/upload/zjf.jpg
     */

    private String integral_description;
    private String img_url;

    public String getIntegral_description() {
        return integral_description;
    }

    public void setIntegral_description(String integral_description) {
        this.integral_description = integral_description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}

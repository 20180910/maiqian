package com.sk.maiqian.module.my.network.request;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class FanKuiBody {

    private List<BodyBean> body;

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * img_url : sample string 1
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}

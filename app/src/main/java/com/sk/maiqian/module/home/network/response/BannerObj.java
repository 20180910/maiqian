package com.sk.maiqian.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class BannerObj extends BaseObj{

    private List<RoastingListBean> roasting_list;

    public List<RoastingListBean> getRoasting_list() {
        return roasting_list;
    }

    public void setRoasting_list(List<RoastingListBean> roasting_list) {
        this.roasting_list = roasting_list;
    }

    public static class RoastingListBean {
        /**
         * img_url : http://121.40.186.118:10089/upload/lbt.jpg
         * merchant_id : 0
         * is_jump : 0
         * content : null
         */

        private String img_url;
        private int merchant_id;
        private int is_jump;
        private Object content;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public int getIs_jump() {
            return is_jump;
        }

        public void setIs_jump(int is_jump) {
            this.is_jump = is_jump;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }
    }
}

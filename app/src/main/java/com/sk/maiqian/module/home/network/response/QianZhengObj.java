package com.sk.maiqian.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class QianZhengObj extends BaseObj {
    /**
     * national_flag : http://121.40.186.118:10089/upload/mg.jpg
     * title : 美国办签政策
     * policys : 美国签证政策美国签证政策美国签证政策美国签证政策美国签证政策美国签证政策美国签证政策美国签证政策
     * list2 : [{"visa_id":2,"title":"美国三个月单次个人旅游签证<上海签证>","img_url":"http://121.40.186.118:10089/upload/qz.jpg","for_how_long":"收齐资料8-10个工作日","price":300},{"visa_id":1,"title":"美国三个月单次个人旅游签证<上海签证>","img_url":"http://121.40.186.118:10089/upload/qz.jpg","for_how_long":"收齐资料8-10个工作日","price":300}]
     */

    private String national_flag;
    private String title;
    private String policys;
    private List<QianZhengBean> list2;

    public String getNational_flag() {
        return national_flag;
    }

    public void setNational_flag(String national_flag) {
        this.national_flag = national_flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPolicys() {
        return policys;
    }

    public void setPolicys(String policys) {
        this.policys = policys;
    }

    public List<QianZhengBean> getList2() {
        return list2;
    }

    public void setList2(List<QianZhengBean> list2) {
        this.list2 = list2;
    }

    public static class QianZhengBean {
        /**
         * visa_id : 2
         * title : 美国三个月单次个人旅游签证<上海签证>
         * img_url : http://121.40.186.118:10089/upload/qz.jpg
         * for_how_long : 收齐资料8-10个工作日
         * price : 300.0
         */

        private String visa_id;
        private String title;
        private String img_url;
        private String for_how_long;
        private double price;

        public String getVisa_id() {
            return visa_id;
        }

        public void setVisa_id(String visa_id) {
            this.visa_id = visa_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getFor_how_long() {
            return for_how_long;
        }

        public void setFor_how_long(String for_how_long) {
            this.for_how_long = for_how_long;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}

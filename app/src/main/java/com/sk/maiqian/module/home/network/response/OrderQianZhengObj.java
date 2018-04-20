package com.sk.maiqian.module.home.network.response;

/**
 * Created by Administrator on 2018/4/20.
 */

public class OrderQianZhengObj {
    /**
     * order_id : 211
     * order_no : M201804201027473747
     * visa_name : 美国签证
     * visa_id : 2
     * order_status : 1
     * title : 美国三个月单次个人旅游签证<上海签证>
     * img_url : http://121.40.186.118:10089/upload/qz.jpg
     * price : 300.0
     */

    private String order_id;
    private String order_no;
    private String visa_name;
    private String visa_id;
    private int order_status;
    private String title;
    private String img_url;
    private double price;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getVisa_name() {
        return visa_name;
    }

    public void setVisa_name(String visa_name) {
        this.visa_name = visa_name;
    }

    public String getVisa_id() {
        return visa_id;
    }

    public void setVisa_id(String visa_id) {
        this.visa_id = visa_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

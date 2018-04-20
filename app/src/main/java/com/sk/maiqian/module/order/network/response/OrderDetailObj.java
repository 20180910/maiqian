package com.sk.maiqian.module.order.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/4/20.
 */

public class OrderDetailObj extends BaseObj {
    /**
     * userid : 100043
     * order_no : E201804201336598725
     * order_status : 4
     * english_training_id : 1
     * title : 麦签英语口语成人培训--雅思托福
     * img_url : http://121.40.186.118:10089/upload/yingyu.jpg
     * price : 1.0
     * original_price : 2000.0
     * biaoqian : 口语|正式课|1对1
     * combined : 1.0
     * payment_add_time :
     * pay_way :
     * create_add_time : 2018-04-20 13:36:59
     */
    //签证
    private String userid;
    private String order_no;
    private int order_status;
    private String address_recipient;
    private String address_phone;
    private String address_detaile;
    private String visa_id;
    private String title;
    private String img_url;
    private double price;
    private double combined;
    private String payment_add_time;
    private String pay_way;
    private String create_add_time;
    private String courier;

    //培训
    private String english_training_id;
    private double original_price;
    private String biaoqian;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getAddress_recipient() {
        return address_recipient;
    }

    public void setAddress_recipient(String address_recipient) {
        this.address_recipient = address_recipient;
    }

    public String getAddress_phone() {
        return address_phone;
    }

    public void setAddress_phone(String address_phone) {
        this.address_phone = address_phone;
    }

    public String getAddress_detaile() {
        return address_detaile;
    }

    public void setAddress_detaile(String address_detaile) {
        this.address_detaile = address_detaile;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCombined() {
        return combined;
    }

    public void setCombined(double combined) {
        this.combined = combined;
    }

    public String getPayment_add_time() {
        return payment_add_time;
    }

    public void setPayment_add_time(String payment_add_time) {
        this.payment_add_time = payment_add_time;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public String getCreate_add_time() {
        return create_add_time;
    }

    public void setCreate_add_time(String create_add_time) {
        this.create_add_time = create_add_time;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getEnglish_training_id() {
        return english_training_id;
    }

    public void setEnglish_training_id(String english_training_id) {
        this.english_training_id = english_training_id;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }
}

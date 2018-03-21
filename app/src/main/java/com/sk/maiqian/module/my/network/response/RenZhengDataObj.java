package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/12.
 */

public class RenZhengDataObj extends BaseObj {
    /**
     * id : 6
     * client_id : 2
     * userid : 142
     * real_name : 张三
     * card_id : 432322197004134857
     * address : 中国
     * card_front_img : http://121.40.186.118:1145/upload/201801/11/180111153350663623.jpg
     * card_back_img : http://121.40.186.118:1145/upload/201801/11/180111153342316757.jpg
     * addtime : 2018-01-11 15:34:53
     * telphone : 15601772922
     * is_validation : 1
     * sex : 男
     */

    private int id;
    private int client_id;
    private int userid;
    private String real_name;
    private String address;
    private String card_front_img;
    private String card_back_img;
    private String addtime;
    private String telphone;
    private int is_validation;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCard_front_img() {
        return card_front_img;
    }

    public void setCard_front_img(String card_front_img) {
        this.card_front_img = card_front_img;
    }

    public String getCard_back_img() {
        return card_back_img;
    }

    public void setCard_back_img(String card_back_img) {
        this.card_back_img = card_back_img;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public int getIs_validation() {
        return is_validation;
    }

    public void setIs_validation(int is_validation) {
        this.is_validation = is_validation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

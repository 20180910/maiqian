package com.sk.maiqian.module.my.network.response;


import com.library.base.BaseObj;

/**
 * Created by administartor on 2017/9/11.
 */

public class LoginObj extends BaseObj {


    /**
     * user_id : 100043
     * user_name : 15601772922
     * nick_name : 15601772922
     * avatar :
     * mobile : 15601772922
     * message_sink : 1
     * point : 0
     */


    private String user_id;
    private String user_name;
    private String name;
    private String nick_name;
    private String avatar;
    private String sex;
    private String mobile;
    private String birthday;
    private double amount;
    private int commission;
    private int is_validation;
    private double point;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getIs_validation() {
        return is_validation;
    }

    public void setIs_validation(int is_validation) {
        this.is_validation = is_validation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}

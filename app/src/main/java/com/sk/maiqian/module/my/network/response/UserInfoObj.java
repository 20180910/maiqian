package com.sk.maiqian.module.my.network.response;


import com.library.base.BaseObj;

/**
 * Created by Administrator on 2017/11/18.
 */

public class UserInfoObj extends BaseObj {


    /**
     * user_id : 72
     * user_name : 121212
     * name : 咕咕咕咕
     * avatar :
     * sex : 男
     * mobile : 18616266522
     * message_sink : 1
     * email :
     * class_name : NET1702
     * user_type : 1
     */

    private String user_id;
    private String user_name;
    private String name;
    private String avatar;
    private String sex;
    private String mobile;
    private int message_sink;
    private String email;
    private String class_name;
    private int user_type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMessage_sink() {
        return message_sink;
    }

    public void setMessage_sink(int message_sink) {
        this.message_sink = message_sink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}

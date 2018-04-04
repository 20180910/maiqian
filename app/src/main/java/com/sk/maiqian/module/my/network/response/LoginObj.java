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
    private String nick_name;
    private String avatar;
    private String mobile;
    private double point;

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

package com.sk.maiqian.module.my.network.request;

/**
 * Created by Administrator on 2018/4/10.
 */

public class EditNickNameBody {
    /**
     * user_id : 1
     * nickname : sample string 2
     */

    private String user_id;
    private String nickname;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

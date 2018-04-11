package com.sk.maiqian.module.home.network.request;

/**
 * Created by Administrator on 2018/4/11.
 */

public class QianZhengLiuYanBody {

    /**
     * name : sample string 1
     * phone : sample string 2
     * email : sample string 3
     * content : sample string 4
     */

    private String name;
    private String phone;
    private String email;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

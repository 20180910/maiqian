package com.sk.maiqian.module.my.network.response;


import com.library.base.BaseObj;

/**
 * Created by administartor on 2017/9/11.
 */

public class LoginObj extends BaseObj {


    /**
     * user_id : 142
     * user_name : 15601772923
     * name :
     * nick_name : 15601772923
     * avatar :
     * sex :
     * mobile : 15601772923
     * birthday : 2018/1/5
     * amount : 0
     * commission : 0
     * message_sink : 1
     * is_validation : 0
     * cumulative_reward : 0
     * email : null
     * major : null
     */

    private String user_id;
    private String user_name;
    private String name;
    private String nick_name;
    private String avatar;
    private String sex;
    private String mobile;
    private String birthday;
    private float amount;
    private int commission;
    private int message_sink;
    private int is_validation;//身份认证状态(0未认证 1待审核 2审核通过 3审核未通过)
    private int cumulative_reward;
    private String email;
    private String major;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getMessage_sink() {
        return message_sink;
    }

    public void setMessage_sink(int message_sink) {
        this.message_sink = message_sink;
    }

    public int getIs_validation() {
        return is_validation;
    }

    public void setIs_validation(int is_validation) {
        this.is_validation = is_validation;
    }

    public int getCumulative_reward() {
        return cumulative_reward;
    }

    public void setCumulative_reward(int cumulative_reward) {
        this.cumulative_reward = cumulative_reward;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

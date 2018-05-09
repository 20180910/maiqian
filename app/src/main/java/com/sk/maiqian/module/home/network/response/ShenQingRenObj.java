package com.sk.maiqian.module.home.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/13.
 */

public class ShenQingRenObj implements Serializable{
    /**
     * applicant_information_id : 1
     * chinese_name : 哦哦
     * sex : 男
     * age_duan : 成人
     * customer_type : 在职人员
     * passport_no : 555
     */

    private String applicant_information_id;
    private String chinese_name;
    private String sex;
    private String age_duan;
    private String customer_type;
    private String passport_no;
    private String id_card_url_positive;
    private String id_card_url_reverse;
    private String image_url;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getId_card_url_positive() {
        return id_card_url_positive;
    }

    public void setId_card_url_positive(String id_card_url_positive) {
        this.id_card_url_positive = id_card_url_positive;
    }

    public String getId_card_url_reverse() {
        return id_card_url_reverse;
    }

    public void setId_card_url_reverse(String id_card_url_reverse) {
        this.id_card_url_reverse = id_card_url_reverse;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getApplicant_information_id() {
        return applicant_information_id;
    }

    public void setApplicant_information_id(String applicant_information_id) {
        this.applicant_information_id = applicant_information_id;
    }

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge_duan() {
        return age_duan;
    }

    public void setAge_duan(String age_duan) {
        this.age_duan = age_duan;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }
}

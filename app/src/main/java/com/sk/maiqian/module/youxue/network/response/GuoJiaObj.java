package com.sk.maiqian.module.youxue.network.response;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GuoJiaObj {
    /**
     * countrie_region_id : 4
     * title : 新加坡
     */

    private String countrie_region_id;
    private String title;
    private String class_name;
    private String major_id;
    private String major_name;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public GuoJiaObj(String countrie_region_id, String title) {
        this.countrie_region_id = countrie_region_id;
        this.title = title;
        this.major_id = countrie_region_id;
        this.major_name = title;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getCountrie_region_id() {
        return countrie_region_id;
    }
    public void setCountrie_region_id(String countrie_region_id) {
        this.countrie_region_id = countrie_region_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

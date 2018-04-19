package com.sk.maiqian.module.liuxue.network.response;

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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public GuoJiaObj(String countrie_region_id, String title) {
        this.countrie_region_id = countrie_region_id;
        this.title = title;
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

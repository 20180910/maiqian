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

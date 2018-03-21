package com.sk.maiqian.module.my.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10.
 */

public class HelpCenterObj implements Serializable{
    /**
     * id : 21
     * title : 审核需要多久?
     * content : 需要很久很久需要很久很久需要很久很久
     */

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MessageDetailObj extends BaseObj {
    /**
     * id : 5356
     * title : 测试
     * content : 测试
     * add_time : 2018/1/10
     */

    private int id;
    private String title;
    private String content;
    private String add_time;

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

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}

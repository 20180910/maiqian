package com.sk.maiqian.module.my.network.response;

/**
 * Created by Administrator on 2018/1/11.
 */

public class MyMessageObj  {
    /**
     * id : 5357
     * title : 测试
     * content : 测试
     * add_time : 2018/1/10
     * is_check : 1
     * image : http://121.40.186.118:1145/upload/gonggao.png
     */

    private int id;
    private String title;
    private String content;
    private String add_time;
    private int is_check;
    private String image;
    private boolean isZhanKai;

    public boolean isZhanKai() {
        return isZhanKai;
    }

    public void setZhanKai(boolean zhanKai) {
        isZhanKai = zhanKai;
    }

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

    public int getIs_check() {
        return is_check;
    }

    public void setIs_check(int is_check) {
        this.is_check = is_check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.sk.maiqian.module.home.network.response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeZiXunObj {
    /**
     * information_id : 642
     * title : 沪江讲坛：上海魔都的风云与风月
     * image_url : http://121.40.186.118:10089/upload/zixun.jpg
     * author : 来源于http://ccad.usst.edu.cn/
     * add_time : 2018/3/21
     */
    private int information_id;
    private String title;
    private String image_url;
    private String author;
    private String add_time;

    public int getInformation_id() {
        return information_id;
    }

    public void setInformation_id(int information_id) {
        this.information_id = information_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}

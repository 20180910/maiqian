package com.sk.maiqian.module.youxue.network.response;

/**
 * Created by Administrator on 2018/4/18.
 */

public class YouXueObj  {
    /**
     * id : 1
     * subtitle : 美国游学
     * english_title : English Title
     * image_url : http://121.40.186.118:10089/upload/mgyx.png
     * interested_peopleum : 200
     */

    private String id;
    private String subtitle;
    private String english_title;
    private String image_url;
    private int interested_peopleum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getEnglish_title() {
        return english_title;
    }

    public void setEnglish_title(String english_title) {
        this.english_title = english_title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getInterested_peopleum() {
        return interested_peopleum;
    }

    public void setInterested_peopleum(int interested_peopleum) {
        this.interested_peopleum = interested_peopleum;
    }
}

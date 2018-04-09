package com.sk.maiqian.module.my.network.response;

/**
 * Created by Administrator on 2018/4/4.
 */

public class MyCollectionObj {
    /**
     * id : 1
     * title : 美国三个月单次个人旅游签证<上海签证>
     * img_url : http://121.40.186.118:10089/upload/qz.jpg
     * price : 300.0
     */

    private String id;
    private String title;
    private String english_title;
    private String img_url;
    private double price;

    private String biaoqian;
    private String original_price;
    private String interested_peopleum;


    public String getEnglish_title() {
        return english_title;
    }

    public void setEnglish_title(String english_title) {
        this.english_title = english_title;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getInterested_peopleum() {
        return interested_peopleum;
    }

    public void setInterested_peopleum(String interested_peopleum) {
        this.interested_peopleum = interested_peopleum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

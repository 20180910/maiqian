package com.sk.maiqian.module.home.network.response;

/**
 * Created by Administrator on 2018/4/17.
 */

public class EnglishPeiXunObj {
    /**
     * english_training_id : 2
     * title : 麦签英语口语成人培训--雅思托福
     * img_url : http://121.40.186.118:10089/upload/yingyu.jpg
     * biaoqian : 口语|体验课|1对1
     * price : 1.0
     * original_price : 2000.0
     * people_number : 2000
     */

    private String english_training_id;
    private String title;
    private String img_url;
    private String biaoqian;
    private double price;
    private double original_price;
    private int people_number;

    public String getEnglish_training_id() {
        return english_training_id;
    }

    public void setEnglish_training_id(String english_training_id) {
        this.english_training_id = english_training_id;
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

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public int getPeople_number() {
        return people_number;
    }

    public void setPeople_number(int people_number) {
        this.people_number = people_number;
    }
}

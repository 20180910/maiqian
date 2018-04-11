package com.sk.maiqian.module.home.network.response;

/**
 * Created by Administrator on 2018/4/11.
 */

public class QianZhengDaiBanObj {
    /**
     * countrie_region_id : 1
     * title : 美国
     * content : 旅游签证·探亲签证·商务签证
     * img_url : http://121.40.186.118:10089/upload/fmt1.jpg
     * for_how_long : 6-8个工作日
     * applications : 900
     * label_type : 1
     * price : 300.0
     */

    private int countrie_region_id;
    private String title;
    private String content;
    private String img_url;
    private String for_how_long;
    private int applications;
    private int label_type;
    private double price;

    public int getCountrie_region_id() {
        return countrie_region_id;
    }

    public void setCountrie_region_id(int countrie_region_id) {
        this.countrie_region_id = countrie_region_id;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getFor_how_long() {
        return for_how_long;
    }

    public void setFor_how_long(String for_how_long) {
        this.for_how_long = for_how_long;
    }

    public int getApplications() {
        return applications;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }

    public int getLabel_type() {
        return label_type;
    }

    public void setLabel_type(int label_type) {
        this.label_type = label_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

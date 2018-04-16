package com.sk.maiqian.module.home.network.response;

/**
 * Created by Administrator on 2018/4/16.
 */

public class ZiLiaoMuBanObj {
    /**
     * id : 88
     * lable : 申请表格
     * title : 个人信息表
     * content : 填写真实完整的美国报名表一份，确保联系电话真实有效、保持畅通便于领馆联系。预约时递交此材料的电子版即可，预约完成后会发送预约确认页到您的邮箱，打印出来面试时携带。
     * img_url : http://121.40.186.118:10089/upload/moban.jpg
     * is_jump : 0
     */

    private String id;
    private String lable;
    private String title;
    private String content;
    private String img_url;
    private int is_jump;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
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

    public int getIs_jump() {
        return is_jump;
    }

    public void setIs_jump(int is_jump) {
        this.is_jump = is_jump;
    }
}

package com.sk.maiqian.module.yingyupeixun.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class TeacherObj extends BaseObj {
    /**
     * teacher_id : 1
     * head_portrait : http://121.40.186.118:10089/upload/touxiang.jpg
     * image_url : http://121.40.186.118:10089/upload/beijing.jpg
     * teacher_name : Tom
     * school_age : 7
     * main_attack : 口语
     * teacher_nationality : 美籍
     * teacher_introduction : 老师来自英语为母语的国家，老师介绍老师介绍老师介绍老师介绍老师介绍
     * title : 麦签英语成人培训--Tom
     * biaoqian : 英语口语|7年教龄
     * shouke_list : [{"english_training_id":1,"title":"麦签英语口语成人培训--雅思托福","img_url":"http://121.40.186.118:10089/upload/yingyu.jpg","biaoqian":"口语|正式课|1对1","price":1,"original_price":2000},{"english_training_id":2,"title":"麦签英语口语成人培训--雅思托福","img_url":"http://121.40.186.118:10089/upload/yingyu.jpg","biaoqian":"口语|体验课|1对1","price":1,"original_price":2000}]
     */

    private String teacher_id;
    private String head_portrait;
    private String image_url;
    private String teacher_name;
    private int school_age;
    private String main_attack;
    private String teacher_nationality;
    private String teacher_introduction;
    private String title;
    private String biaoqian;
    private List<ShoukeListBean> shouke_list;

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public int getSchool_age() {
        return school_age;
    }

    public void setSchool_age(int school_age) {
        this.school_age = school_age;
    }

    public String getMain_attack() {
        return main_attack;
    }

    public void setMain_attack(String main_attack) {
        this.main_attack = main_attack;
    }

    public String getTeacher_nationality() {
        return teacher_nationality;
    }

    public void setTeacher_nationality(String teacher_nationality) {
        this.teacher_nationality = teacher_nationality;
    }

    public String getTeacher_introduction() {
        return teacher_introduction;
    }

    public void setTeacher_introduction(String teacher_introduction) {
        this.teacher_introduction = teacher_introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public List<ShoukeListBean> getShouke_list() {
        return shouke_list;
    }

    public void setShouke_list(List<ShoukeListBean> shouke_list) {
        this.shouke_list = shouke_list;
    }

    public static class ShoukeListBean {
        /**
         * english_training_id : 1
         * title : 麦签英语口语成人培训--雅思托福
         * img_url : http://121.40.186.118:10089/upload/yingyu.jpg
         * biaoqian : 口语|正式课|1对1
         * price : 1.0
         * original_price : 2000.0
         */

        private String english_training_id;
        private String title;
        private String img_url;
        private String biaoqian;
        private double price;
        private double original_price;

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
    }
}

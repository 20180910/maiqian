package com.sk.maiqian.module.yingyupeixun.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class KeChengDetailObj extends BaseObj {
    /**
     * english_training_id : 1
     * title : 麦签英语口语成人培训--雅思托福
     * img_url : http://121.40.186.118:10089/upload/yingyu.jpg
     * price : 1.0
     * original_price : 2000.0
     * address : 上海市淮海西路55号
     * suits_crowd : 成人
     * suits_basic : 不限
     * number_class : 1对1
     * teacher_nationality : 美教
     * class_name : 麦签成人英语口语01班
     * clss_hour : 20
     * topclass_time : 随时上课,随到随学
     * applicable_object : ·有移民计划的学员·有留学计划的学员·想要用正规考试来证明自己英语水平的所有人
     * learning_goals : 冲刺更高分，精析各单科命题思路和策略，强化并反复演练更单科解题技巧，大量讲授真题，大量演练真题
     * teacher_list : [{"teacher_id":1,"teacher_name":"Tom","head_portrait":"http://121.40.186.118:10089/upload/touxiang.jpg","biaoqian":"英语口语|7年教龄"},{"teacher_id":2,"teacher_name":"Eleanor","head_portrait":"http://121.40.186.118:10089/upload/touxiang.jpg","biaoqian":"英语口语|7年教龄"},{"teacher_id":3,"teacher_name":"Jack","head_portrait":"http://121.40.186.118:10089/upload/touxiang.jpg","biaoqian":"英语口语|7年教龄"}]
     * course_characteristic : · 立刻开始说英语· 旅游口语系统提升· 词汇量逐渐增加中、正确使用语法
     * period_validity : 2018.4.1-2018.5.31
     * canshu_list : [{"title":"预约信息","content":"提前一天预约"},{"title":"调课说明","content":"提前一天调课"},{"title":"温馨提示","content":"若您需要发票，请在购买时或者购买后提出。"}]
     * is_collect : 0
     */

    private String english_training_id;
    private String title;
    private String img_url;
    private double price;
    private double original_price;
    private String address;
    private String suits_crowd;
    private String suits_basic;
    private String number_class;
    private String teacher_nationality;
    private String class_name;
    private int clss_hour;
    private String topclass_time;
    private String applicable_object;
    private String learning_goals;
    private String course_characteristic;
    private String period_validity;
    private int is_collect;
    private List<TeacherListBean> teacher_list;
    private List<CanshuListBean> canshu_list;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuits_crowd() {
        return suits_crowd;
    }

    public void setSuits_crowd(String suits_crowd) {
        this.suits_crowd = suits_crowd;
    }

    public String getSuits_basic() {
        return suits_basic;
    }

    public void setSuits_basic(String suits_basic) {
        this.suits_basic = suits_basic;
    }

    public String getNumber_class() {
        return number_class;
    }

    public void setNumber_class(String number_class) {
        this.number_class = number_class;
    }

    public String getTeacher_nationality() {
        return teacher_nationality;
    }

    public void setTeacher_nationality(String teacher_nationality) {
        this.teacher_nationality = teacher_nationality;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getClss_hour() {
        return clss_hour;
    }

    public void setClss_hour(int clss_hour) {
        this.clss_hour = clss_hour;
    }

    public String getTopclass_time() {
        return topclass_time;
    }

    public void setTopclass_time(String topclass_time) {
        this.topclass_time = topclass_time;
    }

    public String getApplicable_object() {
        return applicable_object;
    }

    public void setApplicable_object(String applicable_object) {
        this.applicable_object = applicable_object;
    }

    public String getLearning_goals() {
        return learning_goals;
    }

    public void setLearning_goals(String learning_goals) {
        this.learning_goals = learning_goals;
    }

    public String getCourse_characteristic() {
        return course_characteristic;
    }

    public void setCourse_characteristic(String course_characteristic) {
        this.course_characteristic = course_characteristic;
    }

    public String getPeriod_validity() {
        return period_validity;
    }

    public void setPeriod_validity(String period_validity) {
        this.period_validity = period_validity;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public List<TeacherListBean> getTeacher_list() {
        return teacher_list;
    }

    public void setTeacher_list(List<TeacherListBean> teacher_list) {
        this.teacher_list = teacher_list;
    }

    public List<CanshuListBean> getCanshu_list() {
        return canshu_list;
    }

    public void setCanshu_list(List<CanshuListBean> canshu_list) {
        this.canshu_list = canshu_list;
    }

    public static class TeacherListBean {
        /**
         * teacher_id : 1
         * teacher_name : Tom
         * head_portrait : http://121.40.186.118:10089/upload/touxiang.jpg
         * biaoqian : 英语口语|7年教龄
         */

        private String teacher_id;
        private String teacher_name;
        private String head_portrait;
        private String biaoqian;

        public String getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(String teacher_id) {
            this.teacher_id = teacher_id;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getBiaoqian() {
            return biaoqian;
        }

        public void setBiaoqian(String biaoqian) {
            this.biaoqian = biaoqian;
        }
    }

    public static class CanshuListBean {
        /**
         * title : 预约信息
         * content : 提前一天预约
         */

        private String title;
        private String content;

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
}

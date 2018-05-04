package com.sk.maiqian.module.youxue.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class YouXueDetailObj extends BaseObj {
    /**
     * id : 1
     * image_url : http://121.40.186.118:10089/upload/mgyx.png
     * title : 暑期精选-哈弗深度名校体验营16天(全称哈弗师生领队)
     * english_title : English Title
     * qs_ranking : 5
     * canshu_list : [{"title":"行程天数","content":"16天"},{"title":"适合年龄","content":"中学生"},{"title":"出发地","content":"上海"}]
     * introduce : 体验美式教育体系，与美国学子们共同学习交流，提高同学们的国际沟通能力。走进美国传统科学营地，激发同学们的学习兴趣，拓展同学们的视野。在环识中美双方专业老师的全程陪同下，插班探访2所美国当地中小学校，与美国同龄孩子们一起参与课堂学习交流，亲身体验美国教育理念。在环识中美双方专业老师的全程带领下，探访加州2所知名大学，与名校学子们沟通交流，近距离了解美国升学体系。在环识中美双方专业老师的全程陪同及带领下，走进美国传统科学营地以及科研中心，深度吸取学术知识 ，实现寓教于乐，激发学习兴趣，让您的孩子在不同的文化环境中进步，开拓自己的视野。
     * scenery_list : ["http://121.40.186.118:10089/upload/fg1.png","http://121.40.186.118:10089/upload/fg2.png","http://121.40.186.118:10089/upload/fg3.png"]
     * travel_characteristics : 体验名校生活,快乐学习英语，走访世界顶尖名校
     * major_list : []
     * is_collect : 0
     */

    private String id;
    private String countrie_region_id;
    private String image_url;
    private String title;
    private String english_title;
    private int qs_ranking;
    private String introduce;
    private String travel_characteristics;
    private int is_collect;
    private List<CanshuListBean> canshu_list;
    private List<String> scenery_list;
    private List<String> major_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnglish_title() {
        return english_title;
    }

    public void setEnglish_title(String english_title) {
        this.english_title = english_title;
    }

    public String getCountrie_region_id() {
        return countrie_region_id;
    }

    public void setCountrie_region_id(String countrie_region_id) {
        this.countrie_region_id = countrie_region_id;
    }

    public int getQs_ranking() {
        return qs_ranking;
    }

    public void setQs_ranking(int qs_ranking) {
        this.qs_ranking = qs_ranking;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTravel_characteristics() {
        return travel_characteristics;
    }

    public void setTravel_characteristics(String travel_characteristics) {
        this.travel_characteristics = travel_characteristics;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public List<CanshuListBean> getCanshu_list() {
        return canshu_list;
    }

    public void setCanshu_list(List<CanshuListBean> canshu_list) {
        this.canshu_list = canshu_list;
    }

    public List<String> getScenery_list() {
        return scenery_list;
    }

    public void setScenery_list(List<String> scenery_list) {
        this.scenery_list = scenery_list;
    }

    public List<String> getMajor_list() {
        return major_list;
    }

    public void setMajor_list(List<String> major_list) {
        this.major_list = major_list;
    }

    public static class CanshuListBean {
        /**
         * title : 行程天数
         * content : 16天
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

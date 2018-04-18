package com.sk.maiqian.module.yingyupeixun.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class VideoDetailObj extends BaseObj {
    /**
     * courseware_id : 17
     * title : 英语初级口语音频练习
     * image_url : http://121.40.186.118:10089/upload/zxxx.jpg
     * attachment : 零基础
     * add_time : 2018-03-27
     * video_link : http://www.baidu.com
     * courseware_introduction : 介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍
     * shouke_list : []
     */

    private String courseware_id;
    private String title;
    private String image_url;
    private String attachment;
    private String add_time;
    private String video_link;
    private String courseware_introduction;
    private List<OnlineStudyObj> shouke_list;

    public String getCourseware_id() {
        return courseware_id;
    }

    public void setCourseware_id(String courseware_id) {
        this.courseware_id = courseware_id;
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public String getCourseware_introduction() {
        return courseware_introduction;
    }

    public void setCourseware_introduction(String courseware_introduction) {
        this.courseware_introduction = courseware_introduction;
    }

    public List<OnlineStudyObj> getShouke_list() {
        return shouke_list;
    }

    public void setShouke_list(List<OnlineStudyObj> shouke_list) {
        this.shouke_list = shouke_list;
    }
}

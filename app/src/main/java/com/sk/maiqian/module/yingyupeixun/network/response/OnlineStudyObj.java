package com.sk.maiqian.module.yingyupeixun.network.response;

/**
 * Created by Administrator on 2018/4/18.
 */

public class OnlineStudyObj {
    /**
     * courseware_id : 16
     * title : 英语初级口语视频练习
     * image_url : http://121.40.186.118:10089/upload/zxxx.jpg
     * attachment : 零基础
     * time_length : 00:04:06
     * add_time : 2018-03-27
     * video_link :
     */

    private String courseware_id;
    private String title;
    private String image_url;
    private String attachment;
    private String time_length;
    private String add_time;
    private String video_link;
    private int type_id;
    private String type_name;

    private boolean hidden;


    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

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

    public String getTime_length() {
        return time_length;
    }

    public void setTime_length(String time_length) {
        this.time_length = time_length;
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
}

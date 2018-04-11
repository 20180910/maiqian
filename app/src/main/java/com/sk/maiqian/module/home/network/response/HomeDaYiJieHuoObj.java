package com.sk.maiqian.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeDaYiJieHuoObj extends BaseObj {

    private List<AnswerDoubtsListBean> answer_doubts_list;
    private List<TypeListBean> type_list;

    public List<AnswerDoubtsListBean> getAnswer_doubts_list() {
        return answer_doubts_list;
    }

    public void setAnswer_doubts_list(List<AnswerDoubtsListBean> answer_doubts_list) {
        this.answer_doubts_list = answer_doubts_list;
    }

    public List<TypeListBean> getType_list() {
        return type_list;
    }

    public void setType_list(List<TypeListBean> type_list) {
        this.type_list = type_list;
    }

    public static class AnswerDoubtsListBean {
        /**
         * id : 55
         * title : 签证多久可以办下来?
         * content : 10天
         */

        private int id;
        private String title;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class TypeListBean {
        /**
         * title : 签证代办
         * img_url : http://121.40.186.118:10089/upload/qzdb.jpg
         * introduction : 全球签证、加急签证
         */

        private String title;
        private String img_url;
        private String introduction;

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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}

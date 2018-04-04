package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class FenXiaoObj extends BaseObj {
    /**
     * content : 将此分销码分享给好友，好友注册时填写你的分销码，你可以赚取积分。
     * distribution_yard : 82661186
     * distribution_url : http://121.40.186.118:10089/upload/code/20180504030512897.jpg
     * list : []
     */

    private String content;
    private String distribution_yard;
    private String distribution_url;
    private List<XiaJi> list;
    public class XiaJi {
        private String nick_name;
        private String add_time;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDistribution_yard() {
        return distribution_yard;
    }

    public void setDistribution_yard(String distribution_yard) {
        this.distribution_yard = distribution_yard;
    }

    public String getDistribution_url() {
        return distribution_url;
    }

    public void setDistribution_url(String distribution_url) {
        this.distribution_url = distribution_url;
    }

    public List<XiaJi> getList() {
        return list;
    }

    public void setList(List<XiaJi> list) {
        this.list = list;
    }
}

package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/11.
 */

public class YaoQingObj extends BaseObj {
    /**
     * activity_rule : 一、活动时间：<br />
     2017.11.07-2017.12.30<br />
     二、邀请方式<br />
     1、可以通过立即邀请分享链接，<br />
     2、可以通过扫二维码<br />
     三、玩法介绍：<br />
     1、邀请以为好友注册神奇还卡，即可获得1000元免息额度机会<br />
     2、好友使用神奇还卡还信用卡的用户，即可获得10元现金奖励<br />
     <br />
     */

    private String activity_rule;

    public String getActivity_rule() {
        return activity_rule;
    }

    public void setActivity_rule(String activity_rule) {
        this.activity_rule = activity_rule;
    }
}

package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/10.
 */

public class AboutWeObj extends BaseObj {
    /**
     * image :
     * edition :
     * tel_wechat :
     * QQ :
     * email :
     */

    private String image;
    private String edition;
    private String tel_wechat;
    private String QQ;
    private String email;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getTel_wechat() {
        return tel_wechat;
    }

    public void setTel_wechat(String tel_wechat) {
        this.tel_wechat = tel_wechat;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

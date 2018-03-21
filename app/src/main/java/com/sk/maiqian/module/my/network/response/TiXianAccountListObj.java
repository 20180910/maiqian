package com.sk.maiqian.module.my.network.response;

/**
 * Created by Administrator on 2018/1/25.
 */

public class TiXianAccountListObj {
    /**
     * userid : 157
     * id : 53
     * bank_image : http://121.40.186.118:1145/upload/201709/18/201709181455150015.png
     * bank_name : 中国银行
     * card_type : 储蓄卡
     * bank_card : 尾号4499
     * is_default : 1
     */

    private int userid;
    private int id;
    private String bank_image;
    private String bank_name;
    private String card_type;
    private String bank_card;
    private int is_default;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank_image() {
        return bank_image;
    }

    public void setBank_image(String bank_image) {
        this.bank_image = bank_image;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}

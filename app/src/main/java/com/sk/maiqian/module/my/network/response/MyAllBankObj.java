package com.sk.maiqian.module.my.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MyAllBankObj implements Serializable {

    /**
     * userid : 100043
     * id : 63
     * bank_image :
     * bank_name : 中国银行
     * real_name : 张三
     * id_number : 432322197004134857
     * card_type : 储蓄卡
     * bank_card_number : 6217880800008464499
     * bank_card : 尾号4499
     * opening_bank :
     * is_default : 1
     */

    private String userid;
    private String id;
    private String bank_image;
    private String bank_name;
    private String real_name;
    private String id_number;
    private String card_type;
    private String bank_card_number;
    private String bank_card;
    private String opening_bank;
    private int is_default;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getOpening_bank() {
        return opening_bank;
    }

    public void setOpening_bank(String opening_bank) {
        this.opening_bank = opening_bank;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }
}

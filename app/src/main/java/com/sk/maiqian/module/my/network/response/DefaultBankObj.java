package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

/**
 * Created by Administrator on 2018/1/10.
 */

public class DefaultBankObj extends BaseObj {
    /**
     * id : 42
     * bank_name : 浦发银行   0126
     * bank_card : ****0126
     * card_type : 储蓄卡
     * bank_image : null
     */

    private int id;
    private String bank_name;
    private String bank_card;
    private String card_type;
    private String bank_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getBank_image() {
        return bank_image;
    }

    public void setBank_image(String bank_image) {
        this.bank_image = bank_image;
    }
}

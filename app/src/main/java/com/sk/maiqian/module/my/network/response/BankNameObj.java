package com.sk.maiqian.module.my.network.response;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BankNameObj  {
    /**
     * bank_id : 1
     * image_url : http://121.40.186.118:1145/upload/201709/18/201709181455150015.png
     * bank_name : 中国银行
     */

    private int bank_id;
    private String image_url;
    private String bank_name;

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}

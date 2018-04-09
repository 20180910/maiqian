package com.sk.maiqian.module.my.network.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 */

public class MyAddressObj   implements Serializable{
    /**
     * address_id : 3
     * recipient : 张三
     * phone : 15601772922
     * shipping_address : 上海市上海市卢湾区
     * shipping_address_details : 上海路
     * is_default : 0
     * province : 5781
     * city : 5782
     * area : 5784
     */

    private int address_id;
    private String recipient;
    private String phone;
    private String shipping_address;
    private String shipping_address_details;
    private int is_default;
    private int province;
    private int city;
    private int area;

    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_address_details() {
        return shipping_address_details;
    }

    public void setShipping_address_details(String shipping_address_details) {
        this.shipping_address_details = shipping_address_details;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}

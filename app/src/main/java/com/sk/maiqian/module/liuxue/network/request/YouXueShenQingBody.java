package com.sk.maiqian.module.liuxue.network.request;

/**
 * Created by Administrator on 2018/4/18.
 */

public class YouXueShenQingBody {
    /**
     * destination : sample string 1
     * phone : sample string 2
     * city_gradeschool : sample string 3
     * attend_professional : sample string 4
     */

    private String destination;
    private String phone;
    private String city_gradeschool;
    private String attend_professional;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity_gradeschool() {
        return city_gradeschool;
    }

    public void setCity_gradeschool(String city_gradeschool) {
        this.city_gradeschool = city_gradeschool;
    }

    public String getAttend_professional() {
        return attend_professional;
    }

    public void setAttend_professional(String attend_professional) {
        this.attend_professional = attend_professional;
    }
}

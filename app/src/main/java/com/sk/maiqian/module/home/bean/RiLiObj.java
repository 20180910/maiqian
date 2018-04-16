package com.sk.maiqian.module.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class RiLiObj implements Serializable {

    //0上月，1本月，2下月
    public int type;
    public int year;
    public int month;
    public int day;
    public List<RiLiObj> list;

}

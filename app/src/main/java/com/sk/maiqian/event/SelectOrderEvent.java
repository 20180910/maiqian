package com.sk.maiqian.event;

import com.sk.maiqian.module.home.fragment.OrderFragment;

/**
 * Created by Administrator on 2018/4/20.
 */

public class SelectOrderEvent {
    public static final String type_1= OrderFragment.type_1;
    public static final String type_2=OrderFragment.type_2;
    public String type;

    public SelectOrderEvent(String type) {
        this.type = type;
    }
}

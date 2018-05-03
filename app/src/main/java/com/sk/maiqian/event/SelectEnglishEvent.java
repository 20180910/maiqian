package com.sk.maiqian.event;

/**
 * Created by Administrator on 2018/5/3.
 */

public class SelectEnglishEvent {
    public int flag;//1正式课 2体验课

    public SelectEnglishEvent(int flag) {
        this.flag = flag;
    }
}

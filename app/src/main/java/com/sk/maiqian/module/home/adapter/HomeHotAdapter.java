package com.sk.maiqian.module.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class HomeHotAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private String[]title=new String[]{"签证代办","英语培训","游学","留学"};

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public HomeHotAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

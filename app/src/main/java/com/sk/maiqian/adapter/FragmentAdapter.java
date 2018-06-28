package com.sk.maiqian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private String[]title=new String[]{"英语培训","签证代办","游学","留学"};

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public FragmentAdapter(FragmentManager fm) {
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
        if(title==null||title.length==0){
            return super.getPageTitle(position);
        }else{
            return title[position];
        }
    }
}

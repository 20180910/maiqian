package com.sk.maiqian.module.my.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.library.base.view.MyViewPager;
import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.my.fragment.MyCollectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.tab_collection)
    TabLayout tab_collection;

    @BindView(R.id.vp_collection)
    MyViewPager vp_collection;
    List<Fragment> list=new ArrayList<>();
    FragmentAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("我的收藏");
        return R.layout.mycollection_act;
    }

    @Override
    protected void initView() {
        adapter=new FragmentAdapter(getSupportFragmentManager());
        list.add(MyCollectFragment.newInstance(MyCollectFragment.type_1));
        list.add(MyCollectFragment.newInstance(MyCollectFragment.type_2));
        list.add(MyCollectFragment.newInstance(MyCollectFragment.type_3));
        list.add(MyCollectFragment.newInstance(MyCollectFragment.type_4));

        adapter.setList(list);
        vp_collection.setAdapter(adapter);
        vp_collection.setOffscreenPageLimit(list.size()-1);

        tab_collection.setupWithViewPager(vp_collection);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

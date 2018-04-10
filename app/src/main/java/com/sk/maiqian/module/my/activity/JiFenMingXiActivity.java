package com.sk.maiqian.module.my.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.my.fragment.JiFenMingXiFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class JiFenMingXiActivity extends BaseActivity {

    @BindView(R.id.tab_mingxi)
    TabLayout tab_mingxi;

    @BindView(R.id.vp_mingxi)
    ViewPager vp_mingxi;

    FragmentAdapter adapter;
    List<Fragment> list=new ArrayList<>();
    @Override
    protected int getContentView() {
        setAppTitle("积分明细");
        return R.layout.jifenmingxi_act;
    }

    @Override
    protected void initView() {
        adapter=new FragmentAdapter(getSupportFragmentManager());
        adapter.setTitle(new String[]{"获取明细","提现明细"});

        list.add(JiFenMingXiFragment.newInstance(JiFenMingXiFragment.type_1));
        list.add(JiFenMingXiFragment.newInstance(JiFenMingXiFragment.type_2));

        adapter.setList(list);

        vp_mingxi.setAdapter(adapter);

        tab_mingxi.setupWithViewPager(vp_mingxi);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

package com.sk.maiqian.module.home.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/12/4.
 */

public class OrderFragment extends BaseFragment {
    public static final String type_1="1";//签证
    public static final String type_2="2";//培训

    FragmentAdapter adapter;

    @BindView(R.id.tab_order)
    TabLayout tab_order;

    @BindView(R.id.vp_order)
    ViewPager vp_order;
    private String[]title={"全部订单","待付款","已付款"};//,"待评价"
    @Override
    protected int getContentView() {
        return R.layout.order_frag;
    }

    public static OrderFragment newInstance(String flag) {
        Bundle args = new Bundle();
        args.putString(Constant.flag, flag);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter=new FragmentAdapter(getChildFragmentManager());
        adapter.setTitle(title);

        List<Fragment> list=new ArrayList<>();
        list.add(OrderListFragment.newInstance(getArguments().getString(Constant.flag),OrderListFragment.type_0));
        list.add(OrderListFragment.newInstance(getArguments().getString(Constant.flag),OrderListFragment.type_1));
        list.add(OrderListFragment.newInstance(getArguments().getString(Constant.flag),OrderListFragment.type_2));
//        list.add(OrderListFragment.newInstance(getArguments().getString(Constant.flag),OrderListFragment.type_3));
        adapter.setList(list);


        vp_order.setAdapter(adapter);
        vp_order.setOffscreenPageLimit(list.size()-1);
        tab_order.setupWithViewPager(vp_order);

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

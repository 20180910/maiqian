package com.sk.maiqian.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/21.
 */

public class HomeHotZiXunFragment extends BaseFragment {
    @BindView(R.id.rv_zi_xun)
    RecyclerView rv_zi_xun;
    private MyLoadMoreAdapter<String> adapter;


    @Override
    protected int getContentView() {
        return R.layout.hotzixun_frag;
    }


    public static HomeHotZiXunFragment newInstance() {
        Bundle args = new Bundle();
        HomeHotZiXunFragment fragment = new HomeHotZiXunFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

        adapter =new MyLoadMoreAdapter<String>(mContext,R.layout.hotzixun_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);

        rv_zi_xun.setLayoutManager(new LinearLayoutManager(mContext));
//        rv_zi_xun.setNestedScrollingEnabled(false);
        rv_zi_xun.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_zi_xun.setAdapter(adapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

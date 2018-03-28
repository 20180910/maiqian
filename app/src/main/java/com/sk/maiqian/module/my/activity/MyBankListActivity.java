package com.sk.maiqian.module.my.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyBankListActivity extends BaseActivity {
    @BindView(R.id.rv_mybank)
    RecyclerView rv_mybank;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的银行卡");
        setAppRightImg(R.drawable.share);
        return R.layout.mybanklist_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.mybanklist_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);
        rv_mybank.setLayoutManager(new LinearLayoutManager(mContext));
        rv_mybank.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_mybank.setAdapter(adapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

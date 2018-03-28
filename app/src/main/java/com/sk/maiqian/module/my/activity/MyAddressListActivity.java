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

public class MyAddressListActivity extends BaseActivity {
    @BindView(R.id.rv_my_address)
    RecyclerView rv_my_address;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的地址");
        setAppRightTitle("编辑");
        return R.layout.myaddress_list_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.myaddress_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_address.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_address.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_my_address.setAdapter(adapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

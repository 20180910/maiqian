package com.sk.maiqian.module.order.fragment;

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
 * Created by Administrator on 2018/3/30.
 */

public class OrderListFragment extends BaseFragment {
    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.orderlist_frag;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.orderlist_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_order_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_order_list.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_order_list.setAdapter(adapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

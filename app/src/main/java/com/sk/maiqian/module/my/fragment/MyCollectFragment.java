package com.sk.maiqian.module.my.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MyCollectFragment extends BaseFragment {
    @BindView(R.id.rv_my_collection)
    RecyclerView rv_my_collection;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.mycollection_frag;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.mycollection_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

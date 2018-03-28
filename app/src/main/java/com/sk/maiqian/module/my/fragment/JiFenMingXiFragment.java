package com.sk.maiqian.module.my.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class JiFenMingXiFragment extends BaseFragment {
    @BindView(R.id.rv_jifenmingxi)
    RecyclerView rv_jifenmingxi;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.jifenmingxi_frag;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.jifenmingxi_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);
        rv_jifenmingxi.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

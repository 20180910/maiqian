package com.sk.maiqian.module.my.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class FenXiaoActivity extends BaseActivity {
    @BindView(R.id.rv_fenxiao)
    RecyclerView rv_fenxiao;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的分销码");
        return R.layout.fenxiao_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.fenxiao_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_fenxiao.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

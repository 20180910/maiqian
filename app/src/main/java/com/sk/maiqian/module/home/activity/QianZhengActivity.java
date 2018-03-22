package com.sk.maiqian.module.home.activity;

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
 * Created by Administrator on 2018/3/22.
 */

public class QianZhengActivity extends BaseActivity {
    @BindView(R.id.rv_guojia_qianzheng)
    RecyclerView rv_guojia_qianzheng;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
        return R.layout.guojiaqianzheng_act;
    }

    @Override
    protected void initView() {
        setAppTitle("美国签证");


        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.guojiaqianzheng_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(6);
        adapter.setOnLoadMoreListener(this);
        rv_guojia_qianzheng.setLayoutManager(new LinearLayoutManager(mContext));
        rv_guojia_qianzheng.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_guojia_qianzheng.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

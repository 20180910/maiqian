package com.sk.maiqian.module.my.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.rv_message)
    RecyclerView rv_message;

    MyLoadMoreAdapter adapter;

    TextView textview;

    @Override
    protected int getContentView() {
        setAppTitle("我的消息");
        return R.layout.mymessage_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.mymessage_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        textview.setMaxLines(Integer.MAX_VALUE);
        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);
        rv_message.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

package com.sk.maiqian.module.liuxue.activity;

import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LiuXueActivity extends BaseActivity {

    @BindView(R.id.rv_youxue)
    MyRecyclerView rv_youxue;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("留学");
        setAppRightImg(R.drawable.share);
        return R.layout.liuxue_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.youxue_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_youxue.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

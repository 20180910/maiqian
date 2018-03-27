package com.sk.maiqian.module.yingyupeixun.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26.
 */

public class LaoShiJieShaoActivity extends BaseActivity {
    @BindView(R.id.rv_zhuyao_shouke)
    RecyclerView rv_zhuyao_shouke;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("老师介绍");
        setAppRightImg(R.drawable.share);
        return R.layout.laoshijieshao_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.laoshijieshao_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_zhuyao_shouke.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

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
 * Created by Administrator on 2018/3/23.
 */

public class ZaiZhiRenYuanActivity extends BaseActivity {
    @BindView(R.id.rv_zaizhirenyuan)
    RecyclerView rv_zaizhirenyuan;

    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        setAppTitle("在职人员");
        setAppRightImg(R.drawable.share);
        return R.layout.zaizhirenyuan_act;
    }

    @Override
    protected void initView() {

        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.zaizhirenyuan_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(12);
        adapter.setOnLoadMoreListener(this);
        rv_zaizhirenyuan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_zaizhirenyuan.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_zaizhirenyuan.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

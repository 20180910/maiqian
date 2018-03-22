package com.sk.maiqian.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/22.
 */

public class QianZhengDaiBanActivity extends BaseActivity {
    @BindView(R.id.tv_qianzheng_dingwei)
    TextView tvQianzhengDingwei;
    @BindView(R.id.et_qianzheng_search)
    MyEditText etQianzhengSearch;
    @BindView(R.id.rv_qianzhengdaiban)
    MyRecyclerView rvQianzhengdaiban;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("签证代办");
        setAppRightImg(R.drawable.share);
        return R.layout.qianzhengdaiban_act;
    }

    @Override
    protected void initView() {

        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.qianzhengdaiban_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        STActivity(QianZhengActivity.class);
                    }
                });
            }
        };

        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);
        rvQianzhengdaiban.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv_qianzheng_liuyan)
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.iv_qianzheng_liuyan:
                STActivity(WoYaoLiuYanActivity.class);
                break;
            case R.id.app_right_iv:
                break;
        }
    }
}

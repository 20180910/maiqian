package com.sk.maiqian.module.home.activity;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/22.
 */

public class DaYiJieHuoActivity extends BaseActivity {
    @BindView(R.id.et_dayijiehuo)
    EditText et_dayijiehuo;
    @BindView(R.id.tab_dayijiehuo)
    TabLayout tab_dayijiehuo;
    @BindView(R.id.rv_dayijiehuo)
    MyRecyclerView rv_dayijiehuo;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("答疑解惑");
        return R.layout.dayijiehuo_act;
    }

    @Override
    protected void initView() {

        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.dayijiehuo_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(10);
        adapter.setOnLoadMoreListener(this);

        rv_dayijiehuo.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

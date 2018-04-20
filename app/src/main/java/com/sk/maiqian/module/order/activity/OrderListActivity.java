package com.sk.maiqian.module.order.activity;

import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/30.
 */

public class OrderListActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("服务/订单");
        setAppRightImg(R.drawable.share);
        hiddenBackIcon();
        return R.layout.ordertype_frag;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

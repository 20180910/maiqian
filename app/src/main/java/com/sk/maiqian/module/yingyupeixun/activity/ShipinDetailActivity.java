package com.sk.maiqian.module.yingyupeixun.activity;

import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ShipinDetailActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("视频详情");
        setAppRightImg(R.drawable.share);
        return R.layout.shipindetail_act;
    }

    @Override
    protected void initView() {
        setTitleBackgroud(R.color.transparent);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

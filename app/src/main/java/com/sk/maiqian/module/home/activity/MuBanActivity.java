package com.sk.maiqian.module.home.activity;

import android.view.View;
import android.widget.ImageView;

import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/16.
 */

public class MuBanActivity extends BaseActivity {
    @BindView(R.id.iv_muban)
    ImageView iv_muban;
    @Override
    protected int getContentView() {
        setAppTitle("模板");
        return R.layout.muban_act;
    }

    @Override
    protected void initView() {
        String imgUrl = getIntent().getStringExtra(IntentParam.imgUrl);
        GlideUtils.into(mContext,imgUrl,iv_muban);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

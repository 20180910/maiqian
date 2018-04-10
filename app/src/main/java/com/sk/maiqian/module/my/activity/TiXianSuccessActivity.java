package com.sk.maiqian.module.my.activity;

import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class TiXianSuccessActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("提现");
        setAppRightImg(R.drawable.share);
        return R.layout.tixiansuccess_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_tixiansuccess_jilu, R.id.tv_tixiansuccess_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tixiansuccess_jilu:
                    STActivity(JiFenMingXiActivity.class);
                break;
            case R.id.tv_tixiansuccess_back:
                    finish();
                break;
        }
    }
}

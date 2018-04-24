package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.view.View;

import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/23.
 */

public class PaySuccessActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("付款成功");
        return R.layout.paysuccess_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_pay_result})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_pay_result:
                Intent intent=new Intent(IntentParam.Action.paySuccess);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
                finish();
            break;
        }
    }

}

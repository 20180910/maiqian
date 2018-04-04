package com.sk.maiqian.module.my.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.module.my.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/3/27.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.ll_my_setting)
    LinearLayout ll_my_setting;

    @BindView(R.id.tv_my_phone)
    TextView tv_my_phone;
    @Override
    protected int getContentView() {
        return R.layout.my_frag;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_my, R.id.ll_my_qianzheng_order, R.id.ll_my_yingyu_order, R.id.ll_my_liuxue, R.id.ll_my_youxue, R.id.ll_my_jifen, R.id.ll_my_bank, R.id.ll_my_address, R.id.ll_my_collection, R.id.ll_my_message, R.id.ll_my_setting, R.id.ll_my_fenxiao})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my:
                break;
            case R.id.ll_my_qianzheng_order:
                break;
            case R.id.ll_my_yingyu_order:
                break;
            case R.id.ll_my_liuxue:
                break;
            case R.id.ll_my_youxue:
                break;
            case R.id.ll_my_jifen:
                break;
            case R.id.ll_my_bank:
                break;
            case R.id.ll_my_address:
                break;
            case R.id.ll_my_collection:
                break;
            case R.id.ll_my_message:
                break;
            case R.id.ll_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.ll_my_fenxiao:
                break;
        }
    }
}

package com.sk.maiqian.module.yingyupeixun.activity;

import android.view.View;

import com.github.customview.MyEditText;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/22.
 */

public class PeiXunWoYaoLiuYanActivity extends BaseActivity {
    @BindView(R.id.et_liuyan_name)
    MyEditText et_liuyan_name;
    @BindView(R.id.et_liuyan_phone)
    MyEditText et_liuyan_phone;

    @Override
    protected int getContentView() {
        setAppTitle("我要留言");
        setAppRightImg(R.drawable.share);
        return R.layout.englishpeixunwoyaoliuyan_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_liuyan_time, R.id.tv_liuyan_guojia, R.id.tv_liuyan_type, R.id.tv_commit_liuyan})
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.app_right_iv:
                break;
            case R.id.tv_liuyan_time:
                break;
            case R.id.tv_liuyan_guojia:
                break;
            case R.id.tv_liuyan_type:
                break;
            case R.id.tv_commit_liuyan:
                break;
        }
    }

}

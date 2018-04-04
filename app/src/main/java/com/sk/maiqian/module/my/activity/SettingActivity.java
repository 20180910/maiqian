package com.sk.maiqian.module.my.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.github.baseclass.view.MyDialog;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.event.LoginSuccessEvent;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.sb_setting)
    SwitchButton sb_setting;
    @BindView(R.id.tv_setting_cache)
    TextView tv_setting_cache;

    @Override
    protected int getContentView() {
        setAppTitle("设置");
        setAppRightImg(R.drawable.share);
        return R.layout.setting_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_setting_clear_cache, R.id.ll_setting_share, R.id.ll_setting_resetpwd, R.id.ll_setting_yjfk, R.id.tv_setting_exit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_clear_cache:
                break;
            case R.id.ll_setting_share:
                break;
            case R.id.ll_setting_resetpwd:
                break;
            case R.id.ll_setting_yjfk:
                break;
            case R.id.tv_setting_exit:
                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认退出登录?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        clearUserId();
                        MyRxBus.getInstance().post(new LoginSuccessEvent(LoginSuccessEvent.status_0));

                        finish();
                    }
                });
                mDialog.create().show();
                break;
        }
    }
}

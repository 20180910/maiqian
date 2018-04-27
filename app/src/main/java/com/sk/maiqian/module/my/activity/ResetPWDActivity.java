package com.sk.maiqian.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class ResetPWDActivity extends BaseActivity {
    @BindView(R.id.et_resetpwd_pwd)
    MyEditText et_resetpwd_pwd;
    @BindView(R.id.et_resetpwd_repwd)
    MyEditText et_resetpwd_repwd;
    private String phone,smsCode;

    @Override
    protected int getContentView() {
        setAppTitle("重置密码");
        setAppRightImg(R.drawable.share);
        return R.layout.resetpwd_act;
    }

    @Override
    protected void initView() {
        phone = getIntent().getStringExtra(IntentParam.phone);
        smsCode = getIntent().getStringExtra(IntentParam.smsCode);
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.tv_resetpwd_commit)
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_resetpwd_commit:
                String pwd = getSStr(et_resetpwd_pwd);
                String repwd = getSStr(et_resetpwd_repwd);
                if(!ZhengZeUtils.isShuZiAndZiMu(pwd)||pwd.length()>12||pwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }else if(!TextUtils.equals(pwd,repwd)){
                    showMsg("两次密码不一样");
                    return;
                }
                updatePWD(pwd);
            break;
        }
    }

    private void updatePWD(String pwd) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",phone);
        map.put("sms_code",smsCode);
        map.put("newPassword",pwd);
        map.put("sign",getSign(map));
        ApiRequest.forgetPWD(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}

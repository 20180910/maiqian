package com.sk.maiqian.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/29.
 */

public class EditPhoneActivity extends BaseActivity {
    @BindView(R.id.et_editphone_pwd)
    MyEditText et_editphone_pwd;
    @BindView(R.id.et_editphone_phone)
    MyEditText et_editphone_phone;
    @BindView(R.id.tv_editphone_getmsg)
    TextView tv_editphone_getmsg;
    @BindView(R.id.et_editphone_code)
    MyEditText et_editphone_code;
    @BindView(R.id.tv_editphone_save)
    MyTextView tv_editphone_save;
    private String smsCode;

    @Override
    protected int getContentView() {
        setAppTitle("绑定手机");
        return R.layout.editphone_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_editphone_getmsg, R.id.tv_editphone_save})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_editphone_getmsg:
                String phone = getSStr(et_editphone_phone);
                if (TextUtils.isEmpty(phone) || ZhengZeUtils.notMobile(phone)) {
                    showMsg("请输入正确手机号");
                    return;
                }
                getMsg(phone);
                break;
            case R.id.tv_editphone_save:
                String pwd = getSStr(et_editphone_pwd);
                String phoneStr = getSStr(et_editphone_phone);
                String code = getSStr(et_editphone_code);
                if(TextUtils.isEmpty(pwd)){
                    showMsg("请输入原密码");
                    return;
                }else if (TextUtils.isEmpty(phoneStr)) {
                    showMsg("请输入手机号");
                    return;
                }else if (TextUtils.isEmpty(smsCode) || !TextUtils.equals(code, smsCode)) {
                    showMsg("请输入正确验证码");
                    return;
                }
                savePhone(pwd,phoneStr,code);
                break;
        }
    }

    private void savePhone(String pwd, String phoneStr, String code) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("oldPassword",pwd);
        map.put("mobile",phoneStr);
        map.put("sms_code",code);
        map.put("sign",getSign(map));
        ApiRequest.updatePhone(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                SPUtils.setPrefString(mContext, AppXml.mobile,phoneStr);
                finish();
            }
        });

    }

    private void getMsg(String phone) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mobile",phone);
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        NetApiRequest.getMsgCode(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                smsCode = obj.getSMSCode();
                countDown(tv_editphone_getmsg);
            }
        });

    }
}

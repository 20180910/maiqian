package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class FindPWDActivity extends BaseActivity {
    @BindView(R.id.et_findpwd_phone)
    MyEditText et_findpwd_phone;
    @BindView(R.id.et_findpwd_code)
    MyEditText et_findpwd_code;
    @BindView(R.id.tv_findpwd_getmsg)
    MyTextView tv_findpwd_getmsg;

    @Override
    protected int getContentView() {
        setAppTitle("找回密码");
        setAppRightImg(R.drawable.share);
        return R.layout.findpwd_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_findpwd_getmsg, R.id.tv_findpwd_next})
    public void onViewClick(View view) {
        String phone;
        switch (view.getId()) {
            case R.id.tv_findpwd_getmsg:
                phone = getSStr(et_findpwd_phone);
                if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }
                getMsgCode(phone);
                break;
            case R.id.tv_findpwd_next:
                phone= getSStr(et_findpwd_phone);
                String code= getSStr(et_findpwd_code);
                if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(smsCode)||!TextUtils.equals(code,smsCode)){
                    showMsg("请输入正确验证码");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra(IntentParam.phone, phone);
                intent.putExtra(IntentParam.smsCode, smsCode);
                STActivityForResult(intent,ResetPWDActivity.class,100);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    finish();
                break;
            }
        }
    }

    private String smsCode;
    private void getMsgCode(String phone) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("mobile",phone);
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        NetApiRequest.getMsgCode(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                smsCode = obj.getSMSCode();
                countDown(tv_findpwd_getmsg);
            }
        });
    }
}

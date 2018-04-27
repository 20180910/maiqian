package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.customview.MyCheckBox;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.request.RegisterBody;
import com.sk.maiqian.network.NetApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_register_phone)
    MyEditText et_register_phone;
    @BindView(R.id.et_register_code)
    MyEditText et_register_code;
    @BindView(R.id.et_register_pwd)
    MyEditText et_register_pwd;
    @BindView(R.id.et_register_repwd)
    MyEditText et_register_repwd;
    @BindView(R.id.et_register_fenxiao)
    MyEditText et_register_fenxiao;
    @BindView(R.id.cb_register_xieyi)
    MyCheckBox cb_register_xieyi;
    @BindView(R.id.tv_register_getmsg)
    TextView tv_register_getmsg;
//    @BindView(R.id.iv_register_bg)
//    ImageView iv_register_bg;
    private String smsCode;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
        setTitleBackgroud(R.color.transparent);
        return R.layout.register_act;
    }

    @Override
    protected void initView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height= PhoneUtils.getScreenHeight(mContext);

//        iv_register_bg.setLayoutParams(layoutParams);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_register_getmsg, R.id.tv_register, R.id.tv_register_xieyi, R.id.tv_register_login})
    public void onViewClick(View view) {
        String phone;
        switch (view.getId()) {
            case R.id.tv_register_getmsg:
                phone = getSStr(et_register_phone);
                if(TextUtils.isEmpty(phone)||ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }
                getMsgCode(phone);
                break;
            case R.id.tv_register:
                phone = getSStr(et_register_phone);
                String code = getSStr(et_register_code);
                String pwd = getSStr(et_register_pwd);
                String repwd = getSStr(et_register_repwd);
                String fenxiao = getSStr(et_register_fenxiao);
                if(!cb_register_xieyi.isChecked()){
                    showMsg("请同意用户协议");
                    return;
                }else if(TextUtils.isEmpty(phone)||ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(smsCode)||!TextUtils.equals(code,smsCode)){
                    showMsg("请输入正确验证码");
                    return;
                }else if(!ZhengZeUtils.isShuZiAndZiMu(pwd)||pwd.length()>12||pwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }else if(!TextUtils.equals(pwd,repwd)){
                    showMsg("两次密码不一样");
                    return;
                }
                register(phone,code,pwd,fenxiao);
                break;
            case R.id.tv_register_xieyi:
                STActivity(XieYiActivity.class);
                break;
            case R.id.tv_register_login:
                finish();
                break;
        }
    }

    private void register(String phone, String code, String pwd,String fenxiao) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        RegisterBody body=new RegisterBody();
        body.setUsername(phone);
        body.setPassword(pwd);
        body.setSms_code(code);
        body.setDistribution_yard(fenxiao);
        ApiRequest.register(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                Intent intent=new Intent();
                intent.putExtra(IntentParam.phone,phone);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

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
                countDown(tv_register_getmsg);
            }
        });

    }
}

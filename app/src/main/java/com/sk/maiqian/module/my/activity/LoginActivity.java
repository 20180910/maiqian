package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.BuildConfig;
import com.sk.maiqian.Constant;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.event.LoginSuccessEvent;
import com.sk.maiqian.module.home.activity.MainActivity;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.LoginObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_phone)
    MyEditText et_login_phone;
    @BindView(R.id.et_login_pwd)
    MyEditText et_login_pwd;

    private int flag = 0;

    @Override
    protected int getContentView() {
        setAppTitle("登录");
        setAppRightImg(R.drawable.share);
        return R.layout.login_act;
    }

    @Override
    protected void initView() {
        if (BuildConfig.DEBUG) {
            /*iv_login_test.setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    flag++;
                    et_login_pwd.setText("123456");
                    if(flag%3==0){
                        et_login_phone.setText("18117352720");
                    }else if(flag%3==1){
                        et_login_phone.setText("13122753707");//zp
                    }else if(flag%3==2){
                        et_login_phone.setText("18202198781");//wg
                        et_login_pwd.setText("12345678ax");
                    }
//                    et_login_phone.setText("13122753707");
                }
            });*/
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!"0".equals(getUserId())) {
            STActivity(MainActivity.class);
            finish();
        }
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.tv_login, R.id.tv_login_forgetpwd, R.id.tv_login_register, R.id.iv_login_forqq, R.id.iv_login_forwx})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                String phone = getSStr(et_login_phone);
                String pwd = getSStr(et_login_pwd);
                if(TextUtils.isEmpty(phone)){
                    showMsg("请输入手机号");
                    return;
                }else if(TextUtils.isEmpty(pwd)){
                    showMsg("请输入密码");
                    return;
                }
                login(phone,pwd);
                break;
            case R.id.tv_login_forgetpwd:
                STActivity(FindPWDActivity.class);
                break;
            case R.id.tv_login_register:
                STActivityForResult(RegisterActivity.class,100);
                break;
            case R.id.iv_login_forqq:
                break;
            case R.id.iv_login_forwx:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    if (data != null) {
                        et_login_phone.setText(data.getStringExtra(IntentParam.phone));
                        et_login_pwd.requestFocus();
                    } else {
                        et_login_phone.requestFocus();
                    }
                    break;
            }
        }
    }

    private void login(String phone, String pwd) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", phone);
        map.put("password", pwd);
        map.put("RegistrationID", SPUtils.getString(mContext, Constant.registrationId, "0"));
        map.put("sign", getSign(map));
        ApiRequest.userLogin(map, new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {
                loginResult(obj);
            }
        });
    }

    private void loginResult(LoginObj obj) {

        SPUtils.setPrefString(mContext, AppXml.user_id, obj.getUser_id());
        SPUtils.setPrefString(mContext, AppXml.user_name, obj.getUser_name());
        SPUtils.setPrefString(mContext, AppXml.nick_name, obj.getNick_name());
        SPUtils.setPrefString(mContext, AppXml.avatar, obj.getAvatar());
        SPUtils.setPrefString(mContext, AppXml.mobile, obj.getMobile());
        SPUtils.setPrefInt(mContext, AppXml.message_sink, obj.getMessage_sink());
        SPUtils.setPrefFloat(mContext, AppXml.message_sink, (float) obj.getPoint());
//        SPUtils.setPrefString(mContext, AppXml.sex, obj.getSex());
//        SPUtils.setPrefString(mContext, AppXml.birthday, obj.getBirthday());

//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Config.Bro.operation));
        MyRxBus.getInstance().post(new LoginSuccessEvent());
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        STActivity(intent, MainActivity.class);


        finish();

    }

    private long mExitTime;



    /*@Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            if (Config.exitAPP.equals(1)) {
                Intent intent = new Intent(Config.exitAPP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent, MainActivity.class);
            }
            super.onBackPressed();
        }
    }*/

}

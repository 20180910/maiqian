package com.sk.maiqian.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/4.
 */

public class UpdatePWDActivity extends BaseActivity {
    @BindView(R.id.et_update_pwd_old)
    MyEditText et_update_pwd_old;
    @BindView(R.id.et_update_pwd_new)
    MyEditText et_update_pwd_new;
    @BindView(R.id.et_update_pwd_re)
    MyEditText et_update_pwd_re;

    @Override
    protected int getContentView() {
        setAppTitle("重置登录密码");
        setAppRightImg(R.drawable.share);
        return R.layout.updatepwd_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }



    @OnClick(R.id.tv_update_pwd)
    public void onViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_update_pwd:
                String oldPwd = getSStr(et_update_pwd_old);
                String newPwd =getSStr(et_update_pwd_new);
                String rePwd =getSStr(et_update_pwd_re);
                if(TextUtils.isEmpty(oldPwd)){
                    showMsg("原密码不能为空");
                    return;
                }else if(!ZhengZeUtils.isShuZiAndZiMu(newPwd)||newPwd.length()>12||newPwd.length()<6){
                    showMsg("请输入6-12位数字加字母的密码");
                    return;
                }else if(!TextUtils.equals(newPwd,rePwd)){
                    showMsg("两次密码不一样");
                    return;
                }
                updatePWD(oldPwd,newPwd);
            break;
        }
    }

    private void updatePWD(String oldPwd, String newPwd) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("oldPassword",oldPwd);
        map.put("newPassword",newPwd);
        map.put("sign",getSign(map));
        ApiRequest.updatePWD(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                SPUtils.setPrefBoolean(mContext, AppXml.updatePWD,true);
                finish();
            }
        });

    }
}

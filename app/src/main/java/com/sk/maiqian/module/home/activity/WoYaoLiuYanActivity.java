package com.sk.maiqian.module.home.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.request.QianZhengLiuYanBody;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/22.
 */

public class WoYaoLiuYanActivity extends BaseActivity {
    //类别(1签证留言 2英语培训留言 3游学留言 4留学留言)
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";
    public static final String type_4="4";

    @BindView(R.id.et_liuyan_name)
    MyEditText et_liuyan_name;

    @BindView(R.id.et_liuyan_phone)
    MyEditText et_liuyan_phone;

    @BindView(R.id.et_liuyan_email)
    MyEditText et_liuyan_email;

    @BindView(R.id.et_liuyan_content)
    EditText et_liuyan_content;
    private String type;

    @Override
    protected int getContentView() {
        setAppTitle("我要留言");
        setAppRightImg(R.drawable.share);
        return R.layout.woyaoliuyan_act;
    }

    @Override
    protected void initView() {

        type = getIntent().getStringExtra(IntentParam.qianZhengType);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_commit_liuyan})
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit_liuyan:
                String name = getSStr(et_liuyan_name);
                String phone = getSStr(et_liuyan_phone);
                String email = getSStr(et_liuyan_email);
                String content = getSStr(et_liuyan_content);
                if(TextUtils.isEmpty(name)){
                    showMsg("请输入姓名");
                    return;
                }else if(ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确联系电话");
                    return;
                }else if(ZhengZeUtils.notEmail(email)){
                    showMsg("请输入正确邮箱");
                    return;
                }else if(TextUtils.isEmpty(content)){
                    showMsg("请输入留言内容");
                    return;
                }
                liuYan(name,phone,email,content);
                break;
        }
    }

    private void liuYan(String name, String phone, String email,String content) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("type",type);
        map.put("sign",getSign(map));
        QianZhengLiuYanBody body=new QianZhengLiuYanBody();
        body.setName(name);
        body.setContent(content);
        body.setEmail(email);
        body.setPhone(phone);

        ApiRequest.qianZhengLiuYan(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                finish();
            }
        });

    }

}

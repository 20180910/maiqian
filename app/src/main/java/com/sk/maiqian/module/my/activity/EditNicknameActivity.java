package com.sk.maiqian.module.my.activity;

import android.text.TextUtils;
import android.view.View;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.request.EditNickNameBody;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/29.
 */

public class EditNicknameActivity extends BaseActivity {
    @BindView(R.id.et_editnickname_name)
    MyEditText et_editnickname_name;
    @BindView(R.id.tv_editnickname_save)
    MyTextView tv_editnickname_save;

    @Override
    protected int getContentView() {
        setAppTitle("编辑昵称");
        return R.layout.editnickname_act;
    }

    @Override
    protected void initView() {
        String nickName = getIntent().getStringExtra(IntentParam.nickName);
        if (nickName != null) {
            et_editnickname_name.setText(nickName);
        }

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_editnickname_save})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_editnickname_save:
                String name = getSStr(et_editnickname_name);
                if(TextUtils.isEmpty(name)){
                    showMsg("请输入昵称");
                    return;
                }
                saveName(name);
                break;
        }
    }

    private void saveName(String name) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        EditNickNameBody body=new EditNickNameBody();
        body.setNickname(name);
        body.setUser_id(getUserId());
        ApiRequest.editNickName(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                SPUtils.setPrefString(mContext, AppXml.nick_name,name);
                finish();
            }
        });

    }
}


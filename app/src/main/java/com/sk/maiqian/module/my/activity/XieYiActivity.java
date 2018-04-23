package com.sk.maiqian.module.my.activity;

import android.view.View;

import com.library.base.BaseObj;
import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3.
 */

public class XieYiActivity extends BaseActivity {
    @BindView(R.id.re_xieyi)
    RichEditor re_xieyi;

    @Override
    protected int getContentView() {
        setAppTitle("麦签用户协议");
        return R.layout.xieyi_act;
    }

    @Override
    protected void initView() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getXieYi(map, new MyCallBack<BaseObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                re_xieyi.setInputEnabled(false);
                re_xieyi.setHtml(obj.getUser_agreement());
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

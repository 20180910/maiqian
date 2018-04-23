package com.sk.maiqian.module.my.activity;

import android.graphics.Color;
import android.view.View;

import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.JiFenShuoMingObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class JiFenShuoMingActivity extends BaseActivity {
    @BindView(R.id.re_jifenshuoming)
    RichEditor re_jifenshuoming;
    @Override
    protected int getContentView() {
        setAppTitle("积分说明");
        setAppRightImg(R.drawable.share);
        return R.layout.jifenshuoming_act;
    }

    @Override
    protected void initView() {
        re_jifenshuoming.setEditorFontColor(Color.parseColor("#666666"));
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getJiFenShuoMing(map, new MyCallBack<JiFenShuoMingObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(JiFenShuoMingObj obj, int errorCode, String msg) {
                re_jifenshuoming.setInputEnabled(false);
                re_jifenshuoming.setHtml(obj.getIntegral_description());
            }
        });
    }

    @Override
    protected void onViewClick(View v) {

    }
}

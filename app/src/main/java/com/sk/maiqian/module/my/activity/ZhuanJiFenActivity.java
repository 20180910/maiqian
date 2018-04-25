package com.sk.maiqian.module.my.activity;

import android.view.View;
import android.widget.ImageView;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.activity.QianZhengDaiBanActivity;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.JiFenShuoMingObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class ZhuanJiFenActivity extends BaseActivity {
    @BindView(R.id.iv_zhuanjifen)
    ImageView iv_zhuanjifen;

    @Override
    protected int getContentView() {
        setAppTitle("赚积分");
        setAppRightImg(R.drawable.share);
        return R.layout.zhuanjifen_act;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getJiFenShuoMing(map, new MyCallBack<JiFenShuoMingObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(JiFenShuoMingObj obj, int errorCode, String msg) {
                GlideUtils.into(mContext,obj.getImg_url(),iv_zhuanjifen);
            }
        });
    }

    @OnClick({R.id.ll_zhuanjifen_xiadan, R.id.ll_zhuanjifen_yaoqing})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zhuanjifen_xiadan:
                STActivity(QianZhengDaiBanActivity.class);
                break;
            case R.id.ll_zhuanjifen_yaoqing:
                showFenXiangDialog();
                break;
        }
    }
}

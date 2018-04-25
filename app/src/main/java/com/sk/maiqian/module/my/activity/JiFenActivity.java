package com.sk.maiqian.module.my.activity;

import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class JiFenActivity extends BaseActivity {
    @BindView(R.id.tv_jifen)
    TextView tv_jifen;

    @Override
    protected int getContentView() {
        setAppTitle("");
        setAppRightImg(R.drawable.share);
        return R.layout.jifen_act;
    }

    @Override
    protected void initView() {
//        showProgress();
//        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
//        Map<String,String> map=new HashMap<String,String>();
//        map.put("user_id",getUserId());
//        map.put("sign",getSign(map));
//        ApiRequest.method(map, new MyCallBack<BaseObj>(mContext,pl_load,pcfl) {
//            @Override
//            public void onSuccess(BaseObj obj) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_jifen.setText(SPUtils.getString(mContext, AppXml.jifen,"0")+"积分");
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_jifen_shuoming, R.id.tv_jifen_zhuanjifen, R.id.tv_jifen_mingxi,R.id.tv_jifen_tx})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jifen_tx:
                STActivity(TiXianActivity.class);
                break;
            case R.id.tv_jifen_shuoming:
                STActivity(JiFenShuoMingActivity.class);
                break;
            case R.id.tv_jifen_zhuanjifen:
                showFenXiangDialog();
//                STActivity(ZhuanJiFenActivity.class);
                break;
            case R.id.tv_jifen_mingxi:
                STActivity(JiFenMingXiActivity.class);
                break;
        }
    }

}

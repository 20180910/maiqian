package com.sk.maiqian.module.yingyupeixun.activity;

import android.view.View;
import android.webkit.WebView;

import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26.
 */

public class YinpinDetailActivity extends BaseActivity {
    @BindView(R.id.web_yinpin)
    WebView web_yinpin;
    @Override
    protected int getContentView() {
        setAppTitle("音频详情");
        setAppRightImg(R.drawable.share);
        return R.layout.yinpindetail_act;
    }

    @Override
    protected void initView() {
        String webUrl = getIntent().getStringExtra(IntentParam.webUrl);
        initWebViewForUrl(web_yinpin,webUrl);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_yinpin.destroy();
    }
}

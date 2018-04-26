package com.sk.maiqian.module.yingyupeixun.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

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
    @BindView(R.id.pv_progress)
    ProgressBar pv_progress;
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
        web_yinpin.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log("==="+newProgress);
                if(newProgress==100){
                    pv_progress.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    pv_progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pv_progress.setProgress(newProgress);//设置进度值
                }
            }
        });
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

package com.sk.maiqian.module.yingyupeixun.activity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.rxbus.MyConsumer;
import com.github.rxbus.MyRxBus;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
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
    @BindView(R.id.pb_progress)
    ProgressBar pb_progress;
    private MyFlowableSubscriber<Integer> myFlowableSubscriber;

    @Override
    protected int getContentView() {
        setAppTitle("音频详情");
        setAppRightImg(R.drawable.share);
        return R.layout.yinpindetail_act;
    }
    public class testevent{
        public int progress;

        public testevent(int progress) {
            this.progress = progress;
        }
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEvent(testevent.class, new MyConsumer<testevent>() {
            @Override
            public void onAccept(testevent event) {
                int now = pb_progress.getProgress();
                for (int i = now; i <=event.progress ; i++) {
//                    Log("========="+i);
                    pb_progress.setProgress(i);
                }
            }
        });
    }

    @Override
    protected void initView() {

        String webUrl = getIntent().getStringExtra(IntentParam.webUrl);
        initWebViewForUrl(web_yinpin, webUrl);
        web_yinpin.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                MyRxBus.getInstance().post(new testevent(newProgress));
                if (newProgress == 100) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setDuration(1500);//持续时间
                    pb_progress.startAnimation(alphaAnimation);
                    pb_progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pb_progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
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

package com.sk.maiqian.module.yingyupeixun.activity;

import android.os.SystemClock;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.github.rxbus.MyConsumer;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

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

    public class testevent {
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
                event.progress = event.progress * 10000 / 100;
                int now = pb_progress.getProgress();
                for (int i = now; i <= event.progress; i++) {
                    Log("=========" + i);
                    pb_progress.setProgress(i);
                }
            }
        });
    }

    int totalProgress=0;
    int myProgress=0;
    int sleepFlag=10;
    public void setProgress() {
        MyRx.start(new MyFlowableSubscriber<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) {
                while (true){
                    if(myProgress>=1000){
                        emitter.onComplete();
                        break;
                    }else if(loadError){
                        emitter.onError(new Throwable("加载失败"));
                        break;
                    }
                    if(totalProgress>=1000){
                        myProgress++;
                        sleepFlag=1;
                        emitter.onNext(myProgress);
                    }else{
                        myProgress++;
                        sleepFlag=10;
                        emitter.onNext(myProgress);
                    }
                    Log(myProgress+"===-"+totalProgress);
                    SystemClock.sleep(sleepFlag);
                }
            }
            @Override
            public void onNext(Integer obj) {
                myProgress++;
                pb_progress.setProgress(myProgress);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                alphaAnimation.setDuration(1300);//持续时间
                pb_progress.startAnimation(alphaAnimation);
                pb_progress.setVisibility(View.GONE);//加载完网页进度条消失
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
//                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//                alphaAnimation.setDuration(1300);//持续时间
//                pb_progress.startAnimation(alphaAnimation);
                pb_progress.setVisibility(View.GONE);//加载完网页进度条消失
            }
        });
    }
    boolean loadError;
    @Override
    protected void initView() {
        pb_progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
        String webUrl = getIntent().getStringExtra(IntentParam.webUrl);
        initWebViewForUrl(web_yinpin, webUrl);
        web_yinpin.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                loadError=true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                loadError=true;
            }
        });
        web_yinpin.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                totalProgress=newProgress*1000/100;
                Log("==="+totalProgress);
//                MyRxBus.getInstance().post(new testevent(newProgress));
                /*if (newProgress == 100) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setDuration(1300);//持续时间
                    pb_progress.startAnimation(alphaAnimation);
                    pb_progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pb_progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                }*/
            }
        });

        setProgress();
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

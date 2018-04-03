package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.customview.MyRadioButton;
import com.github.rxbus.MyConsumer;
import com.sk.maiqian.Config;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.event.LoginSuccessEvent;
import com.sk.maiqian.module.home.fragment.HomeFragment;
import com.sk.maiqian.module.home.fragment.MyFragment;
import com.sk.maiqian.module.home.fragment.SelectFragment;
import com.sk.maiqian.module.my.activity.LoginActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/4.
 */

public class MainActivity extends BaseActivity {
//    @BindView(R.id.status_bar)
//    View status_bar;

    HomeFragment homeFragment;
    SelectFragment selectFragment;
    MyFragment myFragment;

    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rb_home_tab1)
    MyRadioButton rb_home_tab1;

    @BindView(R.id.rb_home_tab2)
    MyRadioButton rb_home_tab2;

    @BindView(R.id.rb_home_tab3)
    MyRadioButton rb_home_tab3;
    private MyRadioButton selectView;


    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {


        homeFragment = new HomeFragment();
        addFragment(R.id.fl_content, homeFragment);

        setTabClickListener();

        setBroadcast();
    }

    private void setTabClickListener() {
        selectView = rb_home_tab1;
        rb_home_tab1.setOnClickListener(getTabClickListener(1));
        rb_home_tab2.setOnClickListener(getTabClickListener(2));
        rb_home_tab3.setOnClickListener(getTabClickListener(3));

    }



    @NonNull
    private MyOnClickListener getTabClickListener(final int index) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                switch (index) {
                    case 1:
                        selectHome();
                        break;
                    case 2:
//                        if (TextUtils.equals(noLoginCode, getUserId())) {
//                            STActivity(LoginActivity.class);
//                            selectView.setChecked(true);
//                        } else {
                        selectChaXun();
//                        }
                        break;
                    case 3:
                        if (TextUtils.equals(noLoginCode, getUserId())) {
                            STActivity(LoginActivity.class);
                            selectView.setChecked(true);
                        } else {
                            selectMy();
                        }
                        break;
                }
            }
        };
    }
    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEvent(LoginSuccessEvent.class, new MyConsumer<LoginSuccessEvent>() {
            @Override
            public void onAccept(LoginSuccessEvent event) {
                selectMy();
            }
        });
    }

    private void selectHome() {
        if (selectView == rb_home_tab1) {
            return;
        }
        selectView = rb_home_tab1;
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            addFragment(R.id.fl_content, homeFragment);
        } else {
            showFragment(homeFragment);
        }
        hideFragment(selectFragment);
        hideFragment(myFragment);
    }

    private void selectChaXun() {
        if (selectView == rb_home_tab2) {
            return;
        }
        selectView = rb_home_tab2;
        if (selectFragment == null) {
            selectFragment = new SelectFragment();
            addFragment(R.id.fl_content, selectFragment);
        } else {
            showFragment(selectFragment);
        }
        hideFragment(homeFragment);
        hideFragment(myFragment);
    }

    private void selectMy() {
        if (selectView == rb_home_tab3) {
            return;
        }
        selectView = rb_home_tab3;
        if (myFragment == null) {
            myFragment = new MyFragment();
            addFragment(R.id.fl_content, myFragment);
        } else {
            showFragment(myFragment);
        }
        hideFragment(homeFragment);
        hideFragment(selectFragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (Config.exitAPP.equals(intent.getAction())) {
            finish();
        }
    }

    @Override
    protected void initData() {

        updateApp();
        getPaymentURL(1);//获取支付宝回传地址
        getPaymentURL(2);//获取微信回传地址
    }

    private void updateApp() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
       /* NetApiRequest.getAPPVersion(map, new MyCallBack<APPVersionObj>(mContext) {
            @Override
            public void onSuccess(APPVersionObj obj) {
                if(obj.getAndroid_version()>getAppVersionCode()){
                    SPUtils.setPrefString(mContext,Config.appDownloadUrl,obj.getAndroid_vs_url());
                    SPUtils.setPrefBoolean(mContext,Config.appHasNewVersion,true);
                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                    mDialog.setMessage("检测到app有新版本是否更新?");
                    mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            FileUtils.deleteFile("shenqihuanka.apk");
                            AppInfo info = new AppInfo();
                            info.setUrl(obj.getAndroid_vs_url());
                            info.setHouZhui(".apk");
                            info.setFileName("yangyu");
                            info.setId(obj.getAndroid_version() + "");
                            MyAPPDownloadService.intentDownload(mContext, info);
                        }
                    });
                    mDialog.create().show();
                }else{
                    SPUtils.setPrefBoolean(mContext, Config.appHasNewVersion,false);
                }
            }
        });*/
    }

    private void getPaymentURL(int type) {
       /* Map<String, String> map = new HashMap<String, String>();
        map.put("payment_type", type + "");
        map.put("sign", GetSign.getSign(map));
        NetApiRequest.paymentURL(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
               *//* if(obj.getPayment_type()==1){
                    SPUtils.setPrefString(mContext,Config.payType_ZFB,obj.getPayment_url());
                }else{
                    SPUtils.setPrefString(mContext,Config.payType_WX,obj.getPayment_url());
                }*//*
            }
        });*/

    }





    protected void onViewClick(View v) {
        switch (v.getId()) {

        }
    }

    private void setBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }


    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            showToastS("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}

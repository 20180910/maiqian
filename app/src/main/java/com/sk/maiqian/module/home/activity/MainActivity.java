package com.sk.maiqian.module.home.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.permission.PermissionCallback;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyRadioButton;
import com.github.rxbus.MyConsumer;
import com.github.rxbus.MyRxBus;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.library.base.bean.AppVersionObj;
import com.library.base.bean.PayObj;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.Config;
import com.sk.maiqian.GetSign;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.bean.AppInfo;
import com.sk.maiqian.event.LoginSuccessEvent;
import com.sk.maiqian.event.SelectOrderEvent;
import com.sk.maiqian.event.SelectPeiXunOrderEvent;
import com.sk.maiqian.module.home.fragment.HomeFragment;
import com.sk.maiqian.module.home.fragment.OrderTypeFragment;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.my.fragment.MyFragment;
import com.sk.maiqian.network.NetApiRequest;
import com.sk.maiqian.network.response.ImageObj;
import com.sk.maiqian.service.MyAPPDownloadService;
import com.sk.maiqian.tools.FileUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.FlowableEmitter;

/**
 * Created by Administrator on 2017/11/4.
 */

public class MainActivity extends BaseActivity {
//    @BindView(R.id.status_bar)
//    View status_bar;

    HomeFragment homeFragment;
    OrderTypeFragment orderTypeFragment;
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
        getYouXueImg();
        getLiuXueImg();

        if(SPUtils.getBoolean(mContext, AppXml.updatePWD,false)){
            clearUserId();
            SPUtils.setPrefBoolean(mContext, AppXml.updatePWD,false);
        }

        homeFragment = new HomeFragment();
        addFragment(R.id.fl_content, homeFragment);

        setTabClickListener();

        setBroadcast();
    }



    private void getYouXueImg() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("type","1");
        map.put("sign",getSign(map));
        NetApiRequest.getYouXueObj(map, new MyCallBack<ImageObj>(mContext) {
            @Override
            public void onSuccess(ImageObj obj, int errorCode, String msg) {
                SPUtils.setPrefString(mContext,AppXml.youXueImg,obj.getImg_url());
            }
        });
    }
    private void getLiuXueImg() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("type","2");
        map.put("sign",getSign(map));
        NetApiRequest.getYouXueObj(map, new MyCallBack<ImageObj>(mContext) {
            @Override
            public void onSuccess(ImageObj obj, int errorCode, String msg) {
                SPUtils.setPrefString(mContext,AppXml.liuXueImg,obj.getImg_url());
            }
        });
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
                        if (TextUtils.equals(noLoginCode, getUserId())) {
                            STActivity(LoginActivity.class);
                            selectView.setChecked(true);
                        } else {
                            selectOrder();
                        }
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
                if(event.status==LoginSuccessEvent.status_1){
                    selectMy();
                }else if(event.status==LoginSuccessEvent.status_0){
                    selectHome();
                }
                selectView.setChecked(true);
            }
        });

        getEvent(SelectOrderEvent.class, new MyConsumer<SelectOrderEvent>() {
            @Override
            public void onAccept(SelectOrderEvent event) {
                selectOrder();
                selectView.setChecked(true);
                MyRxBus.getInstance().postReplay(new SelectPeiXunOrderEvent());
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
        hideFragment(orderTypeFragment);
        hideFragment(myFragment);
    }

    private void selectOrder() {
        if (selectView == rb_home_tab2) {
            return;
        }
        selectView = rb_home_tab2;
        if (orderTypeFragment == null) {
            orderTypeFragment = new OrderTypeFragment();
            addFragment(R.id.fl_content, orderTypeFragment);
        } else {
            showFragment(orderTypeFragment);
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
        hideFragment(orderTypeFragment);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (Config.exitAPP.equals(intent.getAction())) {
            finish();
        }else if(intent.getAction().equals(IntentParam.Action.paySuccess)){
            selectOrder();
            selectView.setChecked(true);
            MyRxBus.getInstance().postReplay(new SelectPeiXunOrderEvent());
        }
    }

    @Override
    protected void initData() {
        updateApp();
        getPaymentURL(1);//获取支付宝回传地址
        getPaymentURL(2);//获取微信回传地址
    }

    private void updateApp() {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",GetSign.getSign(map));
        NetApiRequest.getAppVersion(map, new MyCallBack<AppVersionObj>(mContext) {
            @Override
            public void onSuccess(AppVersionObj obj, int errorCode, String msg) {
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

                            downloadApp(obj);
                        }
                    });
                    mDialog.create().show();
                }else{
                    SPUtils.setPrefBoolean(mContext,Config.appHasNewVersion,false);
                }
            }
        });
    }
    private void downloadApp(AppVersionObj obj) {
        requestPermission(new String[]{Manifest.permission_group.STORAGE}, new PermissionCallback() {
            @Override
            public void onGranted() {
                MyRx.start(new MyFlowableSubscriber<Boolean>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull FlowableEmitter<Boolean> subscriber) {
                        boolean delete = FileUtils.delete(FileUtils.getDownloadDir());
                        subscriber.onNext(delete);
                        subscriber.onComplete();
                    }
                    @Override
                    public void onNext(Boolean aBoolean) {
                        AppInfo info = new AppInfo();
                        info.setUrl(obj.getAndroid_vs_url());
                        info.setHouZhui(".apk");
                        info.setFileName(MyAPPDownloadService.downloadName);
                        info.setId(obj.getAndroid_version() + "");
                        MyAPPDownloadService.intentDownload(mContext, info);
                    }
                });
            }
            @Override
            public void onDenied(String s) {
                showMsg("获取权限失败,无法正常更新,请在设置中打开相关权限");
            }
        });

    }

    private void getPaymentURL(int type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("payment_type", type + "");
        map.put("sign", GetSign.getSign(map));
        NetApiRequest.getPayNotifyUrl(map, new MyCallBack<PayObj>(mContext) {
            @Override
            public void onSuccess(PayObj obj, int errorCode, String msg) {
                if(obj.getPayment_type()==1){
                    SPUtils.setPrefString(mContext,Config.payType_ZFB,obj.getPayment_url());
                }else{
                    SPUtils.setPrefString(mContext,Config.payType_WX,obj.getPayment_url());
                }
            }
        });

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

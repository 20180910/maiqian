package com.sk.maiqian.module.my.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.baseclass.permission.PermissionCallback;
import com.github.baseclass.view.MyDialog;
import com.github.rxbus.MyRxBus;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.library.base.BaseObj;
import com.library.base.tools.CacheUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.event.LoginSuccessEvent;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/3/28.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.sb_setting)
    SwitchButton sb_setting;
    @BindView(R.id.tv_setting_cache)
    TextView tv_setting_cache;
    private int message_sink;

    @Override
    protected int getContentView() {
        setAppTitle("设置");
        setAppRightImg(R.drawable.share);
        return R.layout.setting_act;
    }

    @Override
    protected void initView() {
        message_sink =   SPUtils.getInt(mContext, AppXml.message_sink,0);
        if (message_sink==0) {
            sb_setting.setChecked(false);
        }else {
            sb_setting.setChecked(true);
        }
        sb_setting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    setSwitch();
                }
                return true;
            }
        });
        requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionCallback() {
            @Override
            public void onGranted() {
                RXStart(new MyFlowableSubscriber<String>() {
                    @Override
                    public void subscribe(@NonNull FlowableEmitter<String> emitter) {
                        try {
                            String cacheSize = CacheUtils.getExternalCacheSize(mContext);
                            emitter.onNext(cacheSize);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onNext(String s) {
                        tv_setting_cache.setText(s);
                    }
                });
            }
            @Override
            public void onDenied(String s) {
                showMsg("获取权限失败无法正常读取缓存大小");
            }
        });

    }

    @Override
    protected void initData() {

    }
    private void setSwitch() {
        boolean checked = sb_setting.isChecked();
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("message_sink",!checked?"1":"0");
        map.put("sign", getSign(map));
        ApiRequest.setMessageSink(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                sb_setting.setChecked(!checked);
                SPUtils.setPrefFloat(mContext, AppXml.message_sink,obj.getMessage_sink());
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                sb_setting.setChecked(checked);
            }
        });
    }

    @OnClick({R.id.ll_setting_clear_cache, R.id.ll_setting_share, R.id.ll_setting_resetpwd, R.id.ll_setting_yjfk, R.id.tv_setting_exit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_clear_cache:
                requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionCallback() {
                    @Override
                    public void onGranted() {
                        deleteCache(tv_setting_cache,false);
                    }
                    @Override
                    public void onDenied(String s) {
                        showMsg("获取权限失败无法正常清理缓存");
                    }
                });
                break;
            case R.id.ll_setting_share:
                showFenXiangDialog();
                break;
            case R.id.ll_setting_resetpwd:
                STActivity(UpdatePWDActivity.class);
                break;
            case R.id.ll_setting_yjfk:
                STActivity(YiJianFanKuiActivity.class);
                break;
            case R.id.tv_setting_exit:
                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("是否确认退出登录?");
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

                        clearUserId();
                        MyRxBus.getInstance().post(new LoginSuccessEvent(LoginSuccessEvent.status_0));

                        finish();
                    }
                });
                mDialog.create().show();
                break;
        }
    }
    public void deleteCache(final TextView textView,final boolean isAllCache) {
        RXStart(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> subscriber) {
                CacheUtils.clearAllCache(getApplicationContext());
                try {
                    String totalCacheSize = isAllCache?CacheUtils.getTotalCacheSize(getApplicationContext()):CacheUtils.getExternalCacheSize(getApplicationContext());
                    subscriber.onNext(totalCacheSize);
                    subscriber.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onNext(String totalCacheSize) {
                showMsg("清除成功");
                if(textView!=null){
                    textView.setText(totalCacheSize);
                }
            }
        });

    }
}

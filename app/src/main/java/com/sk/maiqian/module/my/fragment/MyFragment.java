package com.sk.maiqian.module.my.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.event.SelectOrderEvent;
import com.sk.maiqian.module.liuxue.activity.LiuXueShenQingActivity;
import com.sk.maiqian.module.my.activity.FenXiaoActivity;
import com.sk.maiqian.module.my.activity.JiFenActivity;
import com.sk.maiqian.module.my.activity.MyAddressListActivity;
import com.sk.maiqian.module.my.activity.MyBankListActivity;
import com.sk.maiqian.module.my.activity.MyCollectionActivity;
import com.sk.maiqian.module.my.activity.MyMessageActivity;
import com.sk.maiqian.module.my.activity.PersonInfoActivity;
import com.sk.maiqian.module.my.activity.SettingActivity;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.LoginObj;
import com.sk.maiqian.module.youxue.activity.YouXueShenQingActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2018/3/27.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.tv_my_name)
    TextView tv_my_name;
    @BindView(R.id.ll_my_setting)
    LinearLayout ll_my_setting;

    @BindView(R.id.tv_my_phone)
    TextView tv_my_phone;

    @BindView(R.id.civ_my)
    CircleImageView civ_my;
    @Override
    protected int getContentView() {
        return R.layout.my_frag;
    }

    @Override
    protected void initView() {


    }

    @Override
    public void onResume() {
        super.onResume();
        setInfo();
    }

    private void setInfo() {
        String avatar = SPUtils.getString(mContext, AppXml.avatar, null);
        GlideUtils.intoD(mContext,avatar,civ_my, R.drawable.default_person);

        String name = SPUtils.getString(mContext, AppXml.nick_name, null);
        String mobile = SPUtils.getString(mContext, AppXml.mobile, null);
        tv_my_name.setText(name);
        tv_my_phone.setText(mobile);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            getUserInfo();
        }
    }

    private void getUserInfo() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getUserInfo(map, new MyCallBack<LoginObj>(mContext) {
            @Override
            public void onSuccess(LoginObj obj) {
                loginResult(obj);
            }
        });
    }
    private void loginResult(LoginObj obj) {

        SPUtils.setPrefString(mContext, AppXml.user_id, obj.getUser_id());
        SPUtils.setPrefString(mContext, AppXml.user_name, obj.getUser_name());
        SPUtils.setPrefString(mContext, AppXml.nick_name, obj.getNick_name());
        SPUtils.setPrefString(mContext, AppXml.avatar, obj.getAvatar());
        SPUtils.setPrefString(mContext, AppXml.mobile, obj.getMobile());
        SPUtils.setPrefInt(mContext, AppXml.message_sink, obj.getMessage_sink());
        SPUtils.setPrefString(mContext, AppXml.jifen,  obj.getPoint()+"");


        setInfo();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_my, R.id.ll_my_qianzheng_order, R.id.ll_my_yingyu_order, R.id.ll_my_liuxue, R.id.ll_my_youxue, R.id.ll_my_jifen, R.id.ll_my_bank, R.id.ll_my_address, R.id.ll_my_collection, R.id.ll_my_message, R.id.ll_my_setting, R.id.ll_my_fenxiao})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_my:
                STActivity(PersonInfoActivity.class);
                break;
            case R.id.ll_my_qianzheng_order:
                MyRxBus.getInstance().post(new SelectOrderEvent(SelectOrderEvent.type_1));
                break;
            case R.id.ll_my_yingyu_order:
                MyRxBus.getInstance().post(new SelectOrderEvent(SelectOrderEvent.type_2));
                break;
            case R.id.ll_my_liuxue:
                intent=new Intent();
                intent.putExtra(IntentParam.selectShenQing,true);
                STActivity(intent,LiuXueShenQingActivity.class);
                break;
            case R.id.ll_my_youxue:
                intent=new Intent();
                intent.putExtra(IntentParam.selectShenQing,true);
                STActivity(intent,YouXueShenQingActivity.class);
                break;
            case R.id.ll_my_jifen:
                STActivity(JiFenActivity.class);
                break;
            case R.id.ll_my_bank:
                STActivity(MyBankListActivity.class);
                break;
            case R.id.ll_my_address:
                STActivity(MyAddressListActivity.class);
                break;
            case R.id.ll_my_collection:
                STActivity(MyCollectionActivity.class);
                break;
            case R.id.ll_my_message:
                STActivity(MyMessageActivity.class);
                break;
            case R.id.ll_my_setting:
                STActivity(SettingActivity.class);
                break;
            case R.id.ll_my_fenxiao:
                STActivity(FenXiaoActivity.class);
                break;
        }
    }
}

package com.sk.maiqian.module.my.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class PersonInfoActivity extends BaseActivity {
    @BindView(R.id.civ_personinfo)
    CircleImageView civ_personinfo;
    @BindView(R.id.ll_personinfo)
    LinearLayout ll_personinfo;
    @BindView(R.id.tv_personinfo_phone)
    TextView tv_personinfo_phone;
    @BindView(R.id.tv_personinfo_nickname)
    TextView tv_personinfo_nickname;

    @Override
    protected int getContentView() {
        setAppTitle("个人信息");
        setAppRightImg(R.drawable.share);
        return R.layout.personinfo_act;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInfo();
    }
    @Override
    protected void initData() {

    }
    private void setInfo() {
        String avatar = SPUtils.getString(mContext, AppXml.avatar, null);
        GlideUtils.intoD(mContext,avatar,civ_personinfo, R.drawable.default_person);

        String name = SPUtils.getString(mContext, AppXml.nick_name, null);
        String mobile = SPUtils.getString(mContext, AppXml.mobile, null);
        tv_personinfo_nickname.setText(name);
        tv_personinfo_phone.setText(mobile);
    }


    @OnClick({R.id.ll_personinfo, R.id.tv_personinfo_phone, R.id.tv_personinfo_nickname})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_personinfo:
                break;
            case R.id.tv_personinfo_phone:
                STActivity(EditPhoneActivity.class);
                break;
            case R.id.tv_personinfo_nickname:
                STActivity(EditNicknameActivity.class);
                break;
        }
    }
}

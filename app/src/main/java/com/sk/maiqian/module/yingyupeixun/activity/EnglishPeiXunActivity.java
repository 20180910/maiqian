package com.sk.maiqian.module.yingyupeixun.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.customview.MyEditText;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.home.activity.WoYaoLiuYanActivity;
import com.sk.maiqian.module.yingyupeixun.event.EnglishSearchEvent;
import com.sk.maiqian.module.yingyupeixun.fragment.EnglishPeiXunFragment;
import com.sk.maiqian.tools.TablayoutUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunActivity extends BaseActivity {
    @BindView(R.id.tv_englishpeixun_dingwei)
    TextView tv_englishpeixun_dingwei;
    @BindView(R.id.et_englishpeixun_search)
    MyEditText et_englishpeixun_search;
    @BindView(R.id.tab_englishpeixun)
    TabLayout tab_englishpeixun;
    @BindView(R.id.vp_englishpeixun)
    ViewPager vp_englishpeixun;

    FragmentAdapter fragmentAdapter;


    @BindView(R.id.iv_englishpeixun_liuyan)
    ImageView iv_englishpeixun_liuyan;

    @Override
    protected int getContentView() {
        setAppTitle("英语培训");
        setAppRightImg(R.drawable.share);
        return R.layout.englishpeixun_act;
    }

    @Override
    protected void initView() {
        TablayoutUtils.setTabWidth(tab_englishpeixun,40);
        et_englishpeixun_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                MyRxBus.getInstance().post(new EnglishSearchEvent(s.toString()));
            }
        });
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setTitle(new String[]{"正式课","体验课"});

        EnglishPeiXunFragment englishPeiXunFragment1 = EnglishPeiXunFragment.newInstance(EnglishPeiXunFragment.type_1);
        EnglishPeiXunFragment englishPeiXunFragment2 = EnglishPeiXunFragment.newInstance(EnglishPeiXunFragment.type_2);
        List<Fragment> list=new ArrayList<>();
        list.add(englishPeiXunFragment1);
        list.add(englishPeiXunFragment2);
        fragmentAdapter.setList(list);


        vp_englishpeixun.setOffscreenPageLimit(list.size()-1);
        vp_englishpeixun.setAdapter(fragmentAdapter);

        tab_englishpeixun.setupWithViewPager(vp_englishpeixun);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_englishpeixun_liuyan})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.iv_englishpeixun_liuyan:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.qianZhengType,WoYaoLiuYanActivity.type_2);
                STActivity(intent,WoYaoLiuYanActivity.class);
            break;
        }
    }
}

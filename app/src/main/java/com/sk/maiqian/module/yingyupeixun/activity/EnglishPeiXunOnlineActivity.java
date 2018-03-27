package com.sk.maiqian.module.yingyupeixun.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.yingyupeixun.fragment.EnglishPeiXunFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunOnlineActivity extends BaseActivity {
    @BindView(R.id.tab_englishpeixun_onlie)
    TabLayout tab_englishpeixun_onlie;
    @BindView(R.id.vp_englishpeixun_onlie)
    ViewPager vp_englishpeixun_onlie;

    FragmentAdapter fragmentAdapter;

    @Override
    protected int getContentView() {
        setAppTitle("在线学习");
        setAppRightImg(R.drawable.share);
        return R.layout.englishpeixun_online_act;
    }

    @Override
    protected void initView() {
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setTitle(new String[]{"体验课","正式课"});

        EnglishPeiXunFragment englishPeiXunFragment1 = EnglishPeiXunFragment.newInstance();
        EnglishPeiXunFragment englishPeiXunFragment2 = EnglishPeiXunFragment.newInstance();
        List<Fragment> list=new ArrayList<>();
        list.add(englishPeiXunFragment1);
        list.add(englishPeiXunFragment2);
        fragmentAdapter.setList(list);


        vp_englishpeixun_onlie.setOffscreenPageLimit(list.size()-1);
        vp_englishpeixun_onlie.setAdapter(fragmentAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

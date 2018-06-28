package com.sk.maiqian.module.yingyupeixun.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.yingyupeixun.fragment.EnglishPeiXunOnlineFragment;
import com.sk.maiqian.tools.TablayoutUtils;

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
        fragmentAdapter.setTitle(new String[]{"音频","视频"});

        EnglishPeiXunOnlineFragment EnglishPeiXunOnlineFragment1 = EnglishPeiXunOnlineFragment.newInstance(EnglishPeiXunOnlineFragment.type_1);
        EnglishPeiXunOnlineFragment EnglishPeiXunOnlineFragment2 = EnglishPeiXunOnlineFragment.newInstance(EnglishPeiXunOnlineFragment.type_2);
        List<Fragment> list=new ArrayList<>();
        list.add(EnglishPeiXunOnlineFragment2);
        list.add(EnglishPeiXunOnlineFragment1);
        fragmentAdapter.setList(list);


        vp_englishpeixun_onlie.setOffscreenPageLimit(list.size()-1);
        vp_englishpeixun_onlie.setAdapter(fragmentAdapter);

        tab_englishpeixun_onlie.setupWithViewPager(vp_englishpeixun_onlie);
        TablayoutUtils.setTabWidth(tab_englishpeixun_onlie,40);

    }

    @Override
    protected void initData() {

    }

    protected void onViewClick(View v) {

    }
}

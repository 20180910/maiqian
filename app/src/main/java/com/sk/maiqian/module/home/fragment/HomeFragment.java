package com.sk.maiqian.module.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.androidtools.PhoneUtils;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.module.home.activity.DaYiJieHuoActivity;
import com.sk.maiqian.module.home.activity.QianZhengDaiBanActivity;
import com.sk.maiqian.module.home.adapter.HomeHotAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.ll_home_qianzheng)
    LinearLayout ll_home_qianzheng;
    @BindView(R.id.ll_home_peixun)
    LinearLayout ll_home_peixun;
    @BindView(R.id.ll_home_youxue)
    LinearLayout ll_home_youxue;
    @BindView(R.id.ll_home_liuxue)
    LinearLayout ll_home_liuxue;

    @BindView(R.id.tab_home_hotzixun)
    TabLayout tab_home_hotzixun;

    @BindView(R.id.vp_home_hot)
    ViewPager vp_home_hot;
    HomeHotAdapter homeHotAdapter;


    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected int getContentView() {
        return R.layout.home_frag;
    }

    @Override
    protected void initView() {
        pcfl.disableWhenHorizontalMove(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        layoutParams.height= PhoneUtils.getScreenWidth(mContext)/2-PhoneUtils.dip2px(mContext,40);
        ll_home_qianzheng.setLayoutParams(layoutParams);
        ll_home_peixun.setLayoutParams(layoutParams);
        ll_home_youxue.setLayoutParams(layoutParams);
        ll_home_liuxue.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams hotLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        hotLayoutParams.height=PhoneUtils.getScreenHeight(mContext)-PhoneUtils.dip2px(mContext,200);
        vp_home_hot.setLayoutParams(hotLayoutParams);


        HomeHotZiXunFragment homeHotZiXunFragment1 = HomeHotZiXunFragment.newInstance();
        HomeHotZiXunFragment homeHotZiXunFragment2 = HomeHotZiXunFragment.newInstance();
        HomeHotZiXunFragment homeHotZiXunFragment3 = HomeHotZiXunFragment.newInstance();
        HomeHotZiXunFragment homeHotZiXunFragment4 = HomeHotZiXunFragment.newInstance();

        List<Fragment>list=new ArrayList<>();
        list.add(homeHotZiXunFragment1);
        list.add(homeHotZiXunFragment2);
        list.add(homeHotZiXunFragment3);
        list.add(homeHotZiXunFragment4);

        homeHotAdapter=new HomeHotAdapter(getChildFragmentManager());
        homeHotAdapter.setList(list);

        vp_home_hot.setOffscreenPageLimit(list.size()-1);
        vp_home_hot.setAdapter(homeHotAdapter);

        tab_home_hotzixun.setupWithViewPager(vp_home_hot);

    }

    @Override
    protected void initData() {
//        showProgress();

        getData(1, false);
    }


    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
       /* ApiRequest.getHomeZiXunData(map, new MyCallBack<List<HomeZiXunDataObj>>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(List<HomeZiXunDataObj> list) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(list, true);
                } else {
                    pageNum = 2;
                    adapter.setList(list, true);
                }
            }
        });*/

    }


    @OnClick({R.id.tv_home_toutiao,R.id.ll_home_qianzheng,R.id.ll_home_peixun,R.id.ll_home_youxue,R.id.ll_home_liuxue})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_toutiao:
                STActivity(DaYiJieHuoActivity.class);
                break;
            case R.id.ll_home_qianzheng:
                STActivity(QianZhengDaiBanActivity.class);
                break;
            case R.id.ll_home_peixun:
                STActivity(QianZhengDaiBanActivity.class);
                break;
            case R.id.ll_home_youxue:
                STActivity(QianZhengDaiBanActivity.class);
                break;
            case R.id.ll_home_liuxue:
                STActivity(QianZhengDaiBanActivity.class);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
       /* if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }*/
    }


    @OnClick({R.id.ll_home_qianzheng, R.id.ll_home_peixun, R.id.ll_home_youxue, R.id.ll_home_liuxue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_qianzheng:
                break;
            case R.id.ll_home_peixun:
                break;
            case R.id.ll_home_youxue:
                break;
            case R.id.ll_home_liuxue:
                break;
        }
    }
}

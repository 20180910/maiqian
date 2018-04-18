package com.sk.maiqian.module.home.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideLoader;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.ImageSizeUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.activity.DaYiJieHuoActivity;
import com.sk.maiqian.module.home.activity.LookAnswerActivity;
import com.sk.maiqian.module.home.activity.QianZhengDaiBanActivity;
import com.sk.maiqian.module.home.event.ZiXunEvent;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.BannerObj;
import com.sk.maiqian.module.home.network.response.HomeDaYiJieHuoObj;
import com.sk.maiqian.module.liuxue.activity.LiuXueActivity;
import com.sk.maiqian.module.yingyupeixun.activity.EnglishPeiXunActivity;
import com.sk.maiqian.module.youxue.activity.YouXueActivity;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tv_home_toutiao)
    MarqueeView tv_home_toutiao;
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

    @BindView(R.id.bn_home)
    Banner bn_home;

    @BindView(R.id.vp_home_hot)
    ViewPager vp_home_hot;
    FragmentAdapter fragmentAdapter;

    @BindView(R.id.civ_home_qzdb)
    CircleImageView civ_home_qzdb;
    @BindView(R.id.tv_home_qzdb)
    TextView tv_home_qzdb;
    @BindView(R.id.tv_home_qzdb_second)
    TextView tv_home_qzdb_second;
    @BindView(R.id.civ_home_yypx)
    CircleImageView civ_home_yypx;
    @BindView(R.id.tv_home_yypx)
    TextView tv_home_yypx;
    @BindView(R.id.tv_home_yypx_second)
    TextView tv_home_yypx_second;
    @BindView(R.id.civ_home_yx)
    CircleImageView civ_home_yx;
    @BindView(R.id.tv_home_yx)
    TextView tv_home_yx;
    @BindView(R.id.tv_home_yx_second)
    TextView tv_home_yx_second;
    @BindView(R.id.civ_home_lx)
    CircleImageView civ_home_lx;
    @BindView(R.id.tv_home_lx)
    TextView tv_home_lx;
    @BindView(R.id.tv_home_lx_second)
    TextView tv_home_lx_second;


    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected int getContentView() {
        return R.layout.home_frag;
    }

    @Override
    protected void initView() {
        pcfl.disableWhenHorizontalMove(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.height = PhoneUtils.getScreenWidth(mContext) / 2 - PhoneUtils.dip2px(mContext, 40);
        ll_home_qianzheng.setLayoutParams(layoutParams);
        ll_home_peixun.setLayoutParams(layoutParams);
        ll_home_youxue.setLayoutParams(layoutParams);
        ll_home_liuxue.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams hotLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        hotLayoutParams.height = PhoneUtils.getScreenHeight(mContext) - PhoneUtils.dip2px(mContext, 200);
        vp_home_hot.setLayoutParams(hotLayoutParams);


        HomeHotZiXunFragment homeHotZiXunFragment1 = HomeHotZiXunFragment.newInstance(HomeHotZiXunFragment.type_1);
        HomeHotZiXunFragment homeHotZiXunFragment2 = HomeHotZiXunFragment.newInstance(HomeHotZiXunFragment.type_2);
        HomeHotZiXunFragment homeHotZiXunFragment3 = HomeHotZiXunFragment.newInstance(HomeHotZiXunFragment.type_3);
        HomeHotZiXunFragment homeHotZiXunFragment4 = HomeHotZiXunFragment.newInstance(HomeHotZiXunFragment.type_4);

        List<Fragment> list = new ArrayList<>();
        list.add(homeHotZiXunFragment1);
        list.add(homeHotZiXunFragment2);
        list.add(homeHotZiXunFragment3);
        list.add(homeHotZiXunFragment4);

        fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        fragmentAdapter.setList(list);

        vp_home_hot.setOffscreenPageLimit(list.size() - 1);
        vp_home_hot.setAdapter(fragmentAdapter);

        tab_home_hotzixun.setupWithViewPager(vp_home_hot);

    }

    @Override
    protected void initData() {
        showProgress();
        getOtherData();
        getData(1,false);
    }

    @Override
    protected void getOtherData() {
        super.getOtherData();
        getBanner();
    }
    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        MyRxBus.getInstance().postReplay(new ZiXunEvent());
        getDaYiJieHuo();
    }

    private void getDaYiJieHuo() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getDaYiJieHuo(map, new MyCallBack<HomeDaYiJieHuoObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(HomeDaYiJieHuoObj obj) {
                if(notEmpty(obj.getAnswer_doubts_list())){
                    List<String> info = new ArrayList<>();
                    for (int i = 0; i < obj.getAnswer_doubts_list().size(); i++) {
                        info.add(obj.getAnswer_doubts_list().get(i).getTitle());
                    }
                    /*LookAnswerActivity*/
                    tv_home_toutiao.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, TextView textView) {

                            Intent intent=new Intent();
                            intent.putExtra(IntentParam.dayijiehuo_title,obj.getAnswer_doubts_list().get(position).getTitle());
                            intent.putExtra(IntentParam.dayijiehuo_content,obj.getAnswer_doubts_list().get(position).getContent());
                            STActivity(intent,LookAnswerActivity.class);
                        }
                    });
                    tv_home_toutiao.startWithList(info);
                }

                List<HomeDaYiJieHuoObj.TypeListBean> type_list = obj.getType_list();
                if (notEmpty(type_list)) {
                    if (type_list.size() >= 1) {
                        GlideUtils.intoD(mContext, type_list.get(0).getImg_url(), civ_home_qzdb,R.drawable.home_icon03_qianzhengdaiban);
                        tv_home_qzdb.setText(type_list.get(0).getTitle());
                        tv_home_qzdb_second.setText(type_list.get(0).getIntroduction());
                    }
                    if (type_list.size() >= 2) {
                        GlideUtils.intoD(mContext, type_list.get(1).getImg_url(), civ_home_yypx,R.drawable.home_icon_peixun);
                        tv_home_yypx.setText(type_list.get(1).getTitle());
                        tv_home_yypx_second.setText(type_list.get(1).getIntroduction());

                    }
                    if (type_list.size() >= 3) {
                        GlideUtils.intoD(mContext, type_list.get(2).getImg_url(), civ_home_yx,R.drawable.home_icon_youxue);
                        tv_home_yx.setText(type_list.get(2).getTitle());
                        tv_home_yx_second.setText(type_list.get(2).getIntroduction());

                    }
                    if (type_list.size() >= 4) {
                        GlideUtils.intoD(mContext, type_list.get(3).getImg_url(), civ_home_lx,R.drawable.home_icon_liuxue);
                        tv_home_lx.setText(type_list.get(3).getTitle());
                        tv_home_lx_second.setText(type_list.get(3).getIntroduction());

                    }
                }
            }
        });
    }

    private void getBanner() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getHomeBanner(map, new MyCallBack<BannerObj>(mContext) {
            @Override
            public void onSuccess(BannerObj obj) {
                if (notEmpty(obj.getRoasting_list())) {
                    List<BannerObj.RoastingListBean> list = obj.getRoasting_list();
                    for (int i = 0; i < list.size(); i++) {
                        bannerList.add(list.get(i).getImg_url());
                    }
                    bn_home.setLayoutParams(ImageSizeUtils.getImageSizeLayoutParams(mContext));
                    bn_home.setImages(bannerList);
                    bn_home.setImageLoader(new GlideLoader());
                    bn_home.start();
                }
            }
        });
    }




    @OnClick({R.id.tv_home_more,R.id.tv_home_toutiao, R.id.ll_home_qianzheng, R.id.ll_home_peixun, R.id.ll_home_youxue, R.id.ll_home_liuxue})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_more:
                STActivity(DaYiJieHuoActivity.class);
                break;
            case R.id.tv_home_toutiao:
                STActivity(DaYiJieHuoActivity.class);
                break;
            case R.id.ll_home_qianzheng:
                STActivity(QianZhengDaiBanActivity.class);
                break;
            case R.id.ll_home_peixun:
                STActivity(EnglishPeiXunActivity.class);
                break;
            case R.id.ll_home_youxue:
                STActivity(YouXueActivity.class);
                break;
            case R.id.ll_home_liuxue:
                STActivity(LiuXueActivity.class);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }
    }
}

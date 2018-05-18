package com.sk.maiqian.module.yingyupeixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyTextView;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.Constant;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.yingyupeixun.activity.ShipinDetailActivity;
import com.sk.maiqian.module.yingyupeixun.activity.YinpinDetailActivity;
import com.sk.maiqian.module.yingyupeixun.network.ApiRequest;
import com.sk.maiqian.module.yingyupeixun.network.response.OnlineStudyObj;
import com.sk.maiqian.module.yingyupeixun.network.response.OnlineTypeObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunOnlineFragment extends BaseFragment {
    public static final String type_1 = "1";//视频
    public static final String type_2 = "2";//音频
    @BindView(R.id.rv_englishpeixundaiban)
    MyRecyclerView rv_englishpeixundaiban;

    @BindView(R.id.tab_online)
    TabLayout tab_online;
    private int selectIndex = 0;
    private List<OnlineTypeObj> typeObjList;

    MyLoadMoreAdapter adapter;


    private List<Integer> sparseIntArray = new ArrayList<>();
    private SparseBooleanArray showPosition = new SparseBooleanArray();
    private String typeid = "0";

    @Override
    protected int getContentView() {
        return R.layout.englishpeixun_online_frag;
    }

    public static EnglishPeiXunOnlineFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type, type);

        EnglishPeiXunOnlineFragment fragment = new EnglishPeiXunOnlineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter = new MyLoadMoreAdapter<OnlineStudyObj>(mContext, R.layout.englishpeixun_online_item, pageSize) {

            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, OnlineStudyObj bean) {
                View fl_yinpin = holder.getView(R.id.fl_yinpin);
                if (type_2.equalsIgnoreCase(getArguments().getString(Constant.type))) {
                    fl_yinpin.setVisibility(View.GONE);
                } else {
                    fl_yinpin.setVisibility(View.VISIBLE);
                }
                ImageView imageView = holder.getImageView(R.id.iv_online);
                GlideUtils.into(mContext, bean.getImage_url(), imageView);
                holder.setText(R.id.tv_online_title, bean.getTitle());
                holder.setText(R.id.tv_online_renqun, bean.getAttachment());
                holder.setText(R.id.tv_online_shichang, bean.getTime_length());
                holder.setText(R.id.tv_online_time, bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent = new Intent();
                        if (type_1.equals(getArguments().getString(Constant.type))) {
                            intent.putExtra(IntentParam.dataId, bean.getCourseware_id());
                            STActivity(intent, ShipinDetailActivity.class);
                        } else {
                            intent.putExtra(IntentParam.webUrl, bean.getVideo_link());
                            STActivity(intent, YinpinDetailActivity.class);
                        }
                    }
                });


                TextView tv_online_fenlei = holder.getTextView(R.id.tv_online_fenlei);
                if (!sparseIntArray.contains(bean.getType_id()) || showPosition.get(position)) {
                    showPosition.put(position, true);
                    sparseIntArray.add(bean.getType_id());
                    tv_online_fenlei.setVisibility(View.VISIBLE);
                    tv_online_fenlei.setText(bean.getType_name());
                } else {
                    tv_online_fenlei.setVisibility(View.GONE);
                }
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_englishpeixundaiban.setAdapter(adapter);


        tab_online.setTabMode(TabLayout.MODE_SCROLLABLE);


        tab_online.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectIndex = tab.getPosition();
                MyTextView tv_dayijiehuo_tabtitle = tab.getCustomView().findViewById(R.id.tv_dayijiehuo_tabtitle);
                tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext, R.color.blue_00));
                tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext, R.color.blue_00));
                tv_dayijiehuo_tabtitle.complete();
                if (notEmpty(typeObjList)) {
                    typeid = typeObjList.get(selectIndex).getType_id();
                    showLoading();
                    getData(1, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                MyTextView tv_dayijiehuo_tabtitle = tab.getCustomView().findViewById(R.id.tv_dayijiehuo_tabtitle);
                tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext, R.color.gray_66));
                tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_66));
                tv_dayijiehuo_tabtitle.complete();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private View getTabItem(String str) {
        View tab0 = LayoutInflater.from(getActivity()).inflate(R.layout.dayijiehuo_tab_item, null);
        MyTextView tv_dayijiehuo_tabtitle = tab0.findViewById(R.id.tv_dayijiehuo_tabtitle);
        tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext, R.color.gray_66));
        tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext,R.color.gray_66));
        tv_dayijiehuo_tabtitle.complete();
        /*if("全部".equals(str)){
            tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext,R.color.blue_00));
            tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
            tv_dayijiehuo_tabtitle.complete();
        }*/
        tv_dayijiehuo_tabtitle.setText(str);
        return tab0;
    }

    @Override
    protected void initData() {
        showProgress();
        getTypeData(getArguments().getString(Constant.type));
        getData(1, false);
    }

    @Override
    protected void getOtherData() {
        super.getOtherData();
        getTypeData(getArguments().getString(Constant.type));
    }

    private void getTypeData(String type) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("sign", getSign(map));
        ApiRequest.getOnlineType(map, new MyCallBack<List<OnlineTypeObj>>(mContext) {
            @Override
            public void onSuccess(List<OnlineTypeObj> list, int errorCode, String msg) {
                if (notEmpty(list)) {
                    tab_online.removeAllTabs();
                    typeObjList = new ArrayList<>();
                    OnlineTypeObj obj = new OnlineTypeObj();
                    obj.setType_id("0");
                    obj.setType_name("全部");
                    typeObjList.add(obj);
                    typeObjList.addAll(list);
                    for (int i = 0; i < typeObjList.size(); i++) {
                        TabLayout.Tab tab=tab_online.newTab();
                        tab.setCustomView(getTabItem(typeObjList.get(i).getType_name()));
                        tab_online.addTab(tab);
                    }
                    tab_online.setVisibility(View.VISIBLE);
                } else {
                    tab_online.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("typeid", typeid);
        map.put("type", getArguments().getString(Constant.type));
        map.put("pagesize", pagesize + "");
        map.put("page", page + "");
        map.put("sign", getSign(map));
        ApiRequest.getOnlineStudyList(map, new MyCallBack<List<OnlineStudyObj>>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(List<OnlineStudyObj> list, int errorCode, String msg) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(list, true);
                } else {
                    sparseIntArray.clear();
                    showPosition.clear();
                    pageNum = 2;
                    adapter.setList(list, true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}

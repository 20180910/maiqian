package com.sk.maiqian.module.home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.github.customview.MyTextView;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.R;
import com.sk.maiqian.adapter.FragmentAdapter;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.home.event.DaYiJieHuoEvent;
import com.sk.maiqian.module.home.fragment.DaYiJieHuoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/22.
 */

public class DaYiJieHuoActivity extends BaseActivity {
    @BindView(R.id.et_dayijiehuo)
    EditText et_dayijiehuo;
    @BindView(R.id.tab_dayijiehuo)
    TabLayout tab_dayijiehuo;

    @BindView(R.id.vp_dayijiehuo)
    ViewPager vp_dayijiehuo;

    FragmentAdapter adapter;
    List<Fragment> list=new ArrayList<>();
    private int selectIndex=0;

    @Override
    protected int getContentView() {
        setAppTitle("答疑解惑");
        return R.layout.dayijiehuo_act;
    }

    @Override
    protected void initView() {
        et_dayijiehuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                MyRxBus.getInstance().post(new DaYiJieHuoEvent(s.toString(),selectIndex+""));
            }
        });


        adapter=new FragmentAdapter(getSupportFragmentManager());
        adapter.setTitle(null);
//        adapter.setTitle(new String[]{"常见问题","签证代办类","英语培训类","游学类","留学类"});

        list.add(DaYiJieHuoFragment.newInstance(DaYiJieHuoFragment.type_0));
        list.add(DaYiJieHuoFragment.newInstance(DaYiJieHuoFragment.type_1));
        list.add(DaYiJieHuoFragment.newInstance(DaYiJieHuoFragment.type_2));
        list.add(DaYiJieHuoFragment.newInstance(DaYiJieHuoFragment.type_3));
        list.add(DaYiJieHuoFragment.newInstance(DaYiJieHuoFragment.type_4));

        adapter.setList(list);

        vp_dayijiehuo.setAdapter(adapter);
        vp_dayijiehuo.setOffscreenPageLimit(list.size()-1);

        tab_dayijiehuo.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_dayijiehuo.setupWithViewPager(vp_dayijiehuo);

        tab_dayijiehuo.getTabAt(0).setCustomView(getTabItem("常见问题"));
        tab_dayijiehuo.getTabAt(1).setCustomView(getTabItem("签证代办类"));
        tab_dayijiehuo.getTabAt(2).setCustomView(getTabItem("英语培训类"));
        tab_dayijiehuo.getTabAt(3).setCustomView(getTabItem("游学类"));
        tab_dayijiehuo.getTabAt(4).setCustomView(getTabItem("留学类"));

        tab_dayijiehuo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectIndex =tab.getPosition();
                Log("==="+selectIndex+"");
                MyTextView tv_dayijiehuo_tabtitle = tab.getCustomView().findViewById(R.id.tv_dayijiehuo_tabtitle);
                tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext,R.color.orange));
                tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext,R.color.orange));
                tv_dayijiehuo_tabtitle.complete();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                MyTextView tv_dayijiehuo_tabtitle = tab.getCustomView().findViewById(R.id.tv_dayijiehuo_tabtitle);
                tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext,R.color.white));
                tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext,R.color.gray_66));
                tv_dayijiehuo_tabtitle.complete();
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

//        tab_dayijiehuo.addTab(tab_dayijiehuo.newTab().setCustomView(getTabItem("常见问题")));
//        tab_dayijiehuo.addTab(tab_dayijiehuo.newTab().setCustomView(getTabItem("签证代办类")));
//        tab_dayijiehuo.addTab(tab_dayijiehuo.newTab().setCustomView(getTabItem("英语培训类")));
//        tab_dayijiehuo.addTab(tab_dayijiehuo.newTab().setCustomView(getTabItem("游学类")));
//        tab_dayijiehuo.addTab(tab_dayijiehuo.newTab().setCustomView(getTabItem("留学类")));

    }

    private View getTabItem(String str) {
        View tab0 = getLayoutInflater().inflate(R.layout.dayijiehuo_tab_item, null);
        MyTextView tv_dayijiehuo_tabtitle = tab0.findViewById(R.id.tv_dayijiehuo_tabtitle);
        if("常见问题".equals(str)){
            tv_dayijiehuo_tabtitle.setTextColor(ContextCompat.getColor(mContext,R.color.orange));
            tv_dayijiehuo_tabtitle.setBorderColor(ContextCompat.getColor(mContext,R.color.orange));
            tv_dayijiehuo_tabtitle.complete();
        }
        tv_dayijiehuo_tabtitle.setText(str);
        return tab0;
    }

    @Override
    protected void initData() {

    }



    @Override
    protected void onViewClick(View v) {

    }
}

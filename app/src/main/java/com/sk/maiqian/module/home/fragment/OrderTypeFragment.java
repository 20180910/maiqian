package com.sk.maiqian.module.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.github.customview.MyTextView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/12/4.
 */

public class OrderTypeFragment extends BaseFragment {

    @BindView(R.id.tv_order_qianzheng)
    MyTextView tv_order_qianzheng;
    @BindView(R.id.tv_order_peixun)
    MyTextView tv_order_peixun;
    @BindView(R.id.fl_order)
    FrameLayout fl_order;
    private OrderFragment orderFragment1;
    private OrderFragment orderFragment2;

    @Override
    protected int getContentView() {
        return R.layout.ordertype_frag;
    }

    @Override
    protected void initView() {
        orderFragment1 = OrderFragment.newInstance(OrderFragment.type_1);
        getChildFragmentManager().beginTransaction().add(R.id.fl_order, orderFragment1).commitAllowingStateLoss();
    }


    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_order_qianzheng, R.id.tv_order_peixun })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_order_qianzheng:
                setTextView(tv_order_qianzheng,tv_order_peixun);


                showFragment(orderFragment1);
                hideFragment(orderFragment2);
                break;
            case R.id.tv_order_peixun:
                setTextView(tv_order_peixun,tv_order_qianzheng);

                if(orderFragment2==null){
                    orderFragment2 = OrderFragment.newInstance(OrderFragment.type_2);
                    getChildFragmentManager().beginTransaction().add(R.id.fl_order, orderFragment2).commitAllowingStateLoss();
                }else{
                    showFragment(orderFragment2);
                }
                hideFragment(orderFragment1);
                break;
        }
    }
    private void setTextView(MyTextView textView1,MyTextView textView2){
        textView1.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
        textView1.setAllLine(true);
        textView1.setSolidColor(ContextCompat.getColor(mContext,R.color.blue_00));
        textView1.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        textView1.complete();

        textView2.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
        textView2.setAllLine(true);
        textView2.setSolidColor(ContextCompat.getColor(mContext,R.color.white));
        textView2.setTextColor(ContextCompat.getColor(mContext,R.color.blue_00));
        textView2.complete();
    }

    private void hideFragment(Fragment fragment){
        if(fragment!=null){
            getChildFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }
    private void showFragment(Fragment fragment){
        if(fragment!=null){
            getChildFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }
}

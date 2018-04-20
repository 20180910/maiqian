package com.sk.maiqian.module.home.fragment;

import android.os.Bundle;
import android.view.View;

import com.library.base.BaseObj;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/12/4.
 */

public class OrderPeiXunListFragment extends BaseFragment {
    //0全部 1待付款 2已付款 3待评价)
    public static final String type_0="0";
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";

    OrderFragment fragment;

    @Override
    protected int getContentView() {
        return R.layout.orderlist_frag;
    }

    public static OrderPeiXunListFragment newInstance(String flag, String type) {

        Bundle args = new Bundle();
        args.putString(Constant.flag, flag);
        args.putString(Constant.type, type);
        OrderPeiXunListFragment fragment = new OrderPeiXunListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {


    }
    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        if(true){

        }else{

        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getQianZhengOrder(map, new MyCallBack<BaseObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(BaseObj obj) {

            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}

package com.sk.maiqian.module.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.rxbus.MyConsumer;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.event.DaYiJieHuoEvent;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.HomeDaYiJieHuoObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/11.
 */

public class DaYiJieHuoFragment extends BaseFragment {
    public static final String type_0="0";
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";
    public static final String type_4="4";
    @BindView(R.id.rv_dayijiehuo)
    RecyclerView rv_dayijiehuo;


    MyLoadMoreAdapter adapter;
    private String searchContent="";

    @Override
    protected int getContentView() {
        return R.layout.dayijiehuo_frag;
    }

    public static DaYiJieHuoFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);
        DaYiJieHuoFragment fragment = new DaYiJieHuoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<HomeDaYiJieHuoObj.AnswerDoubtsListBean>(mContext,R.layout.dayijiehuo_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, HomeDaYiJieHuoObj.AnswerDoubtsListBean bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_dayijiehuo.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEvent(DaYiJieHuoEvent.class, new MyConsumer<DaYiJieHuoEvent>() {
            @Override
            public void onAccept(DaYiJieHuoEvent event) {
                if(event.type.equals(getArguments().getString(Constant.type))){
                    searchContent=event.search;
                    getData(1,false);
                }
            }
        });
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("search_term", searchContent);
        map.put("type_id",getArguments().getString(Constant.type));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getDaYiJieHuoList(map, new MyCallBack<List<HomeDaYiJieHuoObj.AnswerDoubtsListBean>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<HomeDaYiJieHuoObj.AnswerDoubtsListBean> list) {
                if(isLoad){
                    pageNum++;
                    adapter.addList(list,true);
                }else{
                    pageNum=2;
                    adapter.setList(list,true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}

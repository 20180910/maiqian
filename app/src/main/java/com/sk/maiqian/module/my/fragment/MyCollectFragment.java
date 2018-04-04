package com.sk.maiqian.module.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.MyCollectionObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MyCollectFragment extends BaseFragment {
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";
    public static final String type_4="4";
    @BindView(R.id.rv_my_collection)
    RecyclerView rv_my_collection;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.mycollection_frag;
    }

    public static MyCollectFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);
        MyCollectFragment fragment = new MyCollectFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<MyCollectionObj>(mContext,R.layout.mycollection_item2,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, MyCollectionObj bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setAdapter(adapter);

        MyRx.start(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) {
                emitter.onNext("");
                emitter.onComplete();
            }
            @Override
            public void onNext(String obj) {

            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("type",getArguments().getString(Constant.type));
        map.put("user_id",getUserId());
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getMyCollection(map, new MyCallBack<List<MyCollectionObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyCollectionObj> list) {
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

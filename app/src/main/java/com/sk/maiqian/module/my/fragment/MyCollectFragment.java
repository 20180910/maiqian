package com.sk.maiqian.module.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.BaseObj;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.MyCollectionObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
    private String type;

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
        pcfl.disableWhenHorizontalMove(true);
        int layoutId=R.layout.mycollection_item1;
        type = getArguments().getString(Constant.type);
        switch (type){
            case type_1:
                layoutId=R.layout.mycollection_item1;
            break;
            case type_2:
                layoutId=R.layout.mycollection_item2;
            break;
            case type_3:
                layoutId=R.layout.mycollection_item3;
            break;
            case type_4:
                layoutId=R.layout.mycollection_item3;
            break;
        }
        adapter=new MyLoadMoreAdapter<MyCollectionObj>(mContext,layoutId,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, MyCollectionObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_my_collection);
                GlideUtils.into(mContext,bean.getImg_url(),imageView);
                holder.setText(R.id.tv_my_collection_title,bean.getTitle());
                switch (type){
                    case type_1:
                        holder.setText(R.id.tv_my_collection_price,bean.getPrice()+"");
                        break;
                    case type_2:
                        holder.setText(R.id.tv_my_collection_price,bean.getPrice()+"");
                        holder.setText(R.id.tv_my_collection_old_price,"¥"+bean.getOriginal_price());
                        holder.setText(R.id.tv_my_collection_flag,bean.getBiaoqian());
                        break;
                    case type_3:
                        holder.setText(R.id.tv_my_collection_subtitle,bean.getEnglish_title());
                        holder.setText(R.id.tv_my_collection_xingqu,bean.getInterested_peopleum()+"人申请");
                        break;
                    case type_4:
                        layoutId=R.layout.mycollection_item3;
                        holder.setText(R.id.tv_my_collection_subtitle,bean.getEnglish_title());
                        holder.setText(R.id.tv_my_collection_xingqu,bean.getInterested_peopleum()+"人感兴趣");
                        break;
                }


                TextView tv_collection_cancel =   holder.getTextView(R.id.tv_collection_cancel);
                tv_collection_cancel.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        cancelCollection(bean.getId());
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setAdapter(adapter);



    }

    private void cancelCollection(String id) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("mid",id);
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.cancelMyCollection(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                getData(1,false);
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
            public void onSuccess(List<MyCollectionObj> list, int errorCode, String msg) {
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

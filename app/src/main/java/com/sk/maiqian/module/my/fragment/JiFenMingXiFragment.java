package com.sk.maiqian.module.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.JiFenMingXiObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class JiFenMingXiFragment extends BaseFragment {
    //1获取 2提现
    public static final String type_1="1";
    public static final String type_2="2";
    @BindView(R.id.rv_jifenmingxi)
    RecyclerView rv_jifenmingxi;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.jifenmingxi_frag;
    }

    public static JiFenMingXiFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);
        JiFenMingXiFragment fragment = new JiFenMingXiFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<JiFenMingXiObj>(mContext,R.layout.jifenmingxi_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, JiFenMingXiObj bean) {
                holder.setText(R.id.tv_jifen_mingxi_title,bean.getRemark());
                holder.setText(R.id.tv_jifen_mingxi_date,bean.getAdd_time());
                holder.setText(R.id.tv_jifen_mingxi_content,bean.getValue()+"分");
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_jifenmingxi.setAdapter(adapter);

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
        map.put("user_id",getUserId());
        map.put("type",getArguments().getString(Constant.type));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getJiFenMingXi(map, new MyCallBack<List<JiFenMingXiObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<JiFenMingXiObj> list) {
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

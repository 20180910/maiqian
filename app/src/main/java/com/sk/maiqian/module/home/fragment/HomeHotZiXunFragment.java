package com.sk.maiqian.module.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.rxbus.MyConsumer;
import com.sk.maiqian.Constant;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.event.ZiXunEvent;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.HomeZiXunObj;
import com.sk.maiqian.module.home.activity.ZiXunDetailActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/21.
 */

public class HomeHotZiXunFragment extends BaseFragment {
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";
    public static final String type_4="4";
    @BindView(R.id.rv_zi_xun)
    RecyclerView rv_zi_xun;
    private MyLoadMoreAdapter  adapter;


    @Override
    protected int getContentView() {
        return R.layout.hotzixun_frag;
    }


    public static HomeHotZiXunFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);
        HomeHotZiXunFragment fragment = new HomeHotZiXunFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initView() {

        adapter =new MyLoadMoreAdapter<HomeZiXunObj>(mContext,R.layout.hotzixun_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, HomeZiXunObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_home_zixun);
                GlideUtils.into(mContext,bean.getImage_url(),imageView);

                holder.setText(R.id.tv_home_zixun_content,bean.getTitle());
                holder.setText(R.id.tv_home_zixun_laiyuan,bean.getAuthor());
                holder.setText(R.id.tv_home_zixun_date,bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.ziXunId,bean.getInformation_id()+"");
                        STActivity(intent,ZiXunDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_zi_xun.setLayoutManager(new LinearLayoutManager(mContext));
        rv_zi_xun.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_zi_xun.setAdapter(adapter);


    }

    @Override
    protected void initData() {
//        showProgress();
//        getData(1,false);
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEventReplay(ZiXunEvent.class, new MyConsumer<ZiXunEvent>() {
            @Override
            public void onAccept(ZiXunEvent event) {
                getData(1,false);
            }
        });
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("type_id", getArguments().getString(Constant.type));
        map.put("page", page+"");
        map.put("pagesize",pagesize+"");
        map.put("sign", getSign(map));
        ApiRequest.getHomeZiXun(map, new MyCallBack<List<HomeZiXunObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<HomeZiXunObj> list, int errorCode, String msg) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(list, true);
                } else {
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

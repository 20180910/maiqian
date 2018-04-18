package com.sk.maiqian.module.yingyupeixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.rxbus.MyConsumer;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.Constant;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.EnglishPeiXunObj;
import com.sk.maiqian.module.yingyupeixun.activity.EnglishPeiXunOnlineActivity;
import com.sk.maiqian.module.yingyupeixun.activity.KeChengDetailActivity;
import com.sk.maiqian.module.yingyupeixun.event.EnglishSearchEvent;
import com.sk.maiqian.tools.TextViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunFragment extends BaseFragment {
    public static final String type_1="1";
    public static final String type_2="2";

    @BindView(R.id.rv_englishpeixundaiban)
    MyRecyclerView rv_englishpeixundaiban;
    @BindView(R.id.tv_peixun_online_study)
    TextView tv_peixun_online_study;

    MyLoadMoreAdapter adapter;
    private String searchContent="";

    @Override
    protected int getContentView() {
        return R.layout.englishpeixun_frag;
    }

    public static EnglishPeiXunFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);
        EnglishPeiXunFragment fragment = new EnglishPeiXunFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEvent(EnglishSearchEvent.class, new MyConsumer<EnglishSearchEvent>() {
            @Override
            public void onAccept(EnglishSearchEvent event) {
                searchContent=event.searchContent;
                getData(1,false);
            }
        });
    }

    @Override
    protected void initView() {

        adapter=new MyLoadMoreAdapter<EnglishPeiXunObj>(mContext,R.layout.englishpeixun_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, EnglishPeiXunObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_englishpeixu);
                GlideUtils.into(mContext,bean.getImg_url(),imageView);
                holder.setText(R.id.tv_englishpeixu_title,bean.getTitle());
                holder.setText(R.id.tv_englishpeixu_price,"¥"+bean.getPrice());
                holder.setText(R.id.tv_englishpeixu_oldprice,"¥"+bean.getOriginal_price());
                TextViewUtils.underline(holder.getTextView(R.id.tv_englishpeixu_oldprice));
                holder.setText(R.id.tv_englishpeixu_flag,bean.getBiaoqian());
                holder.setText(R.id.tv_englishpeixu_peoplenum,"已有"+bean.getPeople_number()+"人预约申请");

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.kechengId,bean.getEnglish_training_id());
                        intent.putExtra(IntentParam.flag,bean.getBiaoqian());
                        STActivity(intent,KeChengDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_englishpeixundaiban.setAdapter(adapter);
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
        map.put("search_term", searchContent);
        map.put("type",getArguments().getString(Constant.type));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getEnglishPeiXun(map, new MyCallBack<List<EnglishPeiXunObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<EnglishPeiXunObj> list) {
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

    @OnClick({R.id.tv_peixun_online_study})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_peixun_online_study:
                STActivity(EnglishPeiXunOnlineActivity.class);
            break;
        }
    }
}

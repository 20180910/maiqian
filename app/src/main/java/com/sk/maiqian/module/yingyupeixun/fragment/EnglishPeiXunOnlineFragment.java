package com.sk.maiqian.module.yingyupeixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunOnlineFragment extends BaseFragment {
    public static final String type_1="1";//视频
    public static final String type_2="2";//音频
    @BindView(R.id.rv_englishpeixundaiban)
    MyRecyclerView rv_englishpeixundaiban;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.englishpeixun_online_frag;
    }

    public static EnglishPeiXunOnlineFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(Constant.type,type);

        EnglishPeiXunOnlineFragment fragment = new EnglishPeiXunOnlineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<OnlineStudyObj>(mContext,R.layout.englishpeixun_online_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, OnlineStudyObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_online);
                GlideUtils.into(mContext,bean.getImage_url(),imageView);
                holder.setText(R.id.tv_online_title,bean.getTitle());
                holder.setText(R.id.tv_online_renqun,bean.getAttachment());
                holder.setText(R.id.tv_online_shichang,bean.getTime_length());
                holder.setText(R.id.tv_online_time,bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        if(type_1.equals(getArguments().getString(Constant.type))){
                            intent.putExtra(IntentParam.dataId,bean.getCourseware_id());
                            STActivity(intent,ShipinDetailActivity.class);
                        }else{
                            intent.putExtra(IntentParam.webUrl,bean.getVideo_link());
                            STActivity(intent,YinpinDetailActivity.class);
                        }
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
        map.put("type",getArguments().getString(Constant.type));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getOnlineStudyList(map, new MyCallBack<List<OnlineStudyObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<OnlineStudyObj> list) {
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

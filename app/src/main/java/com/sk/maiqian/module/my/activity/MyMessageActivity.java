package com.sk.maiqian.module.my.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.MyMessageObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyMessageActivity extends BaseActivity {
    @BindView(R.id.rv_message)
    RecyclerView rv_message;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        setAppTitle("我的消息");
        setAppRightImg(R.drawable.share);
        return R.layout.mymessage_act;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<MyMessageObj>(mContext,R.layout.mymessage_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, MyMessageObj bean) {
                holder.setText(R.id.tv_message_time,bean.getAdd_time());
                holder.setText(R.id.tv_message_title,bean.getTitle());
                TextView tv_message_content = holder.getTextView(R.id.tv_message_content);
                tv_message_content.setText(bean.getContent());
                TextView tv_message_zankai = holder.getTextView(R.id.tv_message_zankai);
                if(bean.isZhanKai()){
                    tv_message_zankai.setText("收缩");
                }else{
                    tv_message_zankai.setText("展开");
                }
                tv_message_zankai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bean.isZhanKai()){
                            tv_message_content.setMaxLines(1);
                            tv_message_zankai.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(mContext,R.drawable.wodexiaoxi_icon_zhankai),null);
                        }else{
                            tv_message_content.setMaxLines(Integer.MAX_VALUE);
                            tv_message_zankai.setCompoundDrawablesWithIntrinsicBounds(null,null,ContextCompat.getDrawable(mContext,R.drawable.wodexiaoxi_icon_shousuo),null);
                        }
                        bean.setZhanKai(!bean.isZhanKai());
                        if(bean.isZhanKai()){
                            tv_message_zankai.setText("收缩");
                        }else{
                            tv_message_zankai.setText("展开");
                        }
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        adapter.setBottomViewBackground(R.color.background_f2);

        rv_message.setLayoutManager(new LinearLayoutManager(mContext));
        rv_message.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_message.setAdapter(adapter);

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
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getMyMessage(map, new MyCallBack<List<MyMessageObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyMessageObj> list, int errorCode, String msg) {
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

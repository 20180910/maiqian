package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.QianZhengDaiBanObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/22.
 */

public class QianZhengDaiBanActivity extends BaseActivity {
    @BindView(R.id.tv_qianzheng_dingwei)
    TextView tv_qianzheng_dingwei;
    @BindView(R.id.et_qianzheng_search)
    MyEditText et_qianzheng_search;
    @BindView(R.id.rv_qianzhengdaiban)
    MyRecyclerView rv_qianzhengdaiban;

    MyLoadMoreAdapter adapter;
    private String searchContent="";

    @Override
    protected int getContentView() {
        setAppTitle("签证代办");
        setAppRightImg(R.drawable.share);
        return R.layout.qianzhengdaiban_act;
    }

    @Override
    protected void initView() {
        et_qianzheng_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                searchContent=s.toString();
                getData(1,false);
            }
        });

        adapter=new MyLoadMoreAdapter<QianZhengDaiBanObj>(mContext,R.layout.qianzhengdaiban_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, QianZhengDaiBanObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_qianzhengdaiban);
                GlideUtils.into(mContext,bean.getImg_url(),imageView);

                holder.setText(R.id.tv_qianzhengdaiban_guojia,bean.getTitle());
                holder.setText(R.id.tv_qianzhengdaiban_content,bean.getContent());
                holder.setText(R.id.tv_qianzhengdaiban_shichang,bean.getFor_how_long());
                holder.setText(R.id.tv_qianzhengdaiban_renshu,"已经有"+bean.getApplications()+"人申请");
                holder.setText(R.id.tv_qianzhengdaiban_price,bean.getPrice()+"");


                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.dataId,bean.getCountrie_region_id()+"");
                        intent.putExtra(IntentParam.title,bean.getTitle());
                        STActivity(intent,QianZhengActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_qianzhengdaiban.setAdapter(adapter);

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
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getQianZhengDaiBan(map, new MyCallBack<List<QianZhengDaiBanObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<QianZhengDaiBanObj> list) {
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

    @OnClick(R.id.iv_qianzheng_liuyan)
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.iv_qianzheng_liuyan:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.qianZhengType,WoYaoLiuYanActivity.type_1);
                STActivity(intent,WoYaoLiuYanActivity.class);
                break;
            case R.id.app_right_iv:
                break;
        }
    }
}

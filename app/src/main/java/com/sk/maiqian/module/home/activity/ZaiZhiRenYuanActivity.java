package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyTextView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.ZiLiaoMuBanObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class ZaiZhiRenYuanActivity extends BaseActivity {
    @BindView(R.id.rv_zaizhirenyuan)
    RecyclerView rv_zaizhirenyuan;

    MyLoadMoreAdapter adapter;
    private String visa_id;
    private String type;

    @Override
    protected int getContentView() {
        setAppTitle("在职人员");
        setAppRightImg(R.drawable.share);
        return R.layout.zaizhirenyuan_act;
    }

    @Override
    protected void initView() {
        visa_id = getIntent().getStringExtra(IntentParam.visa_id);
        type =getIntent().getStringExtra(IntentParam.type);

        adapter=new MyLoadMoreAdapter<ZiLiaoMuBanObj>(mContext,R.layout.zaizhirenyuan_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, ZiLiaoMuBanObj bean) {
                MyTextView tv_ziliao_muban_label = (MyTextView) holder.getView(R.id.tv_ziliao_muban_label);
                TextView tv_ziliao_muban_title = holder.getTextView(R.id.tv_ziliao_muban_title);
                TextView tv_ziliao_muban_content = holder.getTextView(R.id.tv_ziliao_muban_content);
                MyTextView tv_ziliao_muban = (MyTextView)holder.getView(R.id.tv_ziliao_muban);
                tv_ziliao_muban.setText(bean.getTitle()+"模板");
                if(TextUtils.isEmpty(bean.getLable())){
                    tv_ziliao_muban_label.setVisibility(View.GONE);
                }else{
                    tv_ziliao_muban_label.setVisibility(View.VISIBLE);
                    tv_ziliao_muban_label.setText(bean.getLable());
                }
                tv_ziliao_muban_title.setText(bean.getTitle());
                tv_ziliao_muban_content.setText(bean.getContent());
                if(bean.getIs_jump()==1){
                    tv_ziliao_muban.setVisibility(View.VISIBLE);
                    tv_ziliao_muban.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            Intent intent=new Intent();
                            intent.putExtra(IntentParam.imgUrl,bean.getImg_url());
                            STActivity(intent,MuBanActivity.class);
                        }
                    });
                }else{
                    tv_ziliao_muban.setVisibility(View.GONE);
                }
            }
        };
//        adapter.setOnLoadMoreListener(this);
        rv_zaizhirenyuan.setLayoutManager(new LinearLayoutManager(mContext));
        rv_zaizhirenyuan.setAdapter(adapter);

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
        map.put("visa_id",visa_id);
        map.put("type",type);
        map.put("sign",getSign(map));
        ApiRequest.getZiLiaoMuBan(map, new MyCallBack<List<ZiLiaoMuBanObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<ZiLiaoMuBanObj> list, int errorCode, String msg) {
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

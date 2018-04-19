package com.sk.maiqian.module.liuxue.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.activity.WoYaoLiuYanActivity;
import com.sk.maiqian.module.liuxue.network.ApiRequest;
import com.sk.maiqian.module.liuxue.network.response.YouXueObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LiuXueActivity extends BaseActivity {

    @BindView(R.id.rv_liuxue)
    MyRecyclerView rv_liuxue;

    MyLoadMoreAdapter adapter;

    @BindView(R.id.iv_liuexue_liuyan)
    ImageView iv_liuexue_liuyan;
    @BindView(R.id.tv_liuxue_shenqing)
    MyTextView tv_liuxue_shenqing;
    @BindView(R.id.et_liuxue_search)
    MyEditText et_liuxue_search;
    @BindView(R.id.ll_liuxue_search)
    LinearLayout ll_liuxue_search;
    @BindView(R.id.tv_youxue_guojia)
    TextView tv_youxue_guojia;
    @BindView(R.id.ll_youxue_guojia)
    LinearLayout ll_youxue_guojia;
    @BindView(R.id.tv_youxue_xuexiao)
    TextView tv_youxue_xuexiao;
    @BindView(R.id.ll_youxue_xuexiao)
    LinearLayout ll_youxue_xuexiao;
    @BindView(R.id.tv_youxue_zhuanye)
    TextView tv_youxue_zhuanye;
    @BindView(R.id.ll_youxue_zhuanye)
    LinearLayout ll_youxue_zhuanye;
    @BindView(R.id.tv_youxue_paiming)
    TextView tv_youxue_paiming;
    @BindView(R.id.ll_youxue_paiming)
    LinearLayout ll_youxue_paiming;

    private String searchContent="";
    private String xueXiaoOrder="";
    private String guoJiaOrder="0";
    private String zhuanYeOrder="0";
    private String QSOrder="0";

    @Override
    protected int getContentView() {
        setAppTitle("留学");
        setAppRightImg(R.drawable.share);
        return R.layout.liuxue_act;
    }

    @Override
    protected void initView() {
        et_liuxue_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                searchContent =s.toString();
                getData(1,false);
            }
        });

        rv_liuxue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
                    ll_liuxue_search.requestFocusFromTouch();
                    PhoneUtils.hiddenKeyBoard(mContext, et_liuxue_search);
                }
                return false;
            }
        });

        adapter = new MyLoadMoreAdapter<YouXueObj>(mContext, R.layout.youxue_item, pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, YouXueObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_youxue);
                GlideUtils.into(mContext,bean.getImage_url(),imageView);
                holder.setText(R.id.tv_youxue_title,bean.getSubtitle());
                holder.setText(R.id.tv_youxue_second_title,bean.getEnglish_title());
                holder.setText(R.id.tv_youxue_xingqu,bean.getInterested_peopleum()+"人感兴趣");

                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.dataId,bean.getId());
                        intent.putExtra(IntentParam.guoJia,bean.getSubtitle());
                        STActivity(intent,LiuXueDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_liuxue.setAdapter(adapter);
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
        map.put("school", xueXiaoOrder);
        map.put("countrie_region_id", guoJiaOrder);
        map.put("profession_id", zhuanYeOrder);
        map.put("qs_ranking", QSOrder);
        map.put("pagesize", pagesize+"");
        map.put("page", page+"");
        map.put("sign",getSign(map));
        ApiRequest.getLiuXueList(map, new MyCallBack<List<YouXueObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<YouXueObj> list) {
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

    @OnClick({R.id.iv_liuexue_liuyan})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.iv_liuexue_liuyan:
                Intent intent = new Intent();
                intent.putExtra(IntentParam.qianZhengType, WoYaoLiuYanActivity.type_4);
                STActivity(intent, WoYaoLiuYanActivity.class);
                break;
        }
    }

    @OnClick({R.id.tv_liuxue_shenqing, R.id.ll_youxue_guojia, R.id.ll_youxue_xuexiao, R.id.ll_youxue_zhuanye, R.id.ll_youxue_paiming})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_liuxue_shenqing:
                STActivity(LiuXueShenQingActivity.class);
                break;
            case R.id.ll_youxue_guojia:
                break;
            case R.id.ll_youxue_xuexiao:
                break;
            case R.id.ll_youxue_zhuanye:
                break;
            case R.id.ll_youxue_paiming:
                break;
        }
    }
}

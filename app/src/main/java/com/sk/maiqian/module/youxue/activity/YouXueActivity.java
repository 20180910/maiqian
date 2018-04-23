package com.sk.maiqian.module.youxue.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.baseclass.view.MyPopupwindow;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.activity.WoYaoLiuYanActivity;
import com.sk.maiqian.module.youxue.network.ApiRequest;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;
import com.sk.maiqian.module.youxue.network.response.YouXueObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class YouXueActivity extends BaseActivity {

    @BindView(R.id.rv_youxue)
    MyRecyclerView rv_youxue;

    MyLoadMoreAdapter adapter;
    @BindView(R.id.tv_youxue_shenqing)
    MyTextView tv_youxue_shenqing;
    @BindView(R.id.et_youxue_search)
    MyEditText et_youxue_search;
    @BindView(R.id.ll_youxue_search)
    LinearLayout ll_youxue_search;
    @BindView(R.id.ll_youxue_guojia)
    LinearLayout ll_youxue_guojia;
    @BindView(R.id.ll_youxue_renqi)
    LinearLayout ll_youxue_renqi;
    @BindView(R.id.iv_youxue_liuyan)
    ImageView iv_youxue_liuyan;
    @BindView(R.id.iv_youxue)
    ImageView iv_youxue;

    @BindView(R.id.tv_youxue_guojia)
    TextView tv_youxue_guojia;

    @BindView(R.id.tv_youxue_renqi)
    TextView tv_youxue_renqi;

    private String searchContent="";
    private String guoJiaOrder ="0";
    private String renQiOrder="0";
    private List<GuoJiaObj> guoJiaObjList;

    @Override
    protected int getContentView() {
        setAppTitle("游学");
        setAppRightImg(R.drawable.share);
        return R.layout.youxue_act;
    }

    @Override
    protected void initView() {
        String imgUrl = SPUtils.getString(mContext, AppXml.youXueImg, null);
        if (imgUrl != null) {
            GlideUtils.intoD(mContext,imgUrl,iv_youxue,R.drawable.youxue_banner);
        }
        et_youxue_search.addTextChangedListener(new TextWatcher() {
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

        rv_youxue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE){
                    ll_youxue_search.requestFocusFromTouch();
                    PhoneUtils.hiddenKeyBoard(mContext, et_youxue_search);
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
                        STActivity(intent,YouXueDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_youxue.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        showProgress();
        getGuoJia(false);
        getData(1,false);
    }

    private void getGuoJia(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getGuoJia(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list, int errorCode, String msg) {
                guoJiaObjList=new ArrayList<GuoJiaObj>();
                GuoJiaObj guoJiaObj=new GuoJiaObj("0","所有国家");
                guoJiaObjList.add(guoJiaObj);
                guoJiaObjList.addAll(list);
                if(isShow){
                    showGuoJia(guoJiaObjList);
                }
            }
        });

    }


    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("search_term", searchContent);
        map.put("countrie_region_id", guoJiaOrder);
        map.put("most_popular", renQiOrder);
        map.put("pagesize", pagesize+"");
        map.put("page", page+"");
        map.put("sign",getSign(map));
        ApiRequest.getYouXueList(map, new MyCallBack<List<YouXueObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<YouXueObj> list, int errorCode, String msg) {
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

    @OnClick({R.id.tv_youxue_shenqing, R.id.ll_youxue_guojia, R.id.ll_youxue_renqi, R.id.iv_youxue_liuyan})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_youxue_shenqing:
                STActivity(YouXueShenQingActivity.class);
                break;
            case R.id.ll_youxue_guojia:
                if(isEmpty(guoJiaObjList)){
                    showLoading();
                    getGuoJia(true);
                }else{
                    showGuoJia(guoJiaObjList);
                }
                break;
            case R.id.ll_youxue_renqi:
                List<GuoJiaObj>list=new ArrayList<>();
                list.add(new GuoJiaObj("0","默认"));
                list.add(new GuoJiaObj("1","人气从高到低"));
                list.add(new GuoJiaObj("2","人气从低到高"));
                showRenQi(list);
                break;
            case R.id.iv_youxue_liuyan:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.qianZhengType,WoYaoLiuYanActivity.type_3);
                STActivity(intent,WoYaoLiuYanActivity.class);
                break;
        }
    }

    private void showGuoJia(List<GuoJiaObj> list) {
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyPopupwindow myPopupwindow=new MyPopupwindow(mContext,view, PhoneUtils.getScreenWidth(mContext)/2,-1);
        myPopupwindow.setElevat(PhoneUtils.dip2px(mContext,10));
        MyRecyclerView rv_youxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<GuoJiaObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GuoJiaObj bean) {
                holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        myPopupwindow.dismiss();
                        showLoading();
                        guoJiaOrder=bean.getCountrie_region_id();
                        if(bean.getCountrie_region_id().equals("0")){
                            tv_youxue_guojia.setText("国家");
                        }else{
                            tv_youxue_guojia.setText(bean.getTitle());
                        }
                        getData(1,false);
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);
        myPopupwindow.showAsDropDown(ll_youxue_guojia);
    }
    private void showRenQi(List<GuoJiaObj> list) {
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyPopupwindow myPopupwindow=new MyPopupwindow(mContext,view, PhoneUtils.getScreenWidth(mContext)/2,-1);
        myPopupwindow.setElevat(PhoneUtils.dip2px(mContext,10));
        MyRecyclerView rv_youxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<GuoJiaObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GuoJiaObj bean) {
                holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        myPopupwindow.dismiss();
                        showLoading();
                        renQiOrder=bean.getCountrie_region_id();
                        if(bean.getCountrie_region_id().equals("0")){
                            tv_youxue_renqi.setText("人气排名");
                        }else{
                            tv_youxue_renqi.setText(bean.getTitle());
                        }
                        getData(1,false);
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);

        myPopupwindow.showAsDropDown(ll_youxue_guojia,PhoneUtils.getScreenWidth(mContext)/2,0);

    }
}

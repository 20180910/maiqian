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
import com.sk.maiqian.module.liuxue.network.ApiRequest;
import com.sk.maiqian.module.liuxue.network.response.YouXueObj;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;

import java.util.ArrayList;
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

    @BindView(R.id.iv_liuxue)
    ImageView iv_liuxue;
    @BindView(R.id.iv_liuexue_liuyan)
    ImageView iv_liuexue_liuyan;
    @BindView(R.id.tv_liuxue_shenqing)
    MyTextView tv_liuxue_shenqing;
    @BindView(R.id.et_liuxue_search)
    MyEditText et_liuxue_search;
    @BindView(R.id.ll_liuxue_search)
    LinearLayout ll_liuxue_search;
    @BindView(R.id.tv_liuxue_guojia)
    TextView tv_liuxue_guojia;
    @BindView(R.id.ll_liuxue_guojia)
    LinearLayout ll_liuxue_guojia;
    @BindView(R.id.tv_liuxue_xuexiao)
    TextView tv_liuxue_xuexiao;
    @BindView(R.id.ll_liuxue_xuexiao)
    LinearLayout ll_liuxue_xuexiao;
    @BindView(R.id.tv_liuxue_zhuanye)
    TextView tv_liuxue_zhuanye;
    @BindView(R.id.ll_liuxue_zhuanye)
    LinearLayout ll_liuxue_zhuanye;
    @BindView(R.id.tv_liuxue_paiming)
    TextView tv_liuxue_paiming;
    @BindView(R.id.ll_liuxue_paiming)
    LinearLayout ll_liuxue_paiming;

    private String searchContent="";
    private String xueXiaoOrder="";
    private String guoJiaOrder="0";
    private String zhuanYeOrder="0";
    private String QSOrder="0";
    private ArrayList<GuoJiaObj> guoJiaObjList;
    private List<GuoJiaObj> zhuanYeList=new ArrayList<>();

    @Override
    protected int getContentView() {
        setAppTitle("留学");
        setAppRightImg(R.drawable.share);
        return R.layout.liuxue_act;
    }

    @Override
    protected void initView() {
        String imgUrl = SPUtils.getString(mContext, AppXml.liuXueImg, null);
        if (imgUrl != null) {
            GlideUtils.intoD(mContext,imgUrl,iv_liuxue,R.drawable.liuxue_banner);
        }
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
        getGuoJia(false);
        getZhuanYe(false);
        getData(1,false);
    }
    private void getZhuanYe(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getZhuanYe(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list) {
                if(isEmpty(list)){
                    showMsg("暂无专业信息");
                    return;
                }
                zhuanYeList.clear();
                zhuanYeList.add(new GuoJiaObj("0","所有专业"));
                zhuanYeList.addAll(list);
                if(isShow){
                    showGuoJia(zhuanYeList,2);
                }
            }
        });
    }
    private void getGuoJia(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        com.sk.maiqian.module.youxue.network.ApiRequest.getGuoJia(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list) {
                guoJiaObjList =new ArrayList<GuoJiaObj>();
                GuoJiaObj guoJiaObj=new GuoJiaObj("0","所有国家");
                guoJiaObjList.add(guoJiaObj);
                guoJiaObjList.addAll(list);
                if(isShow){
                    showGuoJia(guoJiaObjList,1);
                }
            }
        });

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

    @OnClick({R.id.tv_liuxue_shenqing, R.id.ll_liuxue_guojia, R.id.ll_liuxue_xuexiao, R.id.ll_liuxue_zhuanye, R.id.ll_liuxue_paiming})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_liuxue_shenqing:
                STActivity(LiuXueShenQingActivity.class);
                break;
            case R.id.ll_liuxue_guojia:
                if(isEmpty(guoJiaObjList)){
                    showLoading();
                    getGuoJia(true);
                }else{
                    showGuoJia(guoJiaObjList,1);
                }
                break;
            case R.id.ll_liuxue_xuexiao:
                break;
            case R.id.ll_liuxue_zhuanye:
                if(isEmpty(zhuanYeList)){
                    showLoading();
                    getGuoJia(true);
                }else{
                    showGuoJia(zhuanYeList,2);
                }
                break;
            case R.id.ll_liuxue_paiming:
                List<GuoJiaObj>list=new ArrayList<>();
                list.add(new GuoJiaObj("0","默认"));
                list.add(new GuoJiaObj("1","QS排名从高到低"));
                list.add(new GuoJiaObj("2","QS排名从低到高"));
                showPaiMing(list);
                break;
        }
    }

    private void showPaiMing(List<GuoJiaObj> list) {
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyPopupwindow myPopupwindow=new MyPopupwindow(mContext,view, PhoneUtils.getScreenWidth(mContext)/3,-1);
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
                        QSOrder=bean.getCountrie_region_id();
                        if(bean.getCountrie_region_id().equals("0")){
                            tv_liuxue_paiming.setText("QS排名");
                        }else{
                            tv_liuxue_paiming.setText(bean.getTitle());
                        }
                        getData(1,false);
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);

        myPopupwindow.showAsDropDown(ll_liuxue_guojia,PhoneUtils.getScreenWidth(mContext)*2/3,0);

    }


    private void showGuoJia(List<GuoJiaObj> list,int flag) {
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyPopupwindow myPopupwindow=new MyPopupwindow(mContext,view, PhoneUtils.getScreenWidth(mContext)/3,-1);
        myPopupwindow.setElevat(PhoneUtils.dip2px(mContext,10));
        MyRecyclerView rv_liuxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<GuoJiaObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GuoJiaObj bean) {
                if(flag==1){
                    holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                }else{
                    holder.setText(R.id.tv_youxue_popu_guojia,bean.getMajor_name());
                }
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        myPopupwindow.dismiss();
                        showLoading();
                        switch (flag){
                            case 1://国家
                                guoJiaOrder=bean.getCountrie_region_id();
                                if(bean.getCountrie_region_id().equals("0")){
                                    tv_liuxue_guojia.setText("国家");
                                }else{
                                    tv_liuxue_guojia.setText(bean.getTitle());
                                }
                            break;
                            case 2://专业
                                zhuanYeOrder=bean.getMajor_id();
                                if(bean.getMajor_id().equals("0")){
                                    tv_liuxue_zhuanye.setText("专业");
                                }else{
                                    tv_liuxue_zhuanye.setText(bean.getMajor_name());
                                }
                            break;
                        }

                        getData(1,false);
                    }
                });
            }
        };
        adapter.setList(list);
        rv_liuxue_guojia.setAdapter(adapter);
        int offset=0;
        if(flag==2){
            offset=PhoneUtils.getScreenWidth(mContext)/3;
        }
        myPopupwindow.showAsDropDown(ll_liuxue_guojia,offset,0);
    }
}

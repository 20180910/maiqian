package com.sk.maiqian.module.liuxue.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.FlowLayout;
import com.github.customview.MyTextView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.response.CollectObj;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.youxue.network.ApiRequest;
import com.sk.maiqian.module.youxue.network.response.YouXueDetailObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LiuXueDetailActivity extends BaseActivity {

    @BindView(R.id.iv_liuxue_detail)
    ImageView iv_liuxue_detail;
    @BindView(R.id.tv_liuxue_detail_title)
    TextView tv_liuxue_detail_title;
    @BindView(R.id.tv_liuxue_detail_second_title)
    TextView tv_liuxue_detail_second_title;
    @BindView(R.id.tv_liuxue_detail_paiming)
    TextView tv_liuxue_detail_paiming;
    @BindView(R.id.tv_liuxue_detail_param11)
    TextView tv_liuxue_detail_param11;
    @BindView(R.id.tv_liuxue_detail_param12)
    TextView tv_liuxue_detail_param12;
    @BindView(R.id.tv_liuxue_detail_param21)
    TextView tv_liuxue_detail_param21;
    @BindView(R.id.tv_liuxue_detail_param22)
    TextView tv_liuxue_detail_param22;
    @BindView(R.id.tv_liuxue_detail_param31)
    TextView tv_liuxue_detail_param31;
    @BindView(R.id.tv_liuxue_detail_param32)
    TextView tv_liuxue_detail_param32;
    @BindView(R.id.tv_liuxue_detail_param41)
    TextView tv_liuxue_detail_param41;
    @BindView(R.id.tv_liuxue_detail_param42)
    TextView tv_liuxue_detail_param42;
    @BindView(R.id.tv_liuxue_detail_content)
    TextView tv_liuxue_detail_content;
    @BindView(R.id.rv_liuxue_detail_fengguang)
    RecyclerView rv_liuxue_detail_fengguang;
    @BindView(R.id.tv_liuxue_detail_zixun)
    TextView tv_liuxue_detail_zixun;
    @BindView(R.id.tv_liuxue_detail_collection)
    TextView tv_liuxue_detail_collection;
    @BindView(R.id.tv_liuxue_detail_shenqing)
    TextView tv_liuxue_detail_shenqing;
    @BindView(R.id.fl_liuxue_detail_zhuanye)
    FlowLayout fl_liuxue_detail_zhuanye;


    private String dataId;
    private String id;
    private String guoJia;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
//        setTitleBackgroud(R.color.transparent);
        return R.layout.liuxuedetail_act;
    }

    @Override
    protected void initView() {
        dataId = getIntent().getStringExtra(IntentParam.dataId);
        guoJia = getIntent().getStringExtra(IntentParam.guoJia);





        scrollChangeBackground(nsv,toolbar);
       /* int screenWidth = PhoneUtils.getScreenWidth(mContext)*2/3;
        toolbar.getBackground().mutate().setAlpha(0);
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY >= 0 && scrollY <= screenWidth) {
                    double alpha = (double) scrollY / screenWidth;
                    toolbar.getBackground().mutate().setAlpha((int) (alpha * 255));
                } else {
                    toolbar.getBackground().mutate().setAlpha(255);
                }
            }
        });*/
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }



    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("travel_study_bbroad_id", dataId);
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.youXueDetail(map, new MyCallBack<YouXueDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(YouXueDetailObj obj, int errorCode, String msg) {
                setData(obj);
            }
        });
    }

    private void setData(YouXueDetailObj obj) {
        id = obj.getId();
        GlideUtils.intoD(mContext,obj.getImage_url(),iv_liuxue_detail,R.drawable.liuxue_img);

        tv_liuxue_detail_content.setText(obj.getIntroduce());
        tv_liuxue_detail_title.setText(obj.getTitle());
        tv_liuxue_detail_second_title.setText(obj.getEnglish_title());
        tv_liuxue_detail_paiming.setText(obj.getQs_ranking()+"");
        if(notEmpty(obj.getCanshu_list())){
            List<YouXueDetailObj.CanshuListBean> canshuList = obj.getCanshu_list();
            if(canshuList.size()>0){
                tv_liuxue_detail_param11.setText(canshuList.get(0).getTitle());
                tv_liuxue_detail_param12.setText(canshuList.get(0).getContent());
            }
            if(canshuList.size()>1){
                tv_liuxue_detail_param21.setText(canshuList.get(1).getTitle());
                tv_liuxue_detail_param22.setText(canshuList.get(1).getContent());
            }
            if(canshuList.size()>2){
                tv_liuxue_detail_param31.setText(canshuList.get(2).getTitle());
                tv_liuxue_detail_param32.setText(canshuList.get(2).getContent());
            }
            if(canshuList.size()>3){
                tv_liuxue_detail_param41.setText(canshuList.get(3).getTitle());
                tv_liuxue_detail_param42.setText(canshuList.get(3).getContent());
            }
        }

        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<String>(mContext,R.layout.youxuedetail_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, String bean) {
                ImageView imageView = holder.getImageView(R.id.iv_youxue_detail_item);
                GlideUtils.into(mContext,bean,imageView);
            }
        };
        adapter.setList(obj.getScenery_list());
        rv_liuxue_detail_fengguang.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_liuxue_detail_fengguang.setNestedScrollingEnabled(false);
        rv_liuxue_detail_fengguang.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,12),R.color.white));
        rv_liuxue_detail_fengguang.setAdapter(adapter);


        if (obj.getIs_collect() == 1) {
            tv_liuxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
        } else {
            tv_liuxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
        }


        fl_liuxue_detail_zhuanye.removeAllViews();
        if(notEmpty(obj.getMajor_list())){
            List<String> majorList = obj.getMajor_list();
            for (int i = 0; i <majorList.size(); i++) {
                MyTextView textView=new MyTextView(mContext);
                textView.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
                textView.setTextColor(ContextCompat.getColor(mContext,R.color.blue_00));
                textView.setAllLine(true);
                textView.setRadius(PhoneUtils.dip2px(mContext,5));
                textView.setText(majorList.get(i));
                textView.setPadding(PhoneUtils.dip2px(mContext,13),PhoneUtils.dip2px(mContext,5),PhoneUtils.dip2px(mContext,13),PhoneUtils.dip2px(mContext,5));
                FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0,0,PhoneUtils.dip2px(mContext,7),0);
                textView.setLayoutParams(layoutParams);
                textView.complete();

                fl_liuxue_detail_zhuanye.addView(textView);

            }
        }

    }

    @OnClick({R.id.tv_liuxue_detail_zixun, R.id.tv_liuxue_detail_collection, R.id.tv_liuxue_detail_shenqing})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_liuxue_detail_zixun:
                showZiXunDialog();
                break;
            case R.id.tv_liuxue_detail_collection:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                collect(id, "4", new MyCallBack<CollectObj>(mContext) {
                    @Override
                    public void onSuccess(CollectObj obj, int errorCode, String msg) {
                        showMsg(obj.getMsg());
                        if (obj.getIs_collect() == 1) {
                            tv_liuxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
                        } else {
                            tv_liuxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
                        }
                    }
                });
                break;
            case R.id.tv_liuxue_detail_shenqing:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.detailInto,true);
                intent.putExtra(IntentParam.guoJia,guoJia);
                STActivity(intent,LiuXueShenQingActivity.class);
                break;
        }
    }
}

package com.sk.maiqian.module.youxue.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.library.base.view.richedit.RichEditor;
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

public class YouXueDetailActivity extends BaseActivity {

    @BindView(R.id.iv_youxue_detail)
    ImageView iv_youxue_detail;
    @BindView(R.id.tv_youxue_detail_title)
    TextView tv_youxue_detail_title;
    @BindView(R.id.tv_youxue_detail_param11)
    TextView tv_youxue_detail_param11;
    @BindView(R.id.tv_youxue_detail_param12)
    TextView tv_youxue_detail_param12;
    @BindView(R.id.tv_youxue_detail_param21)
    TextView tv_youxue_detail_param21;
    @BindView(R.id.tv_youxue_detail_param22)
    TextView tv_youxue_detail_param22;
    @BindView(R.id.tv_youxue_detail_param31)
    TextView tv_youxue_detail_param31;
    @BindView(R.id.tv_youxue_detail_param32)
    TextView tv_youxue_detail_param32;
    @BindView(R.id.tv_youxue_detail_content)
    TextView tv_youxue_detail_content;
    @BindView(R.id.rv_youxue_detail_fengguang)
    RecyclerView rv_youxue_detail_fengguang;
    @BindView(R.id.re_youxue_detail_tese)
    RichEditor re_youxue_detail_tese;
    @BindView(R.id.tv_youxue_detail_zixun)
    TextView tv_youxue_detail_zixun;
    @BindView(R.id.tv_youxue_detail_collection)
    TextView tv_youxue_detail_collection;
    @BindView(R.id.tv_youxue_detail_shenqing)
    TextView tv_youxue_detail_shenqing;


    private String dataId;
    private String id;
    private String guoJiaId;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
//        setTitleBackgroud(R.color.transparent);
        return R.layout.youxuedetail_act;
    }

    @Override
    protected void initView() {
        dataId = getIntent().getStringExtra(IntentParam.dataId);

        scrollChangeBackground(nsv,toolbar);
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
        guoJiaId = obj.getCountrie_region_id();
        GlideUtils.intoD(mContext,obj.getImage_url(),iv_youxue_detail,R.drawable.liuxue_img);

        tv_youxue_detail_content.setText(obj.getIntroduce());
        tv_youxue_detail_title.setText(obj.getTitle());
        if(notEmpty(obj.getCanshu_list())){
            List<YouXueDetailObj.CanshuListBean> canshuList = obj.getCanshu_list();
            if(canshuList.size()>0){
                tv_youxue_detail_param11.setText(canshuList.get(0).getTitle());
                tv_youxue_detail_param12.setText(canshuList.get(0).getContent());
            }
            if(canshuList.size()>1){
                tv_youxue_detail_param21.setText(canshuList.get(1).getTitle());
                tv_youxue_detail_param22.setText(canshuList.get(1).getContent());
            }
            if(canshuList.size()>2){
                tv_youxue_detail_param31.setText(canshuList.get(2).getTitle());
                tv_youxue_detail_param32.setText(canshuList.get(2).getContent());
            }
        }
        re_youxue_detail_tese.setInputEnabled(false);
        re_youxue_detail_tese.setEditorFontSize(13);
        re_youxue_detail_tese.setEditorFontColor(ContextCompat.getColor(mContext,R.color.gray_66));
        re_youxue_detail_tese.setHtml(obj.getTravel_characteristics());

        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<String>(mContext,R.layout.youxuedetail_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, String bean) {
                ImageView imageView = holder.getImageView(R.id.iv_youxue_detail_item);
                GlideUtils.into(mContext,bean,imageView);
            }
        };
        adapter.setList(obj.getScenery_list());
        rv_youxue_detail_fengguang.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_youxue_detail_fengguang.setNestedScrollingEnabled(false);
        rv_youxue_detail_fengguang.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,12),R.color.white));
        rv_youxue_detail_fengguang.setAdapter(adapter);


        if (obj.getIs_collect() == 1) {
            tv_youxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
        } else {
            tv_youxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
        }

    }


    @OnClick({R.id.tv_youxue_detail_zixun, R.id.tv_youxue_detail_collection, R.id.tv_youxue_detail_shenqing})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_youxue_detail_zixun:
                showZiXunDialog(guoJiaId);
                break;
            case R.id.tv_youxue_detail_collection:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                collect(id, "3", new MyCallBack<CollectObj>(mContext) {
                    @Override
                    public void onSuccess(CollectObj obj, int errorCode, String msg) {
                        showMsg(msg);
                        if (obj.getIs_collect() == 1) {
                            tv_youxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
                        } else {
                            tv_youxue_detail_collection.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
                        }
                    }
                });
                break;
            case R.id.tv_youxue_detail_shenqing:
                STActivity(YouXueShenQingActivity.class);
                break;
        }
    }
}

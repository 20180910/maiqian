package com.sk.maiqian.module.yingyupeixun.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.response.CollectObj;
import com.sk.maiqian.module.home.network.response.ZiXunObj;
import com.sk.maiqian.module.yingyupeixun.network.ApiRequest;
import com.sk.maiqian.module.yingyupeixun.network.response.KeChengDetailObj;
import com.sk.maiqian.tools.TablayoutUtils;
import com.sk.maiqian.tools.TextViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/23.
 */

public class KeChengDetailActivity extends BaseActivity {


    @BindView(R.id.iv_peixun_detail)
    ImageView iv_peixun_detail;
    @BindView(R.id.tv_peixun_detail_title)
    TextView tv_peixun_detail_title;
    @BindView(R.id.tv_peixun_detail_price)
    TextView tv_peixun_detail_price;
    @BindView(R.id.tv_peixun_detail_old_price)
    TextView tv_peixun_detail_old_price;
    @BindView(R.id.tv_peixun_detail_address)
    TextView tv_peixun_detail_address;
    @BindView(R.id.tv_peixun_detail_type1)
    TextView tv_peixun_detail_type1;
    @BindView(R.id.tv_peixun_detail_type2)
    TextView tv_peixun_detail_type2;
    @BindView(R.id.tv_peixun_detail_type3)
    TextView tv_peixun_detail_type3;
    @BindView(R.id.tv_peixun_detail_type4)
    TextView tv_peixun_detail_type4;
    @BindView(R.id.tv_peixun_detail_banji)
    TextView tv_peixun_detail_banji;
    @BindView(R.id.tv_peixun_detail_keshi)
    TextView tv_peixun_detail_keshi;
    @BindView(R.id.tv_peixun_detail_shangke)
    TextView tv_peixun_detail_shangke;
    @BindView(R.id.tv_peixun_detail_duixiang)
    TextView tv_peixun_detail_duixiang;
    @BindView(R.id.tv_peixun_detail_mubiao)
    TextView tv_peixun_detail_mubiao;
    @BindView(R.id.tv_peixun_detail_collect)
    TextView tv_peixun_detail_collect;
    @BindView(R.id.rv_shouke_laoshi)
    RecyclerView rv_shouke_laoshi;
    @BindView(R.id.re_peixun_detail_tese)
    RichEditor re_peixun_detail_tese;
    @BindView(R.id.tv_peixun_detail_youxiaoqi)
    TextView tv_peixun_detail_youxiaoqi;

    @BindView(R.id.ll_peixun_detail_kecheng)
    LinearLayout ll_peixun_detail_kecheng;
    @BindView(R.id.ll_peixun_detail_goumai)
    LinearLayout ll_peixun_detail_goumai;

    @BindView(R.id.rv_peixun_detail_yuyue)
    RecyclerView rv_peixun_detail_yuyue;

    @BindView(R.id.tab_peixun_detail)
    TabLayout tab_peixun_detail;

    BaseRecyclerAdapter adapter;

    private String kechengId;
    private int goumaiTop;
    private int kechengTop;
    private TextView goumai;
    private TextView kechengDetail;
    private String englishTrainingId;
    private KeChengDetailObj keChengDetailObj;
    private String flag;

    @Override
    protected int getContentView() {
        setAppTitle("课程详情");
        setAppRightImg(R.drawable.share);
        return R.layout.kechengdetail_act;
    }

    @Override
    protected void initView() {
        kechengId = getIntent().getStringExtra(IntentParam.kechengId);
        flag = getIntent().getStringExtra(IntentParam.flag);

        adapter=new BaseRecyclerAdapter<KeChengDetailObj.TeacherListBean>(mContext,R.layout.kechengdatail_laoshi_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, KeChengDetailObj.TeacherListBean bean) {
                ImageView imageView = holder.getImageView(R.id.iv_peixun_detail_laoshi);
                GlideUtils.into(mContext,bean.getHead_portrait(),imageView);
                holder.setText(R.id.tv_peixun_detail_laoshi_name,bean.getTeacher_name());
                holder.setText(R.id.tv_peixun_detail_laoshi_shuoming,bean.getBiaoqian());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.teacherId,bean.getTeacher_id());
                        STActivity(intent,LaoShiJieShaoActivity.class);
                    }
                });
            }
        };

        List<View> list=new ArrayList<>();
        list.add(ll_peixun_detail_goumai);
        list.add(ll_peixun_detail_kecheng);
        scrollCheckViewIsShow(nsv, list, new OnScrollAutoSelectViewInter() {
            @Override
            public void selectViewPosition(int position,View view) {
                switch (position){
                    case 0:
                        kechengDetail.setSelected(false);
                        goumai.setSelected(true);
                        tab_peixun_detail.getTabAt(1).select();
                    break;
                    case 1:
                        kechengDetail.setSelected(true);
                        goumai.setSelected(false);
                        tab_peixun_detail.getTabAt(0).select();
                    break;
                }
            }
        });
        /*nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(keJian(ll_peixun_detail_goumai)){
                    kechengDetail.setSelected(false);
                    goumai.setSelected(true);
                    tab_peixun_detail.getTabAt(1).select();
                }else if(keJian(ll_peixun_detail_kecheng)){
                    kechengDetail.setSelected(true);
                    goumai.setSelected(false);
                    tab_peixun_detail.getTabAt(0).select();
                }
            }
        });*/
        TablayoutUtils.setTabWidth(tab_peixun_detail,50);

        kechengDetail = new TextView(mContext);
        goumai = new TextView(mContext);

        kechengDetail.setGravity(Gravity.CENTER);
        int[] colors = new int[] {ContextCompat.getColor(mContext,R.color.blue_00) , ContextCompat.getColor(mContext,R.color.gray_66)};
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_selected };
        states[1] = new int[] { };
        ColorStateList stateList=new ColorStateList(states,colors);
        kechengDetail.setTextColor(stateList);
        kechengDetail.setText("课程详情");
        kechengDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kechengDetail.setSelected(true);
                goumai.setSelected(false);
                scrollAutoSelectView(nsv,ll_peixun_detail_kecheng);
            }
        });

        tab_peixun_detail.addTab(tab_peixun_detail.newTab().setCustomView(kechengDetail));

        goumai.setGravity(Gravity.CENTER);
        goumai.setTextColor(stateList);
        goumai.setText("购买说明");
        goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kechengDetail.setSelected(false);
                goumai.setSelected(true);
                scrollAutoSelectView(nsv,ll_peixun_detail_goumai);
            }
        });
        tab_peixun_detail.addTab(tab_peixun_detail.newTab().setCustomView(goumai));
    }
    @Override
    protected void initData() {
        getZiXunData(new MyCallBack<ZiXunObj>(mContext) {
            @Override
            public void onSuccess(ZiXunObj obj, int errorCode, String msg) {
                ziXunObj = obj;
            }
        });
        showProgress();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("english_training_id", kechengId);
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getEnglishPeiXunDetail(map, new MyCallBack<KeChengDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(KeChengDetailObj obj, int errorCode, String msg) {
                setData(obj);
                ll_peixun_detail_goumai.post(new Runnable() {
                    @Override
                    public void run() {
                        kechengTop = ll_peixun_detail_kecheng.getTop();
                        goumaiTop = ll_peixun_detail_goumai.getTop();
                        Log(kechengTop+"==1="+goumaiTop);
                    }
                });
            }
        });

    }

    private void setData(KeChengDetailObj obj) {
        keChengDetailObj = obj;
        englishTrainingId = obj.getEnglish_training_id();
        if(obj.getIs_collect()==1){
            tv_peixun_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.collect_normal2,0,0);
        }else{
            tv_peixun_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.collect_normal,0,0);
        }
        GlideUtils.into(mContext,obj.getImg_url(),iv_peixun_detail);
        tv_peixun_detail_title.setText(obj.getTitle());
        tv_peixun_detail_price.setText("¥"+obj.getPrice());
        tv_peixun_detail_old_price.setText("¥"+obj.getOriginal_price());
        TextViewUtils.underline(tv_peixun_detail_old_price);
        tv_peixun_detail_address.setText(obj.getAddress());
        tv_peixun_detail_type1.setText(obj.getSuits_crowd());
        tv_peixun_detail_type2.setText(obj.getSuits_basic());
        tv_peixun_detail_type3.setText(obj.getNumber_class());
        tv_peixun_detail_type4.setText(obj.getTeacher_nationality());

        tv_peixun_detail_banji.setText(obj.getClass_name());
        tv_peixun_detail_keshi.setText(obj.getClss_hour()+"课时");
        tv_peixun_detail_shangke.setText(obj.getTopclass_time());
        tv_peixun_detail_duixiang.setText(obj.getApplicable_object());
        tv_peixun_detail_mubiao.setText(obj.getLearning_goals());

        re_peixun_detail_tese.setEditorFontSize(13);
        re_peixun_detail_tese.setInputEnabled(false);
        re_peixun_detail_tese.setEditorFontColor(ContextCompat.getColor(mContext,R.color.gray_66));
        re_peixun_detail_tese.setHtml(obj.getCourse_characteristic());

        tv_peixun_detail_youxiaoqi.setText(obj.getPeriod_validity());
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<KeChengDetailObj.CanshuListBean>(mContext,R.layout.kechengdetail_yuyue_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int i, KeChengDetailObj.CanshuListBean bean) {
                holder.setText(R.id.tv_peixun_detail_yuyue_title,bean.getTitle());
                holder.setText(R.id.tv_peixun_detail_yuyue_content,bean.getContent());
            }
        };
        adapter.setList(obj.getCanshu_list());
        rv_peixun_detail_yuyue.setLayoutManager(new LinearLayoutManager(mContext));
        rv_peixun_detail_yuyue.setNestedScrollingEnabled(false);
        rv_peixun_detail_yuyue.setAdapter(adapter);

        this.adapter.setList(obj.getTeacher_list());
        rv_shouke_laoshi.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_shouke_laoshi.setNestedScrollingEnabled(false);
        rv_shouke_laoshi.setAdapter(this.adapter);


    }

    @OnClick({R.id.tv_peixun_detail_zixun, R.id.tv_peixun_detail_buy, R.id.tv_peixun_detail_yuyueshiting,R.id.tv_peixun_detail_collect})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_peixun_detail_collect:
                collect(englishTrainingId, "2", new MyCallBack<CollectObj>(mContext) {
                    @Override
                    public void onSuccess(CollectObj obj, int errorCode, String msg) {
                        showMsg(msg);
                        if (obj.getIs_collect() == 1) {
                            tv_peixun_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
                        } else {
                            tv_peixun_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
                        }
                    }
                });
                break;
            case R.id.tv_peixun_detail_zixun:
                showZiXunDialog();
                break;
            case R.id.tv_peixun_detail_buy:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.keChengDetailObj,keChengDetailObj);
                intent.putExtra(IntentParam.flag,flag);
                STActivity(intent,TiJiaoOrderActivity.class);
                break;
            case R.id.tv_peixun_detail_yuyueshiting:
                showYuYue();
                break;
        }
    }

    private void showYuYue() {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        View view = getLayoutInflater().inflate(R.layout.kechengdetail_popu, null);
        EditText et_yuyue_phone = view.findViewById(R.id.et_yuyue_phone);
        TextView tv_yuyue = view.findViewById(R.id.tv_yuyue);
        tv_yuyue.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                String phone = getSStr(et_yuyue_phone);
                if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }
                showLoading();
                Map<String,String>map=new HashMap<String,String>();
                map.put("english_training_id",kechengId);
                map.put("phone",phone);
                map.put("sign",getSign(map));
                ApiRequest.keChengYuYue(map, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj, int errorCode, String msg) {
                        showMsg(msg);
                        dialog.dismiss();
                    }
                });

            }
        });

        dialog.setContentView(view);
        dialog.show();
    }
}

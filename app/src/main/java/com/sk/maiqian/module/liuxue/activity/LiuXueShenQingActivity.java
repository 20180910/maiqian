package com.sk.maiqian.module.liuxue.activity;

import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.liuxue.network.ApiRequest;
import com.sk.maiqian.module.liuxue.network.response.ShenQingObj;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.youxue.network.request.YouXueShenQingBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/27.
 */

public class LiuXueShenQingActivity extends BaseActivity {
    @BindView(R.id.tv_liuxue_shenqing_guojia)
    TextView tv_liuxue_shenqing_guojia;
    @BindView(R.id.tv_liuxue_shenqing_xuexiao)
    TextView tv_liuxue_shenqing_xuexiao;
    @BindView(R.id.et_liuxue_shenqing_phone)
    MyEditText et_liuxue_shenqing_phone;
    @BindView(R.id.tv_liuxue_shenqing_nianji)
    TextView tv_liuxue_shenqing_nianji;
    @BindView(R.id.tv_liuxue_shenqing_zhuanye)
    TextView tv_liuxue_shenqing_zhuanye;
    @BindView(R.id.ll_liuxue_shenqing_zixun)
    MyLinearLayout ll_liuxue_shenqing_zixun;
    @BindView(R.id.tv_liuxue_shenqing_commit)
    MyTextView tv_liuxue_shenqing_commit;
    private boolean detailInto;

    private List<GuoJiaObj> guoJiaObjList;
    private List<GuoJiaObj> nianJiList;
    private List<GuoJiaObj> zhuanYeList=new ArrayList<>();
    private String guoJia;
    private boolean selectShenQing;

    @Override
    protected int getContentView() {
        setAppTitle("留学申请");
        setAppRightImg(R.drawable.share);
        return R.layout.liuxueshenqing_act;
    }

    @Override
    protected void initView() {
        selectShenQing = getIntent().getBooleanExtra(IntentParam.selectShenQing, false);

        String phone = SPUtils.getString(mContext, AppXml.mobile, null);
        et_liuxue_shenqing_phone.setText(phone);

        detailInto = getIntent().getBooleanExtra(IntentParam.detailInto,false);
        guoJia = getIntent().getStringExtra(IntentParam.guoJia);
        if(detailInto){
            tv_liuxue_shenqing_guojia.setText("想去留学学校?");

            tv_liuxue_shenqing_xuexiao.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            tv_liuxue_shenqing_xuexiao.setOnClickListener(null);
            tv_liuxue_shenqing_xuexiao.setText(guoJia);
        }else{
            getGuoJia(false);
            tv_liuxue_shenqing_guojia.setText("想去留学目的地?");

            tv_liuxue_shenqing_xuexiao.setOnClickListener(new MyOnClickListener() {
                @Override
                protected void onNoDoubleClick(View view) {
                    if(isEmpty(guoJiaObjList)){
                        showLoading();
                        getGuoJia(true);
                    }else{
                        showGuoJia(guoJiaObjList,1);
                    }
                }
            });
        }

    }

    @Override
    protected void initData() {
        getNianJi(false);
        getZhuanYe(false);
        if(selectShenQing){
            showProgress();
            getData(1,false);
        }else{
            showContent();
        }
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String>map=new HashMap<String,String>();
        map.put("type","2");
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getShenQingDetail(map, new MyCallBack<ShenQingObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(ShenQingObj obj, int errorCode, String msg) {
                tv_liuxue_shenqing_xuexiao.setText(obj.getDestination());
                et_liuxue_shenqing_phone.setText(obj.getPhone());
                tv_liuxue_shenqing_nianji.setText(obj.getCity_gradeschool());
                tv_liuxue_shenqing_zhuanye.setText(obj.getAttend_professional());
            }
        });

    }

    private void getZhuanYe(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getZhuanYe(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list, int errorCode, String msg) {
                if(isEmpty(list)){
                    showMsg("暂无专业信息");
                    return;
                }
                zhuanYeList=list;
                if(isShow){
                    showGuoJia(zhuanYeList,3);
                }
            }
        });
    }
    private void getNianJi(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getNianJi(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list, int errorCode, String msg) {
                nianJiList =list;
                if(isShow){
                    showGuoJia(nianJiList,2);
                }
            }
        });

    }
    private void getGuoJia(boolean isShow) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getGuoJia(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list, int errorCode, String msg) {
                guoJiaObjList =list;
                if(isShow){
                    showGuoJia(guoJiaObjList,1);
                }
            }
        });
    }
    @OnClick({ R.id.tv_liuxue_shenqing_nianji, R.id.tv_liuxue_shenqing_zhuanye, R.id.ll_liuxue_shenqing_zixun, R.id.tv_liuxue_shenqing_commit})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_liuxue_shenqing_nianji:
                if(isEmpty(nianJiList)){
                    showLoading();
                    getNianJi(true);
                }else{
                    showGuoJia(nianJiList,2);
                }
                break;
            case R.id.tv_liuxue_shenqing_zhuanye:
                if(isEmpty(zhuanYeList)){
                    showLoading();
                    getZhuanYe(true);
                }else{
                    showGuoJia(zhuanYeList,3);
                }
                break;
            case R.id.ll_liuxue_shenqing_zixun:
                showZiXunDialog("0");
                break;
            case R.id.tv_liuxue_shenqing_commit:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                String guojia = getSStr(tv_liuxue_shenqing_xuexiao);
                String phone=getSStr(et_liuxue_shenqing_phone);
                String nianji=getSStr(tv_liuxue_shenqing_nianji);
                String zhuanye=getSStr(tv_liuxue_shenqing_zhuanye);
                if(!detailInto&&TextUtils.isEmpty(guojia)){
                    showMsg("请选择目的地");
                    return;
                }else if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(nianji)){
                    showMsg("请选择年级");
                    return;
                }else if(TextUtils.isEmpty(zhuanye)){
                    showMsg("请选择专业");
                    return;
                }
                shenQing(guojia,
                        phone,
                        nianji,
                        zhuanye);
                break;
        }
    }
    private void shenQing(String guojia, String phone, String nianji, String zhuanye) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "2");
        map.put("user_id",getUserId());
        map.put("sign", getSign(map));
        YouXueShenQingBody body = new YouXueShenQingBody();
        body.setDestination(guojia);
        body.setPhone(phone);
        body.setCity_gradeschool(nianji);
        body.setAttend_professional(zhuanye);

        com.sk.maiqian.module.youxue.network.ApiRequest.youXueShenQing(map, body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                finish();
            }
        });
    }

    private void showGuoJia(List<GuoJiaObj> list,int flag) {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyRecyclerView rv_youxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<GuoJiaObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GuoJiaObj bean) {
                switch (flag){
                    case 1://国家
                        holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                        break;
                    case 2://年级
                        holder.setText(R.id.tv_youxue_popu_guojia,bean.getClass_name());
                        break;
                    case 3://专业
                        holder.setText(R.id.tv_youxue_popu_guojia,bean.getMajor_name());
                        break;
                }
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        dialog.dismiss();
                        switch (flag){
                            case 1://国家
                                tv_liuxue_shenqing_xuexiao.setText(bean.getTitle());
                            break;
                            case 2://年级
                                tv_liuxue_shenqing_nianji.setText(bean.getClass_name());
                            break;
                            case 3://专业
                                tv_liuxue_shenqing_zhuanye.setText(bean.getMajor_name());
                            break;
                        }
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
    }
}

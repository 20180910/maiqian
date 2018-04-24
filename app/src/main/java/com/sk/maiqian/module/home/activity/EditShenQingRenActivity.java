package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.github.customview.MyImageView;
import com.github.customview.MyTextView;
import com.library.base.BaseObj;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.ShenQingRenObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EditShenQingRenActivity extends BaseActivity {

    @BindView(R.id.et_addpeople_name)
    MyEditText et_addpeople_name;
    @BindView(R.id.tv_addpeople_nan)
    MyTextView tv_addpeople_nan;
    @BindView(R.id.tv_addpeople_nv)
    MyTextView tv_addpeople_nv;
    @BindView(R.id.tv_addpeople_age)
    TextView tv_addpeople_age;
    @BindView(R.id.tv_addpeople_type)
    TextView tv_addpeople_type;
    @BindView(R.id.et_addpeople_code)
    MyEditText et_addpeople_code;
    @BindView(R.id.iv_addpeople_img1)
    MyImageView iv_addpeople_img1;
    @BindView(R.id.iv_addpeople_img2)
    MyImageView iv_addpeople_img2;


    private boolean isEditPeople;

    private int sex=1;
    private int imgType=1;
    private String imgUrl1,imgUrl2;
    private String peopleId;
    private ShenQingRenObj obj;

    @Override
    protected int getContentView() {
        setAppTitle("编辑申请人");
        return R.layout.editshenqingren_act;
    }

    @Override
    protected void initView() {
        obj = (ShenQingRenObj) getIntent().getSerializableExtra(IntentParam.people);
        if(obj!=null){
            isEditPeople =true;
            peopleId = obj.getApplicant_information_id();
        }
    }
    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_addpeople_nan,R.id.tv_addpeople_nv,R.id.tv_addpeople_scan, R.id.tv_addpeople_age, R.id.tv_addpeople_type, R.id.fl_addpeople_img1, R.id.fl_addpeople_img2, R.id.tv_addpeople_save})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_addpeople_nan:
                setSex(true);
                sex=1;
                break;
            case R.id.tv_addpeople_nv:
                setSex(false);
                sex=0;
                break;
            case R.id.tv_addpeople_scan:
                break;
            case R.id.tv_addpeople_age:
                showAge();
                break;
            case R.id.tv_addpeople_type:
                showType();
                break;
            case R.id.fl_addpeople_img1:
                imgType=1;
                showSelectPhotoDialog();
                break;
            case R.id.fl_addpeople_img2:
                imgType=2;
                showSelectPhotoDialog();
                break;
            case R.id.tv_addpeople_save:
                String name = getSStr(et_addpeople_name);
                String age  = getSStr(tv_addpeople_age);
                String type = getSStr(tv_addpeople_type);
                String code = getSStr(et_addpeople_code);
                if(TextUtils.isEmpty(name)){
                    showMsg("请输入姓名");
                    return;
                }else if(TextUtils.isEmpty(age)){
                    showMsg("请选择年龄段");
                    return;
                }else if(TextUtils.isEmpty(type)){
                    showMsg("请选择客户类型");
                    return;
                }else if(TextUtils.isEmpty(code)||code.toString().replace(" ","").length()<=0){
                    showMsg("请输入护照号码");
                    return;
                }else if(TextUtils.isEmpty(imgUrl1)){
                    showMsg("请上传身份证正面照");
                    return;
                }else if(TextUtils.isEmpty(imgUrl2)){
                    showMsg("请上传身份证背面照");
                    return;
                }
                addPeople(name,age,type,code);
                break;
        }
    }

    private void showAge() {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = getLayoutInflater().inflate(R.layout.addpeople_age_popu, null);
        TextView tv_addpeople_chengren=view.findViewById(R.id.tv_addpeople_chengren);
        tv_addpeople_chengren.setOnClickListener(getAddAgeListener(tv_addpeople_chengren,dialog));

        TextView tv_addpeople_qingnian=view.findViewById(R.id.tv_addpeople_qingnian);
        tv_addpeople_qingnian.setOnClickListener(getAddAgeListener(tv_addpeople_qingnian,dialog));

        TextView tv_addpeople_ertong=view.findViewById(R.id.tv_addpeople_ertong);
        tv_addpeople_ertong.setOnClickListener(getAddAgeListener(tv_addpeople_ertong,dialog));
        dialog.setContentView(view);
        dialog.show();
    }
    private void showType() {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = getLayoutInflater().inflate(R.layout.addpeople_type_popu, null);

        MyRecyclerView rv_addpeople_type=view.findViewById(R.id.rv_addpeople_type);
        MyBaseRecyclerAdapter adapter=new MyBaseRecyclerAdapter<String>(mContext,R.layout.addpeople_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int i, String bean) {
                holder.setText(R.id.tv_addpeople_type,bean);
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        dialog.dismiss();
                        tv_addpeople_type.setText(bean);
                    }
                });
            }
        };
        List<String> list=new ArrayList<>();
        list.add("在职人员");
        list.add("自由职业者");
        list.add("退休人员");
        list.add("在校学生");
        list.add("学龄前儿童");
        adapter.setList(list);
        rv_addpeople_type.setAdapter(adapter);

        dialog.setContentView(view);
        dialog.show();
    }

    @NonNull
    private MyOnClickListener getAddAgeListener(TextView textView,final BottomSheetDialog dialog) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                dialog.dismiss();
                tv_addpeople_age.setText(textView.getText());
            }
        };
    }

    private void setSex(boolean isSelectNan) {
        /*tv_addpeople_nan,tv_addpeople_nv*/
        if(isSelectNan){
            tv_addpeople_nan.setTextColor(ContextCompat.getColor(mContext, R.color.blue_00));
            tv_addpeople_nan.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
            tv_addpeople_nan.setSolidColor(ContextCompat.getColor(mContext,R.color.white));
            tv_addpeople_nan.setBottomLeftRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nan.setTopLeftRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nan.setBottomRightRadius(0);
            tv_addpeople_nan.setTopRightRadius(0);
            tv_addpeople_nan.setAllLine(true);
            tv_addpeople_nan.complete();

            tv_addpeople_nv.setTextColor(ContextCompat.getColor(mContext,R.color.gray_99));
            tv_addpeople_nv.setBorderColor(ContextCompat.getColor(mContext,R.color.gray_99));
            tv_addpeople_nv.setSolidColor(ContextCompat.getColor(mContext,R.color.white));
            tv_addpeople_nv.setBottomLeftRadius(0);
            tv_addpeople_nv.setTopLeftRadius(0);
            tv_addpeople_nv.setBottomRightRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nv.setTopRightRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nv.setAllLine(true);
            tv_addpeople_nv.complete();
        }else{

            tv_addpeople_nan.setTextColor(ContextCompat.getColor(mContext,R.color.gray_99));
            tv_addpeople_nan.setBorderColor(ContextCompat.getColor(mContext,R.color.gray_99));
            tv_addpeople_nan.setSolidColor(ContextCompat.getColor(mContext,R.color.white));
            tv_addpeople_nan.setBottomLeftRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nan.setTopLeftRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nan.setBottomRightRadius(0);
            tv_addpeople_nan.setTopRightRadius(0);
            tv_addpeople_nan.setAllLine(true);
            tv_addpeople_nan.complete();

            tv_addpeople_nv.setTextColor(ContextCompat.getColor(mContext, R.color.blue_00));
            tv_addpeople_nv.setBorderColor(ContextCompat.getColor(mContext,R.color.blue_00));
            tv_addpeople_nv.setSolidColor(ContextCompat.getColor(mContext,R.color.white));
            tv_addpeople_nv.setBottomLeftRadius(0);
            tv_addpeople_nv.setTopLeftRadius(0);
            tv_addpeople_nv.setBottomRightRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nv.setTopRightRadius(PhoneUtils.dip2px(mContext,4));
            tv_addpeople_nv.setAllLine(true);
            tv_addpeople_nv.complete();
        }
    }

    private void addPeople(String name, String age, String type, String code) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("applicant_information_id",isEditPeople?peopleId:"0");
        map.put("user_id",getUserId());
        map.put("chinese_name",name);
        map.put("sex",sex==1?"男":"女");
        map.put("age_duan",age);
        map.put("customer_type",type);
        map.put("passport_no",code);
        map.put("id_card_url_zheng",imgUrl1);
        map.put("id_card_url_fan",imgUrl2);
        map.put("sign",getSign(map));
        ApiRequest.addShenQingRen(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case result_select_photo:
                    uploadImg(getSelectPhotoPath(data));
                break;
                case result_take_photo:
                    uploadImg(takePhotoImgSavePath);
                break;
            }
        }
    }

    private void uploadImg(String photoPath) {
        uploadImg(photoPath, new UploadImgCallback() {
            @Override
            public void result(String imgUrl) {
                if(imgType==1){//正面
                    imgUrl1=imgUrl;
                    GlideUtils.into(mContext,imgUrl,iv_addpeople_img1);
                    iv_addpeople_img1.setVisibility(View.VISIBLE);
                }else{//反面
                    imgUrl2=imgUrl;
                    GlideUtils.into(mContext,imgUrl,iv_addpeople_img2);
                    iv_addpeople_img2.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}

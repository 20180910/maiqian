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
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.google.gson.Gson;
import com.library.base.BaseObj;
import com.library.base.tools.has.BitmapUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.Config;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.HuZhaoCallBack;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.ShenQingRenObj;
import com.sk.maiqian.network.NetApiRequest;
import com.sk.maiqian.network.request.ShiBieHuZhaoBody;
import com.sk.maiqian.network.response.HuZhaoObj;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.FlowableEmitter;
import top.zibin.luban.Luban;

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
    @BindView(R.id.iv_addpeople_img3)
    MyImageView iv_addpeople_img3;
    @BindView(R.id.tv_addpeople_save)
    TextView tv_addpeople_save;


    private boolean isEditPeople;

    private int sex=1;
    private int imgType=1;//0护照,1正面，2反面，3自身图片
    private String imgUrl1,imgUrl2,imgUrl3;
    private String peopleId;
    private ShenQingRenObj obj;
    private String name;
    private String age;
    private String type;
    private String code;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
        return R.layout.editshenqingren_act;
    }

    @Override
    protected void initView() {
        obj = (ShenQingRenObj) getIntent().getSerializableExtra(IntentParam.people);
        if(obj!=null){
            setAppTitle("编辑申请人");
            tv_addpeople_save.setText("保存申请人");
            isEditPeople =true;
            peopleId = obj.getApplicant_information_id();
            name=obj.getChinese_name();
            age=obj.getAge_duan();
            type=obj.getCustomer_type();
            code=obj.getPassport_no();

            et_addpeople_name.setText(name);
            tv_addpeople_age.setText(age);
            tv_addpeople_type.setText(type);
            et_addpeople_code.setText(code);


            imgUrl1=obj.getId_card_url_positive();
            imgUrl2=obj.getId_card_url_reverse();
            imgUrl3=obj.getImage_url();
            GlideUtils.into(mContext,imgUrl1,iv_addpeople_img1);
            GlideUtils.into(mContext,imgUrl2,iv_addpeople_img2);
            GlideUtils.into(mContext,imgUrl3,iv_addpeople_img3);
            iv_addpeople_img1.setVisibility(View.VISIBLE);
            iv_addpeople_img2.setVisibility(View.VISIBLE);
            iv_addpeople_img3.setVisibility(View.VISIBLE);
            if("男".equals(obj.getSex())){
                setSex(true);
                sex=1;
            }else{
                setSex(false);
                sex=0;
            }
        }else{
            setAppTitle("添加申请人");
            tv_addpeople_save.setText("添加申请人");
        }
    }
    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_addpeople_nan,R.id.tv_addpeople_nv,R.id.tv_addpeople_scan, R.id.tv_addpeople_age, R.id.tv_addpeople_type, R.id.fl_addpeople_img1, R.id.fl_addpeople_img2, R.id.fl_addpeople_img3, R.id.tv_addpeople_save})
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
                imgType=0;
                PhoneUtils.hiddenKeyBoard(mContext);
                showSelectPhotoDialog();
                break;
            case R.id.tv_addpeople_age:
                showAge();
                break;
            case R.id.tv_addpeople_type:
                showType();
                break;
            case R.id.fl_addpeople_img1:
                imgType=1;
                PhoneUtils.hiddenKeyBoard(mContext);
                showSelectPhotoDialog();
                break;
            case R.id.fl_addpeople_img2:
                imgType=2;
                PhoneUtils.hiddenKeyBoard(mContext);
                showSelectPhotoDialog();
                break;
            case R.id.fl_addpeople_img3:
                imgType=3;
                PhoneUtils.hiddenKeyBoard(mContext);
                showSelectPhotoDialog();
                break;
            case R.id.tv_addpeople_save:
                name = getSStr(et_addpeople_name);
                age = getSStr(tv_addpeople_age);
                type = getSStr(tv_addpeople_type);
                code = getSStr(et_addpeople_code);
                if(TextUtils.isEmpty(name)){
                    showMsg("请输入姓名");
                    return;
                }else if(TextUtils.isEmpty(age)){
                    showMsg("请选择年龄段");
                    return;
                }else if(TextUtils.isEmpty(type)){
                    showMsg("请选择客户类型");
                    return;
                }else if(TextUtils.isEmpty(code)|| code.toString().replace(" ","").length()<=0){
                    showMsg("请输入护照号码");
                    return;
                }else if(TextUtils.isEmpty(imgUrl1)){
                    showMsg("请上传身份证正面照");
                    return;
                }else if(TextUtils.isEmpty(imgUrl2)){
                    showMsg("请上传身份证背面照");
                    return;
                }else if(TextUtils.isEmpty(imgUrl3)){
                    showMsg("请上传个人图片");
                    return;
                }
                addPeople( );
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

    private void addPeople( ) {
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
        map.put("image_url",imgUrl3);
        map.put("sign",getSign(map));
        ApiRequest.addShenQingRen(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
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
        if(imgType==0){
            shiBieHuZhao(photoPath);
        }else{
            uploadImg(photoPath, new UploadImgCallback() {
                @Override
                public void result(String imgUrl) {
                    if(imgType==1){//正面
                        imgUrl1=imgUrl;
                        GlideUtils.into(mContext,imgUrl,iv_addpeople_img1);
                        iv_addpeople_img1.setVisibility(View.VISIBLE);
                    }else if(imgType==2){//反面
                        imgUrl2=imgUrl;
                        GlideUtils.into(mContext,imgUrl,iv_addpeople_img2);
                        iv_addpeople_img2.setVisibility(View.VISIBLE);
                    }else{//个人图片
                        imgUrl3=imgUrl;
                        GlideUtils.into(mContext,imgUrl,iv_addpeople_img3);
                        iv_addpeople_img3.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }

    private void shiBieHuZhao(String photoPath) {
        showLoading();
        RXStart(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull FlowableEmitter<String> subscriber) {
                try {
                    List<File> files = Luban.with(mContext).load(takePhotoImgSavePath).get();
                    File file = files.get(0);
                    String imgStr = BitmapUtils.fileToString(file);
                    subscriber.onNext(imgStr);
                    subscriber.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onNext(String base64) {
                String header="APPCODE " + Config.appcode;
                ShiBieHuZhaoBody body=new ShiBieHuZhaoBody();
                body.setImage(base64);
                NetApiRequest.shiBieHuZhao(header,body, new HuZhaoCallBack(mContext) {
                    @Override
                    public void onSuccess(HuZhaoObj obj) {
                        Log("==="+new Gson().toJson(obj));
                        et_addpeople_name.setText(obj.getName_cn());
                        if("F".equalsIgnoreCase(obj.getSex())){
                            setSex(false);
                            sex=0;
                        }else{
                            setSex(true);
                            sex=1;
                        }
                        et_addpeople_code.setText(obj.getPassport_no());
                    }
                });
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                dismissLoading();
                showToastS("图片处理失败");
            }
        });
    }
}

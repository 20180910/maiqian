package com.sk.maiqian.module.yingyupeixun.activity;

import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.module.yingyupeixun.network.response.KeChengDetailObj;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/26.
 */

public class TiJiaoOrderActivity extends BaseActivity {
    @BindView(R.id.iv_order_img)
    ImageView iv_order_img;
    @BindView(R.id.tv_order_title)
    TextView tv_order_title;
    @BindView(R.id.tv_order_price)
    TextView tv_order_price;
    @BindView(R.id.tv_order_old_price)
    TextView tv_order_old_price;
    @BindView(R.id.tv_order_flag)
    TextView tv_order_flag;
    @BindView(R.id.tv_order_banji)
    TextView tv_order_banji;
    @BindView(R.id.iv_order_jian)
    ImageView iv_order_jian;
    @BindView(R.id.et_order_num)
    MyEditText et_order_num;
    @BindView(R.id.iv_order_jia)
    ImageView iv_order_jia;
    @BindView(R.id.tv_order_total)
    TextView tv_order_total;
    @BindView(R.id.et_order_phone)
    EditText et_order_phone;
    @BindView(R.id.tv_order_pay)
    MyTextView tv_order_pay;
    private KeChengDetailObj keChengDetailObj;
    private String flag;

    @Override
    protected int getContentView() {
        setAppTitle("提交订单");
        setAppRightImg(R.drawable.share);
        return R.layout.tijiaoorder_act;
    }

    @Override
    protected void initView() {
        flag = getIntent().getStringExtra(IntentParam.flag);
        keChengDetailObj = (KeChengDetailObj) getIntent().getSerializableExtra(IntentParam.keChengDetailObj);
        GlideUtils.into(mContext,keChengDetailObj.getImg_url(),iv_order_img);
        tv_order_title.setText(keChengDetailObj.getTitle());
        tv_order_price.setText("¥"+keChengDetailObj.getPrice());
        tv_order_old_price.setText("¥"+keChengDetailObj.getOriginal_price());
        tv_order_flag.setText(flag);
        tv_order_banji.setText(keChengDetailObj.getClass_name());
        et_order_num.setText("1");
        tv_order_total.setText("¥"+keChengDetailObj.getPrice()+"");
        String mobile = SPUtils.getString(mContext, AppXml.mobile,null);
        et_order_phone.setText(mobile);
        tv_order_pay.setText("总计"+keChengDetailObj.getPrice()+"元\t去支付");

        iv_order_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_order_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_order_pay})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_order_pay:
                showPay();
            break;
        }
    }

    private void showPay() {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        View view = getLayoutInflater().inflate(R.layout.tijiaoorder_pay_popu, null);

        dialog.setContentView(view);
        dialog.show();
    }

}

package com.sk.maiqian.module.yingyupeixun.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.customview.MyEditText;
import com.github.customview.MyTextView;
import com.github.rxbus.MyRxBus;
import com.library.base.tools.ZhengZeUtils;
import com.library.base.tools.has.AndroidUtils;
import com.sdklibrary.base.pay.alipay.MyAliOrderBean;
import com.sdklibrary.base.pay.alipay.MyAliPay;
import com.sdklibrary.base.pay.alipay.MyAliPayCallback;
import com.sdklibrary.base.pay.alipay.PayResult;
import com.sdklibrary.base.pay.wxpay.MyWXOrderBean;
import com.sdklibrary.base.pay.wxpay.MyWXPay;
import com.sdklibrary.base.pay.wxpay.MyWXPayCallback;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.Config;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.activity.MainActivity;
import com.sk.maiqian.module.home.activity.PaySuccessActivity;
import com.sk.maiqian.module.home.event.RefreshOrderEvent;
import com.sk.maiqian.module.home.fragment.OrderFragment;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.yingyupeixun.network.response.KeChengDetailObj;
import com.sk.maiqian.module.yingyupeixun.network.response.PeiXunMakeOrderObj;
import com.sk.maiqian.tools.TextViewUtils;

import java.util.HashMap;
import java.util.Map;

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
    private double price;
    private BottomSheetDialog payDialog;

    @Override
    protected int getContentView() {
        setAppTitle("提交订单");
        setAppRightImg(R.drawable.share);
        return R.layout.tijiaoorder_act;
    }

    @Override
    protected void initView() {
        keChengDetailObj = (KeChengDetailObj) getIntent().getSerializableExtra(IntentParam.keChengDetailObj);
        flag = getIntent().getStringExtra(IntentParam.flag);

        price = keChengDetailObj.getPrice();
        GlideUtils.into(mContext,keChengDetailObj.getImg_url(),iv_order_img);
        tv_order_title.setText(keChengDetailObj.getTitle());
        tv_order_price.setText("¥"+keChengDetailObj.getPrice());
        tv_order_old_price.setText("¥"+keChengDetailObj.getOriginal_price());
        TextViewUtils.underline(tv_order_old_price);
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
                int num = Integer.parseInt(getSStr(et_order_num));
                if(num==1){
                    return;
                }
                num--;
                et_order_num.setText(num+"");
                setPriceAndNum(num);
            }
        });
        iv_order_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(getSStr(et_order_num));
                num++;
                et_order_num.setText(num+"");
                setPriceAndNum(num);
            }
        });

    }

    private void setPriceAndNum(int num) {
        double total = AndroidUtils.chengFa(price, num);
        tv_order_total.setText("¥"+total);
        tv_order_pay.setText("总计"+total+"元\t去支付");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_order_pay})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_order_pay:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                String phone = getSStr(et_order_phone);
                if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }
                showPeiXunPay(keChengDetailObj.getEnglish_training_id(),getSStr(et_order_num),phone);
            break;
        }
    }
    BottomSheetDialog peiXunPayDialog;
    protected void showPeiXunPay(String dataId, String num, String phone) {
        peiXunPayDialog = new BottomSheetDialog(mContext);
        peiXunPayDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        View view = getLayoutInflater().inflate(R.layout.tijiaoorder_pay_popu, null);
        RadioButton rb_order_pay = view.findViewById(R.id.rb_order_pay);
        view.findViewById(R.id.tv_commit_liuyan).setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                showLoading();
                Map<String,String> map=new HashMap<String,String>();
                map.put("english_training_id",dataId);
                map.put("number",num);
                map.put("phone",phone);
                map.put("user_id",getUserId());
                map.put("sign",getSign(map));
                com.sk.maiqian.module.yingyupeixun.network.ApiRequest.makePeiXunOrder(map, new MyCallBack<PeiXunMakeOrderObj>(mContext,true) {
                    @Override
                    public void onSuccess(PeiXunMakeOrderObj obj, int errorCode, String msg) {
                        peiXunPayDialog.dismiss();
                        if(rb_order_pay.isChecked()){
                            MyWXOrderBean bean=new MyWXOrderBean();
                            bean.setTotalFee((int) AndroidUtils.chengFa(obj.getCombined(),100));
                            bean.setOut_trade_no(obj.getOrder_no());
                            bean.setBody("英语培训订单支付");
                            weixinPay(bean);
                        }else{
                            MyAliOrderBean bean=new MyAliOrderBean();
                            bean.setTotal_amount(obj.getCombined());
                            bean.setOut_trade_no(obj.getOrder_no());
                            bean.setBody("英语培训订单支付");
                            aliPay(bean);
                        }
                    }
                });
            }
        });

        peiXunPayDialog.setContentView(view);
        peiXunPayDialog.show();
    }
    private void weixinPay(MyWXOrderBean bean) {
        String url = SPUtils.getString(mContext, Config.payType_WX, null);
        bean.setNotifyUrl(url);
//        bean.setIp("1");
        MyWXPay.newInstance(mContext).startPay(bean, new MyWXPayCallback() {
            @Override
            public void paySuccess() {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                STActivity(PaySuccessActivity.class);
                finish();
            }
            @Override
            public void payFail() {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                showMsg("支付失败");
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                Intent intent=new Intent(IntentParam.Action.peiXunPaySuccess);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
                finish();
            }
            @Override
            public void payCancel() {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                showMsg("支付已取消");
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                Intent intent=new Intent(IntentParam.Action.peiXunPaySuccess);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
                finish();
            }
        });
    }

    protected void aliPay(MyAliOrderBean bean) {
        String url = SPUtils.getString(mContext, Config.payType_ZFB, null);
        bean.setAppId(Config.zhifubao_app_id);
        bean.setPid(Config.zhifubao_pid);
        bean.setSiYao(Config.zhifubao_rsa2);
        bean.setNotifyUrl(url);
        bean.setSubject("麦签订单支付");
        MyAliPay.newInstance(mContext).startPay(bean, new MyAliPayCallback() {
            @Override
            public void paySuccess(PayResult payResult) {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                STActivity(PaySuccessActivity.class);
                finish();
            }
            @Override
            public void payFail() {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                showMsg("支付失败");
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                Intent intent=new Intent(IntentParam.Action.peiXunPaySuccess);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
                finish();
            }
            @Override
            public void payCancel() {
                MyRxBus.getInstance().postReplay(new RefreshOrderEvent(OrderFragment.type_2));
                dismissLoading();
                showMsg("支付已取消");
                if(payDialog!=null){
                    payDialog.dismiss();
                }
                Intent intent=new Intent(IntentParam.Action.peiXunPaySuccess);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                STActivity(intent,MainActivity.class);
                finish();
            }
        });
    }


}

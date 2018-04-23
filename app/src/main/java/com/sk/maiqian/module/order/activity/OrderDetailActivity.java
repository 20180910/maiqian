package com.sk.maiqian.module.order.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.baseclass.view.MyDialog;
import com.github.customview.MyTextView;
import com.github.rxbus.MyRxBus;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.event.RefreshOrderEvent;
import com.sk.maiqian.module.home.network.response.OrderQianZhengObj;
import com.sk.maiqian.module.order.network.ApiRequest;
import com.sk.maiqian.module.order.network.response.OrderDetailObj;
import com.sk.maiqian.tools.TextViewUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/30.
 */

public class OrderDetailActivity extends BaseActivity {
    public static final String type_1 = "1";
    public static final String type_2 = "2";
    @BindView(R.id.iv_order_detail_status)
    ImageView iv_order_detail_status;
    @BindView(R.id.tv_order_detail_status)
    TextView tv_order_detail_status;
    @BindView(R.id.tv_order_detail_name)
    TextView tv_order_detail_name;
    @BindView(R.id.tv_order_detail_phone)
    TextView tv_order_detail_phone;
    @BindView(R.id.tv_order_detail_address)
    TextView tv_order_detail_address;
    @BindView(R.id.iv_order_detail_img)
    ImageView iv_order_detail_img;
    @BindView(R.id.tv_order_detail_title)
    TextView tv_order_detail_title;
    @BindView(R.id.tv_order_detail_peixun_price)
    TextView tv_order_detail_peixun_price;
    @BindView(R.id.tv_order_detail_peixun_oldprice)
    TextView tv_order_detail_peixun_oldprice;
    @BindView(R.id.tv_order_detail_peixun_flag)
    TextView tv_order_detail_peixun_flag;
    @BindView(R.id.tv_order_detail_price)
    TextView tv_order_detail_price;
    @BindView(R.id.tv_order_detail_total)
    TextView tv_order_detail_total;
    @BindView(R.id.tv_order_detail_orderno)
    TextView tv_order_detail_orderno;
    @BindView(R.id.tv_order_detail_createtime)
    TextView tv_order_detail_createtime;
    @BindView(R.id.tv_order_detail_paytime)
    TextView tv_order_detail_paytime;
    @BindView(R.id.tv_order_detail_payway)
    TextView tv_order_detail_payway;
    @BindView(R.id.tv_order_detail_kuaidi)
    TextView tv_order_detail_kuaidi;
    @BindView(R.id.tv_order_detail_cancel)
    MyTextView tv_order_detail_cancel;
    @BindView(R.id.tv_order_detail_pay)
    MyTextView tv_order_detail_pay;
    @BindView(R.id.tv_order_detail_complete)
    MyTextView tv_order_detail_complete;
    @BindView(R.id.ll_order_detail_addres)
    LinearLayout ll_order_detail_addres;
    @BindView(R.id.ll_order_detail_kuaidu)
    LinearLayout ll_order_detail_kuaidu;

    private String dataId, type;

    @Override
    protected int getContentView() {
        setAppTitle("签证代办详情");
        setAppRightImg(R.drawable.share);
        return R.layout.qianzhengorder_act;
    }

    @Override
    protected void initView() {
        type = getIntent().getStringExtra(IntentParam.type);
        dataId = getIntent().getStringExtra(IntentParam.dataId);
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
        map.put("type", type);
        map.put("order_no", dataId);
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getOrderDetail(map, new MyCallBack<OrderDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(OrderDetailObj obj, int errorCode, String msg) {
                if (type_1.equals(type)) {

                    tv_order_detail_name.setText(obj.getAddress_recipient());
                    tv_order_detail_address.setText(obj.getAddress_detaile());
                    tv_order_detail_phone.setText(obj.getAddress_phone());

                    ll_order_detail_addres.setVisibility(View.VISIBLE);
                    //签证
                    tv_order_detail_price.setText("¥"+obj.getPrice());
                    tv_order_detail_price.setVisibility(View.VISIBLE);

                    tv_order_detail_peixun_price.setVisibility(View.GONE);
                    tv_order_detail_peixun_oldprice.setVisibility(View.GONE);
                    ll_order_detail_kuaidu.setVisibility(View.VISIBLE);
                } else {
                    ll_order_detail_addres.setVisibility(View.GONE);
                    //培训
                    tv_order_detail_price.setVisibility(View.GONE);

                    tv_order_detail_peixun_price.setText("¥"+obj.getPrice());
                    tv_order_detail_peixun_price.setVisibility(View.VISIBLE);

                    tv_order_detail_peixun_oldprice.setText("¥"+obj.getOriginal_price());
                    TextViewUtils.underline(tv_order_detail_peixun_oldprice);
                    tv_order_detail_peixun_oldprice.setVisibility(View.VISIBLE);

                    tv_order_detail_peixun_flag.setText(obj.getBiaoqian());
                    ll_order_detail_kuaidu.setVisibility(View.GONE);

                }

                tv_order_detail_title.setText(obj.getTitle());
                tv_order_detail_total.setText("¥"+obj.getCombined());
                GlideUtils.into(mContext,obj.getImg_url(),iv_order_detail_img);

                tv_order_detail_orderno.setText(obj.getOrder_no());
                tv_order_detail_createtime.setText(obj.getCreate_add_time());
                tv_order_detail_paytime.setText(obj.getPayment_add_time());
                tv_order_detail_payway.setText(obj.getPay_way());
                tv_order_detail_kuaidi.setText(obj.getCourier());
                setData(obj);
            }
        });
    }

    private void setData(OrderDetailObj obj) {
//1待付款 2已付款 3待评价 4已取消 5已完成
        switch (obj.getOrder_status()){
            case 1:
                iv_order_detail_status.setImageResource(R.drawable.dingdanxiangqing_icon_daifukuan);
                tv_order_detail_status.setText("待付款");
                tv_order_detail_cancel.setVisibility(View.VISIBLE);
                tv_order_detail_pay.setVisibility(View.VISIBLE);
                tv_order_detail_complete.setVisibility(View.GONE);
                break;
            case 2:
                tv_order_detail_status.setText("已付款");
                iv_order_detail_status.setImageResource(R.drawable.dingdanxiangqing_icon_yifukuan);
                tv_order_detail_cancel.setVisibility(View.GONE);
                tv_order_detail_pay.setVisibility(View.GONE);
                tv_order_detail_complete.setVisibility(View.VISIBLE);
                break;
            case 3:
                iv_order_detail_status.setImageResource(R.drawable.dingdanxiangqing_icon_yifukuan);
                tv_order_detail_status.setText("已付款");//待评价
                tv_order_detail_cancel.setVisibility(View.GONE);
                tv_order_detail_pay.setVisibility(View.GONE);
                tv_order_detail_complete.setVisibility(View.VISIBLE);
                break;
            case 4:
                iv_order_detail_status.setImageResource(R.drawable.dingdanxiangqing_icon_yifukuan);
                tv_order_detail_status.setText("已取消");
                tv_order_detail_cancel.setVisibility(View.GONE);
                tv_order_detail_pay.setVisibility(View.GONE);
                tv_order_detail_complete.setVisibility(View.GONE);
                break;
            case 5:
                iv_order_detail_status.setImageResource(R.drawable.dingdanxiangqing_icon_yifukuan);
                tv_order_detail_status.setText("已完成");
                tv_order_detail_cancel.setVisibility(View.GONE);
                tv_order_detail_pay.setVisibility(View.GONE);
                tv_order_detail_complete.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onViewClick(View v) {

    }

    @OnClick({R.id.tv_order_detail_cancel, R.id.tv_order_detail_pay, R.id.tv_order_detail_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_order_detail_cancel:
                mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("确认取消订单吗?");
                mDialog.setNegativeButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        cancelOrder(dataId);
                    }
                });
                mDialog.create().show();
                break;
            case R.id.tv_order_detail_pay:
                payOrder(dataId);
                break;
            case R.id.tv_order_detail_complete:

                completeOrder(dataId);
                break;
        }
    }
    private void completeOrder(String orderNo) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("order_no",orderNo);
        map.put("sign",getSign(map));
        com.sk.maiqian.module.home.network.ApiRequest.completeOrder(map, new MyCallBack<List<OrderQianZhengObj>>(mContext) {
            @Override
            public void onSuccess(List<OrderQianZhengObj> obj, int errorCode, String msg) {
                MyRxBus.getInstance().post(new RefreshOrderEvent(type));
            }
        });
    }

    private void cancelOrder(String orderNo) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("order_no",orderNo);
        map.put("sign",getSign(map));
        com.sk.maiqian.module.home.network.ApiRequest.cancelOrder(map, new MyCallBack<List<OrderQianZhengObj>>(mContext) {
            @Override
            public void onSuccess(List<OrderQianZhengObj> obj, int errorCode, String msg) {
                showMsg("取消成功");
                MyRxBus.getInstance().post(new RefreshOrderEvent(type));
            }
        });
    }

    private void payOrder(String orderNo) {

    }

}

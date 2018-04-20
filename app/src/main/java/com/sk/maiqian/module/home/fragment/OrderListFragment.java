package com.sk.maiqian.module.home.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.view.MyDialog;
import com.github.rxbus.MyConsumer;
import com.github.rxbus.MyRxBus;
import com.library.base.BaseObj;
import com.sk.maiqian.Constant;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.event.RefreshOrderEvent;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.OrderQianZhengObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/12/4.
 */

public class OrderListFragment extends BaseFragment {
    //0全部 1待付款 2已付款 3待评价)
    public static final String type_0="0";
    public static final String type_1="1";
    public static final String type_2="2";
    public static final String type_3="3";

    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;

    OrderFragment fragment;
    private MyLoadMoreAdapter<OrderQianZhengObj> adapter;

    @Override
    protected int getContentView() {
        return R.layout.orderlist_frag;
    }

    public static OrderListFragment newInstance(String flag,String type) {

        Bundle args = new Bundle();
        args.putString(Constant.flag, flag);
        args.putString(Constant.type, type);
        OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initRxBus() {
        super.initRxBus();
        getEvent(RefreshOrderEvent.class, new MyConsumer<RefreshOrderEvent>() {
            @Override
            public void onAccept(RefreshOrderEvent event) {
                if(event.flag.equals(OrderFragment.type_1)){
                    getQianZhengOrder(1,false);
                }else{

                }
            }
        });
    }

    @Override
    protected void initView() {

        adapter =new MyLoadMoreAdapter<OrderQianZhengObj>(mContext,R.layout.orderlist_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, OrderQianZhengObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_qianzheng_order_img);
                GlideUtils.into(mContext,bean.getImg_url(),imageView);
                holder.setText(R.id.tv_qianzheng_order_flag,bean.getVisa_name());


                TextView tv_qianzheng_order_cancel = holder.getTextView(R.id.tv_qianzheng_order_cancel);
                TextView tv_qianzheng_order_pay = holder.getTextView(R.id.tv_qianzheng_order_pay);
                TextView tv_qianzheng_order_complete = holder.getTextView(R.id.tv_qianzheng_order_complete);
                TextView tv_qianzheng_order_delete = holder.getTextView(R.id.tv_qianzheng_order_delete);

                tv_qianzheng_order_cancel.setOnClickListener(getL(1,bean.getOrder_no()));
                tv_qianzheng_order_pay.setOnClickListener(getL(2,bean.getOrder_no()));
                tv_qianzheng_order_complete.setOnClickListener(getL(3,bean.getOrder_no()));
                tv_qianzheng_order_delete.setOnClickListener(getL(4,bean.getOrder_no()));

                //1待付款 2已付款 3待评价 4已取消 5已完成
                switch (bean.getOrder_status()){
                    case 1:
                        holder.setText(R.id.tv_qianzheng_order_status,"待付款");
                        tv_qianzheng_order_cancel.setVisibility(View.VISIBLE);
                        tv_qianzheng_order_pay.setVisibility(View.VISIBLE);
                        tv_qianzheng_order_delete.setVisibility(View.GONE);
                        tv_qianzheng_order_complete.setVisibility(View.GONE);
                    break;
                    case 2:
                        holder.setText(R.id.tv_qianzheng_order_status,"已付款");
                        tv_qianzheng_order_cancel.setVisibility(View.GONE);
                        tv_qianzheng_order_pay.setVisibility(View.GONE);
                        tv_qianzheng_order_delete.setVisibility(View.GONE);
                        tv_qianzheng_order_complete.setVisibility(View.VISIBLE);
                    break;
                    case 3:
                        holder.setText(R.id.tv_qianzheng_order_status,"已付款");//待评价
                        tv_qianzheng_order_cancel.setVisibility(View.GONE);
                        tv_qianzheng_order_pay.setVisibility(View.GONE);
                        tv_qianzheng_order_delete.setVisibility(View.GONE);
                        tv_qianzheng_order_complete.setVisibility(View.VISIBLE);
                    break;
                    case 4:
                        holder.setText(R.id.tv_qianzheng_order_status,"已取消");
                        tv_qianzheng_order_cancel.setVisibility(View.GONE);
                        tv_qianzheng_order_pay.setVisibility(View.GONE);
                        tv_qianzheng_order_delete.setVisibility(View.VISIBLE);
                        tv_qianzheng_order_complete.setVisibility(View.GONE);
                    break;
                    case 5:
                        holder.setText(R.id.tv_qianzheng_order_status,"已完成");
                        tv_qianzheng_order_cancel.setVisibility(View.GONE);
                        tv_qianzheng_order_pay.setVisibility(View.GONE);
                        tv_qianzheng_order_delete.setVisibility(View.GONE);
                        tv_qianzheng_order_complete.setVisibility(View.GONE);
                    break;
                }
                holder.setText(R.id.tv_qianzheng_order_title,bean.getTitle());
                holder.setText(R.id.tv_qianzheng_order_price,"¥"+bean.getPrice());
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_order_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_order_list.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5)));
        rv_order_list.setAdapter(adapter);

    }

    @NonNull
    private MyOnClickListener getL(int flag,String orderNo) {
        return new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                switch (flag){
                    case 1://取消
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
                                cancelOrder(orderNo);
                            }
                        });
                        mDialog.create().show();
                    break;
                    case 2://支付
                        payOrder(orderNo);
                    break;
                    case 3://完成
                        completeOrder(orderNo);
                    break;
                    case 4://删除
                        mDialog=new MyDialog.Builder(mContext);
                        mDialog.setMessage("确认删除订单吗?");
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
                                deleteOrder(orderNo);
                            }
                        });
                        mDialog.create().show();
                    break;
                }
            }
        };
    }

    private void completeOrder(String orderNo) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("order_no",orderNo);
        map.put("sign",getSign(map));
        ApiRequest.completeOrder(map, new MyCallBack<List<OrderQianZhengObj>>(mContext) {
            @Override
            public void onSuccess(List<OrderQianZhengObj> obj) {
                MyRxBus.getInstance().post(new RefreshOrderEvent(OrderFragment.type_1));
            }
        });
    }

    private void cancelOrder(String orderNo) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("order_no",orderNo);
        map.put("sign",getSign(map));
        ApiRequest.cancelOrder(map, new MyCallBack<List<OrderQianZhengObj>>(mContext) {
            @Override
            public void onSuccess(List<OrderQianZhengObj> obj) {
                showMsg("取消成功");
                MyRxBus.getInstance().post(new RefreshOrderEvent(OrderFragment.type_1));
            }
        });
    }

    private void payOrder(String orderNo) {

    }

    private void deleteOrder(String orderNo) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("order_no",orderNo);
        map.put("sign",getSign(map));
        ApiRequest.deleteOrder(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                MyRxBus.getInstance().post(new RefreshOrderEvent(OrderFragment.type_1));
            }
        });

    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        //签证
        if(getArguments().getString(Constant.flag).equals(OrderFragment.type_1)){
            getQianZhengOrder(page, isLoad);
        }else{//培训

        }


    }

    private void getQianZhengOrder(int page, final boolean isLoad) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("type",getArguments().getString(Constant.type));
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getQianZhengOrder(map, new MyCallBack<List<OrderQianZhengObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<OrderQianZhengObj> list) {
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

    @Override
    protected void onViewClick(View v) {

    }
}

package com.sk.maiqian.module.home.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyEditText;
import com.github.customview.MyRadioButton;
import com.github.customview.MyTextView;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.base.SpaceItemDecoration;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.QianZhengDetailObj;
import com.sk.maiqian.module.home.network.response.ShenQingRenObj;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.my.activity.MyAddressListActivity;
import com.sk.maiqian.module.my.network.response.MyAddressObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2018/3/23.
 */

public class DingDanTianXieActivity extends BaseActivity {
    @BindView(R.id.tv_qianzheng_order_title)
    TextView tv_qianzheng_order_title;
    @BindView(R.id.tv_qianzheng_order_time)
    TextView tv_qianzheng_order_time;
    @BindView(R.id.rv_qianzheng_order_people)
    RecyclerView rv_qianzheng_order_people;
    @BindView(R.id.tv_qianzheng_order_add)
    MyTextView tv_qianzheng_order_add;
    @BindView(R.id.et_qianzheng_order_name)
    MyEditText et_qianzheng_order_name;
    @BindView(R.id.et_qianzheng_order_phone)
    MyEditText et_qianzheng_order_phone;
    @BindView(R.id.et_qianzheng_order_email)
    MyEditText et_qianzheng_order_email;
    @BindView(R.id.tv_qianzheng_order_kuaidi)
    TextView tv_qianzheng_order_kuaidi;
    @BindView(R.id.tv_qianzheng_order_address)
    TextView tv_qianzheng_order_address;
    @BindView(R.id.rb_qianzheng_order_wx)
    MyRadioButton rb_qianzheng_order_wx;
    @BindView(R.id.rb_qianzheng_order_price)
    TextView rb_qianzheng_order_price;

    private List<String> kuaiDiList;
    private String kuaiDiName;
    private QianZhengDetailObj qianZhengDetailObj;

    MyBaseRecyclerAdapter adapter;
    private MyLoadMoreAdapter peopleAdaper;
    private BottomSheetDialog peopleDialog;
    private PtrClassicFrameLayout pcfl_refresh;
    private String addressId;

    @Override
    protected int getContentView() {
        setAppTitle("订单填写");
        setAppRightImg(R.drawable.share);
        return R.layout.dingdantianxie_act;
    }

    @Override
    protected void initView() {
        qianZhengDetailObj = (QianZhengDetailObj) getIntent().getSerializableExtra(IntentParam.qianZhengObj);
        tv_qianzheng_order_title.setText(qianZhengDetailObj.getTitle());

        rb_qianzheng_order_price.setText("¥"+qianZhengDetailObj.getPrice());

        adapter=new MyBaseRecyclerAdapter<ShenQingRenObj>(mContext,R.layout.dingdantianxie_people_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, ShenQingRenObj bean) {
                holder.setText(R.id.tv_order_shenqingren_name,bean.getChinese_name());
                holder.setText(R.id.tv_order_shenqingren_zhiwei,bean.getCustomer_type());
                holder.getImageView(R.id.iv_order_shenqingren_zhiwei).setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        getList().remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        rv_qianzheng_order_people.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_qianzheng_order_people.setNestedScrollingEnabled(false);
        rv_qianzheng_order_people.addItemDecoration(new SpaceItemDecoration(10));
        rv_qianzheng_order_people.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        getKuaiDi(false);
    }

    private void getKuaiDi(boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getKuaiDiList(map, new MyCallBack<List<String>>(mContext) {
            @Override
            public void onSuccess(List<String> list) {
                kuaiDiList = list;
                if (isShow) {
                    showKuaiDi(kuaiDiList);
                }
            }
        });
    }

    public void showKuaiDi(List<String> list) {
        BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        View view = getLayoutInflater().inflate(R.layout.addbank_popu, null);
        MyRecyclerView rv_yinhang = view.findViewById(R.id.rv_yinhang);
        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<String>(mContext, R.layout.dingdantianxie_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

                holder.setText(R.id.tv_dingdantianxie_kuaidi, bean);
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        kuaiDiName = bean;
                        tv_qianzheng_order_kuaidi.setText(bean);
                        dialog.dismiss();
                    }
                });
            }
        };
        adapter.setList(list);
        rv_yinhang.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
    }

    @OnClick({R.id.tv_qianzheng_order_time,R.id.tv_qianzheng_order_kuaidi,R.id.tv_qianzheng_order_add, R.id.tv_qianzheng_order_address, R.id.rb_qianzheng_order_xuzhi, R.id.tv_qianzheng_order_pay})
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_qianzheng_order_time:
                STActivity(RiLiActivity.class);
                break;
            case R.id.tv_qianzheng_order_kuaidi:
                if (isEmpty(kuaiDiList)) {
                    showLoading();
                    getKuaiDi(true);
                } else {
                    showKuaiDi(kuaiDiList);
                }
                break;
            case R.id.tv_qianzheng_order_add:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                showLoading();
                getPeople(1,false);
                break;
            case R.id.tv_qianzheng_order_address:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.selectAddress,true);
                STActivityForResult(intent,MyAddressListActivity.class,200);
                break;
            case R.id.rb_qianzheng_order_xuzhi:
                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("签证须知");
                mDialog.setMessage(qianZhengDetailObj.getVisa_information());
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.create().show();
                break;
            case R.id.tv_qianzheng_order_pay:
                break;
        }
    }


    private void getPeople(int page,boolean isLoad,boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("pagesize",pagesize+"");
        map.put("page",page+"");
        map.put("sign",getSign(map));
        ApiRequest.getShenQingRen(map, new MyCallBack<List<ShenQingRenObj>>(mContext,pcfl_refresh) {
            @Override
            public void onSuccess(List<ShenQingRenObj> list) {
                if(isLoad){
                    pageNum++;
                    peopleAdaper.addList(list,true);
                }else{
                    if(isShow){
                        showPeople();
                    }
                    pageNum=2;
                    peopleAdaper.setList(list,true);
                }
            }
        });
    }
    private void getPeople(int page,boolean isLoad) {
        getPeople(page,isLoad,true);
    }

    private void showPeople() {
        Dialog peopleDialog=new Dialog(mContext);
        peopleAdaper = new MyLoadMoreAdapter<ShenQingRenObj>(mContext, R.layout.dingdantianxie_lvke_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, ShenQingRenObj bean) {
                holder.setText(R.id.tv_shenqingren_name,bean.getChinese_name());
                ImageView imageView = holder.getImageView(R.id.iv_shenqingren_edit);
                imageView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.people,bean);
                        STActivityForResult(intent,EditShenQingRenActivity.class,100);
                    }
                });
                CheckBox cb_shenqingren_select = (CheckBox) holder.getView(R.id.cb_shenqingren_select);
                cb_shenqingren_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setCheck(cb_shenqingren_select.isChecked());
                    }
                });
                cb_shenqingren_select.setChecked(bean.isCheck());
            }
        };
        View view = getLayoutInflater().inflate(R.layout.dingdantianxie_add_popu, null);
        pcfl_refresh = view.findViewById(R.id.pcfl_refresh);
        pcfl_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                getPeople(1,false,false);
            }
        });
        TextView tv_add_shengqingren_cancel=view.findViewById(R.id.tv_add_shengqingren_cancel);
        tv_add_shengqingren_cancel.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                peopleDialog.dismiss();
            }
        });
        TextView tv_add_shengqingren_sure=view.findViewById(R.id.tv_add_shengqingren_sure);
        tv_add_shengqingren_sure.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                peopleDialog.dismiss();
                getSelectPeople();
            }
        });
        LinearLayout ll_add_shengqingren_add=view.findViewById(R.id.ll_add_shengqingren_add);
        ll_add_shengqingren_add.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                STActivityForResult(EditShenQingRenActivity.class,100);
            }
        });
        RecyclerView rv_lvke=view.findViewById(R.id.rv_lvke);
        rv_lvke.setAdapter(peopleAdaper);
        peopleAdaper.setOnLoadMoreListener(new MyLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                getPeople(pageNum,true);
            }
        });
        Window win = peopleDialog.getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        win.setGravity(Gravity.BOTTOM);
        win.getDecorView().setPadding(0, 0, 0, 0);
        win.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height=PhoneUtils.getScreenHeight(mContext)*3/5;
        win.setAttributes(lp);
        peopleDialog.setContentView(view);
        peopleDialog.show();
    }

    private void getSelectPeople() {
        List<ShenQingRenObj> list = peopleAdaper.getList();
        boolean flag=false;
        List<ShenQingRenObj>objs=new ArrayList<>();
        if(notEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCheck()){
                    objs.add(list.get(i));
                    flag=true;
                }
            }
        }
        if(flag){
            if(notEmpty(adapter.getList())){
                objs.addAll(adapter.getList());
            }
            adapter.setList(removeDupliById(objs),true);
        }
    }
    private List<ShenQingRenObj> removeDupliById(List<ShenQingRenObj> persons) {
        Set<ShenQingRenObj> personSet = new TreeSet<>((o1, o2) -> o1.getApplicant_information_id().compareTo(o2.getApplicant_information_id()));
        personSet.addAll(persons);
        return new ArrayList<>(personSet);
    }

    private void showPeople2() {
        peopleAdaper = new MyLoadMoreAdapter<ShenQingRenObj>(mContext, R.layout.dingdantianxie_lvke_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, ShenQingRenObj bean) {
                holder.setText(R.id.tv_shenqingren_name,bean.getChinese_name());
                ImageView imageView = holder.getImageView(R.id.iv_shenqingren_edit);
                imageView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.people,bean);
                        STActivityForResult(intent,EditShenQingRenActivity.class,100);
                    }
                });
                CheckBox cb_shenqingren_select = (CheckBox) holder.getView(R.id.cb_shenqingren_select);
                cb_shenqingren_select.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {

                    }
                });
            }
        };
        View view = getLayoutInflater().inflate(R.layout.dingdantianxie_add_popu, null);
        TextView tv_add_shengqingren_cancel=view.findViewById(R.id.tv_add_shengqingren_cancel);
        tv_add_shengqingren_cancel.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                peopleDialog.dismiss();
            }
        });
        TextView tv_add_shengqingren_sure=view.findViewById(R.id.tv_add_shengqingren_sure);
        LinearLayout ll_add_shengqingren_add=view.findViewById(R.id.ll_add_shengqingren_add);
        ll_add_shengqingren_add.setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                STActivityForResult(EditShenQingRenActivity.class,100);
            }
        });
        RecyclerView rv_lvke=view.findViewById(R.id.rv_lvke);
        rv_lvke.setAdapter(peopleAdaper);
        peopleAdaper.setOnLoadMoreListener(new MyLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void loadMore() {
                getPeople(pageNum,true);
            }
        });

        peopleDialog = new BottomSheetDialog(mContext);
        WindowManager.LayoutParams attributes = peopleDialog.getWindow().getAttributes();
        attributes.height= PhoneUtils.getScreenHeight(mContext)*3/5;
        peopleDialog.getWindow().setAttributes(attributes);
        peopleDialog.setCanceledOnTouchOutside(false);
        peopleDialog.setCancelable(false);
        peopleDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK){
                    peopleDialog.dismiss();
                }
                return true;
            }
        });
        peopleDialog.setContentView(view);

        peopleDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    showLoading();
                    getPeople(1,false,false);
                break;
                case 200:
                    MyAddressObj addressObj= (MyAddressObj) data.getSerializableExtra(IntentParam.addressBean);
                    addressId =addressObj.getAddress_id()+"";
                    tv_qianzheng_order_address.setText(addressObj.getShipping_address()+addressObj.getShipping_address_details());
                    break;
            }
        }
    }
}

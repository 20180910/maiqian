package com.sk.maiqian.module.my.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyCheckBox;
import com.library.base.BaseObj;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.request.DeleteAddressBody;
import com.sk.maiqian.module.my.network.response.MyAddressObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyAddressListActivity extends BaseActivity {

    MyLoadMoreAdapter adapter;
    @BindView(R.id.rv_my_address)
    RecyclerView rv_my_address;
    @BindView(R.id.cb_address_all)
    MyCheckBox cb_address_all;
    @BindView(R.id.ll_address_list)
    LinearLayout ll_address_list;

    private boolean isEdit;
    private boolean selectAddress;

    @Override
    protected int getContentView() {
        setAppTitle("我的地址");
        setAppRightTitle("编辑");
        return R.layout.myaddress_list_act;
    }

    @Override
    protected void initView() {
        selectAddress = getIntent().getBooleanExtra(IntentParam.selectAddress, false);

        setBottomViewShow();
        adapter = new MyLoadMoreAdapter<MyAddressObj>(mContext, R.layout.myaddress_item, pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, MyAddressObj bean) {
                bean.setCheck(cb_address_all.isChecked());
                CheckBox cb_address_check = (CheckBox) holder.getView(R.id.cb_address_check);
                holder.setText(R.id.tv_address_name,bean.getRecipient());
                holder.setText(R.id.tv_address_phone,bean.getPhone());
                holder.setText(R.id.tv_address_detail,bean.getShipping_address()+bean.getShipping_address_details());
                if(isEdit){
                    cb_address_check.setVisibility(View.VISIBLE);
                    cb_address_check.setChecked(bean.isCheck());
                }else{
                    cb_address_check.setVisibility(View.GONE);
                }
                cb_address_check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setCheck(cb_address_check.isChecked());

                        cb_address_all.setChecked(isSelectAll());
                    }
                });
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        if(selectAddress){
                            intent.putExtra(IntentParam.addressBean,bean);
                            setResult(RESULT_OK,intent);
                            finish();
                        }else{
                            intent.putExtra(IntentParam.addressBean,bean);
                            intent.putExtra(IntentParam.isEditAddress,true);
                            STActivityForResult(EditAddressActivity.class,100);
                        }
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_address.setLayoutManager(new LinearLayoutManager(mContext));
        rv_my_address.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext, 5)));
        rv_my_address.setAdapter(adapter);


    }

    private boolean isSelectAll() {
        boolean flag=true;
        for (int i = 0; i < adapter.getList().size(); i++) {
            MyAddressObj obj = (MyAddressObj) adapter.getList().get(i);
            if(!obj.isCheck()){
                flag=false;
                break;
            }
        }
        return flag;
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getAddress(map, new MyCallBack<List<MyAddressObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyAddressObj> list, int errorCode, String msg) {
                if(isEmpty(list)){
                    isEdit=false;
                    cb_address_all.setChecked(false);
                }
                setBottomViewShow();
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

    private void setBottomViewShow() {
        if(isEdit){
            setAppRightTitle("完成");
            ll_address_list.setVisibility(View.VISIBLE);
        }else{
            setAppRightTitle("编辑");
            ll_address_list.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.ll_address_add, R.id.cb_address_all, R.id.tv_address_delete,R.id.app_right_tv})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_address_add:
                STActivityForResult(EditAddressActivity.class,100);
                break;
            case R.id.cb_address_all:
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_address_delete:
                List<String>list=new ArrayList<>();
                for (int i = 0; i < adapter.getList().size(); i++) {
                    MyAddressObj obj = (MyAddressObj) adapter.getList().get(i);
                    if(obj.isCheck()){
                        int addressId = obj.getAddress_id();
                        list.add(addressId+"");
                    }
                }
                if(list.size()==0){
                    showMsg("请选择需要删除的地址");
                    return;
                }
                MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                mDialog.setMessage("确认删除地址吗?");
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
                        deleteAddress(list);
                    }
                });
                mDialog.create().show();
                break;
            case R.id.app_right_tv:
                isEdit=!isEdit;
                setBottomViewShow();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void deleteAddress(List<String> list) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        DeleteAddressBody body=new DeleteAddressBody();
        body.setBody(list);
        ApiRequest.deleteAddress(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                getData(1,false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    getData(1,false);
                break;
            }
        }
    }
}

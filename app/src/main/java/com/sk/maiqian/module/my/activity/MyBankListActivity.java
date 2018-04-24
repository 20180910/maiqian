package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyLinearLayout;
import com.library.base.BaseObj;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.MyAllBankObj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class MyBankListActivity extends BaseActivity {
    @BindView(R.id.rv_mybank)
    RecyclerView rv_mybank;
    @BindView(R.id.ll_add_bank)
    MyLinearLayout ll_add_bank;

    MyLoadMoreAdapter adapter;
    private boolean isSelectBank;


    @Override
    protected int getContentView() {
        isSelectBank = getIntent().getBooleanExtra(IntentParam.selectBank, false);


        setAppTitle("我的银行卡");
        setAppRightImg(R.drawable.share);
        return R.layout.mybanklist_act;
    }

    @Override
    protected void initView() {
        pcfl.disableWhenHorizontalMove(true);
        adapter=new MyLoadMoreAdapter<MyAllBankObj>(mContext,R.layout.mybanklist_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, MyAllBankObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_bank);
                GlideUtils.into(mContext,bean.getBank_image(),imageView);
                holder.setText(R.id.tv_bank_name,bean.getBank_name());
                holder.setText(R.id.tv_bank_type,bean.getCard_type());
                holder.setText(R.id.tv_bank_code,bean.getBank_card());
                View view = holder.getView(R.id.tv_bank_delete);
                view.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        deleteBank(bean.getId());
                    }
                });
                if(isSelectBank){
                    holder.getView(R.id.ll_bank_list).setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            setDefaultBank(bean);
                        }
                    });
                }
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_mybank.setLayoutManager(new LinearLayoutManager(mContext));
        rv_mybank.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5),R.color.white));
        rv_mybank.setAdapter(adapter);


    }

    private void setDefaultBank(MyAllBankObj bean) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("account_id",bean.getId());
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.setDefault(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                Intent intent=new Intent();
                intent.putExtra(IntentParam.bank,bean);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    private void deleteBank(String id) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("account_id",id);
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.deleteBank(map, new MyCallBack<BaseObj>(mContext,true) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                getData(1,false);
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
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.getBank(map, new MyCallBack<List<MyAllBankObj>>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(List<MyAllBankObj> list, int errorCode, String msg) {
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

    @OnClick({R.id.ll_add_bank})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.ll_add_bank:
                STActivityForResult(AddBankActivity.class,100);
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

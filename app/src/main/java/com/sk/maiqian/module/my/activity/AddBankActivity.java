package com.sk.maiqian.module.my.activity;

import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.library.base.BaseObj;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.BuildConfig;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.request.AddBankBody;
import com.sk.maiqian.module.my.network.response.BankNameObj;
import com.sk.maiqian.network.NetApiRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AddBankActivity extends BaseActivity {
    @BindView(R.id.et_add_bank_name)
    MyEditText et_add_bank_name;
    @BindView(R.id.et_add_bank_idcard)
    MyEditText et_add_bank_idcard;
    @BindView(R.id.et_add_bank_bankcode)
    MyEditText et_add_bank_bankcode;
    @BindView(R.id.et_add_bank_zhihang)
    MyEditText et_add_bank_zhihang;
    @BindView(R.id.tv_add_bank_bankname)
    TextView tv_add_bank_bankname;
    private String bankId;
    private List<BankNameObj> bankList;

    @Override
    protected int getContentView() {
        setAppTitle("添加银行卡");
        setAppRightImg(R.drawable.share);
        return R.layout.addbank_act;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        getBank(false);
    }

    @OnClick({R.id.tv_add_bank_bankname, R.id.tv_add_bank_save,R.id.tv_add_bank_username})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_bank_username:
                if(BuildConfig.DEBUG){
                    et_add_bank_bankcode.setText("6217880800008464499");
                    et_add_bank_idcard.setText("432322197004134857");
                }
                break;
            case R.id.tv_add_bank_bankname:
                if(isEmpty(bankList)){
                    showLoading();
                    getBank(true);
                }else{
                    showBank(bankList);
                }
                break;
            case R.id.tv_add_bank_save:
                String name=getSStr(et_add_bank_name);
                String idcard=getSStr(et_add_bank_idcard);
                String bankname=getSStr(tv_add_bank_bankname);
                String bankcode=getSStr(et_add_bank_bankcode);
                String zhihang=getSStr(et_add_bank_zhihang);
                if(TextUtils.isEmpty(name)){
                    showMsg("请输入持卡人姓名");
                    return;
                }else if(TextUtils.isEmpty(idcard)){
                    showMsg("请输入身份证号");
                    return;
                }else if(TextUtils.isEmpty(bankId)){
                    showMsg("请选择银行");
                    return;
                }else if(TextUtils.isEmpty(bankcode)){
                    showMsg("请输入银行卡号");
                    return;
                }else if(TextUtils.isEmpty(zhihang)){
                    showMsg("请输入开户行");
                    return;
                }
                AddBankBody bankBody=new AddBankBody();
                bankBody.setRealname(name);
                bankBody.setId_number(idcard);
                bankBody.setBank_card_num(bankcode);
                bankBody.setOpening_bank(zhihang);
                bankBody.setBank_id(bankId);

                addBank(bankBody);
                break;
        }
    }
    public void getBank(boolean isShow){
        Map<String,String> map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        NetApiRequest.getBankList(map, new MyCallBack<List<BankNameObj>>(mContext) {
            @Override
            public void onSuccess(List<BankNameObj> list, int errorCode, String msg) {
                bankList = list;
                if(isShow){
                    showBank(list);
                }
            }
        });
    }

    public void showBank(List<BankNameObj> list){
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        View view = getLayoutInflater().inflate(R.layout.addbank_popu, null);
        MyRecyclerView rv_yinhang = view.findViewById(R.id.rv_yinhang);
        MyBaseRecyclerAdapter adapter=new MyBaseRecyclerAdapter<BankNameObj>(mContext,R.layout.addbank_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, BankNameObj bean) {

                ImageView imageView = holder.getImageView(R.id.iv_yinhang);
                if(TextUtils.isEmpty(bean.getImage_url())){
                    imageView.setVisibility(View.GONE);
                }else{
                    imageView.setVisibility(View.VISIBLE);
                    GlideUtils.into(mContext,bean.getImage_url(),imageView);
                }
                holder.setText(R.id.tv_yinhang_name,bean.getBank_name());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        bankId=bean.getBank_id()+"";
                        tv_add_bank_bankname.setText(bean.getBank_name());
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

    private void addBank(AddBankBody bankBody) {
        showLoading();
        Map<String,String> map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("sign",getSign(map));
        ApiRequest.addBank(map,bankBody,new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}

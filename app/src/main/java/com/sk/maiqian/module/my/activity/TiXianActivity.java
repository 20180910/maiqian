package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.customview.MyImageView;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.DefaultBankObj;
import com.sk.maiqian.module.my.network.response.JiFenObj;
import com.sk.maiqian.module.my.network.response.MyAllBankObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class TiXianActivity extends BaseActivity {
    @BindView(R.id.ll_tixian_selectbank)
    LinearLayout ll_tixian_selectbank;
    @BindView(R.id.iv_tixian)
    MyImageView iv_tixian;
    @BindView(R.id.tv_tixian_bank)
    TextView tv_tixian_bank;
    @BindView(R.id.tv_tixian_bankcode)
    TextView tv_tixian_bankcode;
    @BindView(R.id.ll_tixian_defaultbank)
    LinearLayout ll_tixian_defaultbank;
    @BindView(R.id.et_tixian_jifen)
    EditText et_tixian_jifen;


    private String bankId;

    @Override
    protected int getContentView() {
        setAppTitle("提现");
        return R.layout.tixian_act;
    }

    @Override
    protected void initView() {

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
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.getDefaultAccount(map, new MyCallBack<DefaultBankObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(DefaultBankObj obj, int errorCode, String msg) {
                if (obj.getId() != 0) {
                    bankId = obj.getId()+"";
                    GlideUtils.into(mContext,obj.getBank_image(),iv_tixian);
                    tv_tixian_bank.setText(obj.getBank_name());
                    tv_tixian_bankcode.setText(obj.getBank_card());

                    ll_tixian_selectbank.setVisibility(View.GONE);
                    ll_tixian_defaultbank.setVisibility(View.VISIBLE);
                }else{
                    ll_tixian_selectbank.setVisibility(View.VISIBLE);
                    ll_tixian_defaultbank.setVisibility(View.GONE);
                }
            }
        });

    }

    @OnClick({R.id.ll_tixian_selectbank, R.id.tv_tixian,R.id.ll_tixian_defaultbank})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_tixian_defaultbank:
            case R.id.ll_tixian_selectbank:
//                STActivityForResult(AddBankActivity.class,100);
                Intent intent=new Intent();
                intent.putExtra(IntentParam.selectBank,true);
                STActivityForResult(intent,MyBankListActivity.class,100);
                break;
            case R.id.tv_tixian:
                if(TextUtils.isEmpty(bankId)){
                    showMsg("请选择银行卡");
                    return;
                }
                String jifen=getSStr(et_tixian_jifen);
                if(jifen.indexOf("0")==0){
                    jifen=jifen.substring(1,jifen.length());
                }
                if(TextUtils.isEmpty(jifen)){
                    showMsg("请输入积分");
                    return;
                }else if(jifen.length()==1&&jifen.indexOf("0")==0){
                    showMsg("请输入积分");
                    return;
                }
                tiXian(jifen);
                break;
        }
    }



    private void tiXian(String jifen) {
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("account_id",bankId);
        map.put("point",jifen);
        map.put("sign",getSign(map));
        ApiRequest.tiXian(map, new MyCallBack<JiFenObj>(mContext) {
            @Override
            public void onSuccess(JiFenObj obj, int errorCode, String msg) {
                //需要返回剩余积分
                //添加银行卡无默认
//                showMsg(obj.getMsg());
                SPUtils.setPrefString(mContext, AppXml.jifen,obj.getPoint());
                STActivity(TiXianSuccessActivity.class);
                finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 100:
                    if(data!=null){
                        MyAllBankObj obj = (MyAllBankObj) data.getSerializableExtra(IntentParam.bank);

                        bankId = obj.getId()+"";
                        GlideUtils.into(mContext,obj.getBank_image(),iv_tixian);
                        tv_tixian_bank.setText(obj.getBank_name());
                        tv_tixian_bankcode.setText(obj.getBank_card());

                        ll_tixian_selectbank.setVisibility(View.GONE);
                        ll_tixian_defaultbank.setVisibility(View.VISIBLE);
                    }
                break;
            }
        }
    }
}

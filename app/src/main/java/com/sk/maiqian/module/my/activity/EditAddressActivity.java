package com.sk.maiqian.module.my.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.github.androidtools.PhoneUtils;
import com.github.customview.MyEditText;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.MyAddressObj;
import com.sk.maiqian.network.NetApiRequest;
import com.sk.maiqian.network.response.CityObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EditAddressActivity extends BaseActivity {
    @BindView(R.id.et_address_name)
    MyEditText et_address_name;
    @BindView(R.id.et_address_phone)
    MyEditText et_address_phone;
    @BindView(R.id.et_address_detail)
    MyEditText et_address_detail;
    @BindView(R.id.tv_address_area)
    TextView tv_address_area;
    private List<CityObj> cityList;
    private String first;
    private String second;
    private String third;

    private String addressId;
    private boolean isEditAddress;
    private MyAddressObj addressObj;

    @Override
    protected int getContentView() {
        setAppTitle("编辑地址");
        setAppRightImg(R.drawable.share);
        return R.layout.editaddress_act;
    }

    @Override
    protected void initView() {
//        isEditAddress = getIntent().getBooleanExtra(IntentParam.isEditAddress, false);
        addressObj = (MyAddressObj) getIntent().getSerializableExtra(IntentParam.addressBean);
        if(addressObj!=null){
            isEditAddress=(addressObj ==null?false:true);
            addressId = addressObj.getAddress_id()+"";
        }
        getAllArea(false);

    }

    private void getAllArea(boolean isShow) {
        if (isShow&&notEmpty(options1Items)) {
            showArea();
        } else {
            MyRx.start(new MyFlowableSubscriber<List<CityObj>>() {
                @Override
                public void subscribe(@NonNull FlowableEmitter<List<CityObj>> emitter) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("rnd", getRnd());
                    map.put("sign", getSign(map));
                    NetApiRequest.getAllArea(map, new MyCallBack<List<CityObj>>(mContext) {
                        @Override
                        public void onSuccess(List<CityObj> list, int errorCode, String msg) {
                            cityList = list;
                            emitter.onNext(list);
                            for (int i = 0; i < cityList.size(); i++) {
                                options1Items.add(cityList.get(i).getTitle());

                                List<String> item2 = new ArrayList<>();
                                List<List<String>> item3 = new ArrayList<>();

                                for (int j = 0; j < cityList.get(i).getPca_list().size(); j++) {
                                    item2.add(cityList.get(i).getPca_list().get(j).getTitle());
                                    List<String> stringList = new ArrayList<>();
                                    for (int k = 0; k < cityList.get(i).getPca_list().get(j).getPca_list().size(); k++) {
                                        stringList.add(cityList.get(i).getPca_list().get(j).getPca_list().get(k).getTitle());
                                    }
                                    item3.add(stringList);
                                }
                                options2Items.add(item2);
                                options3Items.add(item3);
                            }
                            emitter.onComplete();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            emitter.onError(e);
                        }
                    });
                }

                @Override
                public void onNext(List<CityObj> list) {
                    if (isShow) {
                        showArea();
                    }
                }
            });
        }
    }

    @Override
    protected void initData() {
        if(isEditAddress){
            et_address_name.setText(addressObj.getRecipient());
            et_address_phone.setText(addressObj.getPhone());
            et_address_detail.setText(addressObj.getShipping_address_details());
            tv_address_area.setText(addressObj.getShipping_address());
        }
    }

    @OnClick({R.id.tv_address_area, R.id.tv_commit_address})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address_area:
                getAllArea(true);
                break;
            case R.id.tv_commit_address:
                if(isEditAddress){
                    first =addressObj.getProvince()+"";
                    second=addressObj.getCity()+"";
                    third =addressObj.getArea()+"";
                }
                String name   = getSStr(et_address_name);
                String phone  = getSStr(et_address_phone);
                String area   = getSStr(tv_address_area);
                String detail = getSStr(et_address_detail);
                if (TextUtils.isEmpty(name)) {
                    showMsg("请输入联系人");
                    return;
                } else if (TextUtils.isEmpty(phone) || ZhengZeUtils.notMobile(phone)) {
                    showMsg("请输入正确手机号");
                    return;
                } else if (TextUtils.isEmpty(area)) {
                    showMsg("请选择地区");
                    return;
                } else if (TextUtils.isEmpty(detail)) {
                    showMsg("请输入详细地址");
                    return;
                }
                addAddress(name, phone, area, detail);
                break;
        }
    }

    private void addAddress(String name, String phone, String area, String detail) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("address_id", isEditAddress?addressId:"0");
        map.put("user_id", getUserId());
        map.put("recipient", name);
        map.put("phone", phone);
        map.put("shipping_address", area);
        map.put("shipping_address_detail", detail);
        map.put("province", first);
        map.put("city", second);
        map.put("area", third);
        map.put("sign", getSign(map));
        ApiRequest.addAddress(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(obj.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void showArea() {

        PhoneUtils.hiddenKeyBoard(mContext);

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {


            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                Log("===" + options1 + "===" + option2 + "===" + options3);
                first = cityList.get(options1).getId() + "";
                second = cityList.get(options1).getPca_list().get(option2).getId() + "";
                third = cityList.get(options1).getPca_list().get(option2).getPca_list().get(options3).getId() + "";

                String firstCity = cityList.get(options1).getTitle();
                String secondCity = cityList.get(options1).getPca_list().get(option2).getTitle();
                String thirdCity = cityList.get(options1).getPca_list().get(option2).getPca_list().get(options3).getTitle();

                tv_address_area.setText(firstCity + secondCity + thirdCity);
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);

        pvOptions.show();

    }

    private List<String> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();
}

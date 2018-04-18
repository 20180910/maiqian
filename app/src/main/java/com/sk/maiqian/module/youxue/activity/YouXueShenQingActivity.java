package com.sk.maiqian.module.youxue.activity;

import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.github.androidtools.ClickUtils;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.SPUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.github.customview.MyEditText;
import com.github.customview.MyLinearLayout;
import com.github.customview.MyTextView;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.library.base.BaseObj;
import com.library.base.tools.ZhengZeUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.module.youxue.network.ApiRequest;
import com.sk.maiqian.module.youxue.network.request.YouXueShenQingBody;
import com.sk.maiqian.module.youxue.network.response.GuoJiaObj;
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
 * Created by Administrator on 2018/3/27.
 */

public class YouXueShenQingActivity extends BaseActivity {
    @BindView(R.id.tv_youxue_shenqing_guojia)
    TextView tv_youxue_shenqing_guojia;
    @BindView(R.id.et_youxue_shenqing)
    MyEditText et_youxue_shenqing;
    @BindView(R.id.tv_youxue_shenqing_city)
    TextView tv_youxue_shenqing_city;
    @BindView(R.id.ll_youxue_shenqing_zixun)
    MyLinearLayout ll_youxue_shenqing_zixun;
    @BindView(R.id.tv_youxue_shenqing_commit)
    MyTextView tv_youxue_shenqing_commit;

    private List<GuoJiaObj> guoJiaObjList;

    private List<CityObj> cityObjList;


    private List<String> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private List<List<List<String>>> options3Items = new ArrayList<>();
    @Override
    protected int getContentView() {
        setAppTitle("游学申请");
        setAppRightImg(R.drawable.share);
        return R.layout.youxueshenqing_act;
    }

    @Override
    protected void initView() {
        String phone = SPUtils.getString(mContext, AppXml.mobile, null);
        et_youxue_shenqing.setText(phone);
    }

    @Override
    protected void initData() {
        getAllArea(false);
        getGuoJia(false);
        getCity(false);
    }

    private void getCity(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("search_term","");
        map.put("sign",getSign(map));
        NetApiRequest.getAllCity(map, new MyCallBack<List<CityObj>>(mContext) {
            @Override
            public void onSuccess(List<CityObj> list) {
                cityObjList = list;
                if(isShow){
                    showCity(list);
                }
            }
        });

    }

    private void getGuoJia(boolean isShow) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("rnd",getRnd());
        map.put("sign",getSign(map));
        ApiRequest.getGuoJia(map, new MyCallBack<List<GuoJiaObj>>(mContext) {
            @Override
            public void onSuccess(List<GuoJiaObj> list) {
                guoJiaObjList=list;
                if(isShow){
                    showGuoJia(guoJiaObjList);
                }
            }
        });
    }
    @OnClick({R.id.tv_youxue_shenqing_guojia, R.id.tv_youxue_shenqing_city, R.id.ll_youxue_shenqing_zixun, R.id.tv_youxue_shenqing_commit})
    public void onViewClick(View view) {
        if(ClickUtils.isFastClick(view)){
            return;
        }
        switch (view.getId()) {
            case R.id.tv_youxue_shenqing_guojia:
                if(isEmpty(guoJiaObjList)){
                    showLoading();
                    getGuoJia(true);
                }else{
                    showGuoJia(guoJiaObjList);
                }
                break;
            case R.id.tv_youxue_shenqing_city:
                /*if(isEmpty(cityObjList)){
                    showLoading();
                    getCity(true);
                }else{
                    showCity(cityObjList);
                }*/
                getAllArea(true);
                break;
            case R.id.ll_youxue_shenqing_zixun:
                showZiXunDialog();
                break;
            case R.id.tv_youxue_shenqing_commit:
                if(noLogin()){
                    STActivity(LoginActivity.class);
                    return;
                }
                String guojia = getSStr(tv_youxue_shenqing_guojia);
                String phone=getSStr(et_youxue_shenqing);
                String city=getSStr(tv_youxue_shenqing_city);
                if(TextUtils.isEmpty(guojia)){
                    showMsg("请选择目的地");
                    return;
                }else if(TextUtils.isEmpty(phone)|| ZhengZeUtils.notMobile(phone)){
                    showMsg("请输入正确手机号");
                    return;
                }else if(TextUtils.isEmpty(city)){
                    showMsg("请选择城市");
                    return;
                }
                shenQing(guojia,
                        phone,
                        city);
                break;
        }
    }
    private void shenQing(String guojia, String phone, String city) {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", "1");
        map.put("user_id",getUserId());
        map.put("sign", getSign(map));
        YouXueShenQingBody body = new YouXueShenQingBody();
        body.setDestination(guojia);
        body.setPhone(phone);
        body.setCity_gradeschool(city);
        ApiRequest.youXueShenQing(map, body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                showMsg(obj.getMsg());
                finish();
            }
        });
    }
    private void showGuoJia(List<GuoJiaObj> list) {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyRecyclerView rv_youxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<GuoJiaObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, GuoJiaObj bean) {
                holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        dialog.dismiss();
                        tv_youxue_shenqing_guojia.setText(bean.getTitle());
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
    }
    private void showCity(List<CityObj> list) {
        BottomSheetDialog dialog=new BottomSheetDialog(mContext);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = getLayoutInflater().inflate(R.layout.youxue_popu, null);
        MyRecyclerView rv_youxue_guojia = view.findViewById(R.id.rv_youxue_guojia);
        BaseRecyclerAdapter adapter=new BaseRecyclerAdapter<CityObj>(mContext,R.layout.youxue_popu_item) {
            @Override
            public void bindData(RecyclerViewHolder holder, int position, CityObj bean) {
                holder.setText(R.id.tv_youxue_popu_guojia,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        dialog.dismiss();
                        tv_youxue_shenqing_city.setText(bean.getTitle());
                    }
                });
            }
        };
        adapter.setList(list);
        rv_youxue_guojia.setAdapter(adapter);
        dialog.setContentView(view);
        dialog.show();
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
                        public void onSuccess(List<CityObj> list) {
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

    private List<CityObj> cityList;
    private void showArea() {

        PhoneUtils.hiddenKeyBoard(mContext);

        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {


            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                Log("===" + options1 + "===" + option2 + "===" + options3);
//                first = cityList.get(options1).getId() + "";
//                second = cityList.get(options1).getPca_list().get(option2).getId() + "";
//                third = cityList.get(options1).getPca_list().get(option2).getPca_list().get(options3).getId() + "";

                String firstCity = cityList.get(options1).getTitle();
                String secondCity = cityList.get(options1).getPca_list().get(option2).getTitle();
                String thirdCity = cityList.get(options1).getPca_list().get(option2).getPca_list().get(options3).getTitle();

                tv_youxue_shenqing_city.setText( secondCity );
            }
        }).build();
        pvOptions.setPicker(options1Items, options2Items, null);

        pvOptions.show();

    }
}

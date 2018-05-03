package com.sk.maiqian.module.home.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.view.MyDialog;
import com.github.customview.MyTextView;
import com.library.base.view.MyRecyclerView;
import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.CollectObj;
import com.sk.maiqian.module.home.network.response.QianZhengDetailObj;
import com.sk.maiqian.module.home.network.response.ZiXunObj;
import com.sk.maiqian.module.my.activity.LoginActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/22.
 */

public class QianZhengDetailActivity extends BaseActivity {

    @BindView(R.id.tv_qianzheng_detail_title)
    TextView tv_qianzheng_detail_title;
    @BindView(R.id.tv_qianzheng_detail_price)
    TextView tv_qianzheng_detail_price;
    @BindView(R.id.tv_qianzheng_detail_mx)
    MyTextView tv_qianzheng_detail_mx;
    @BindView(R.id.tv_qianzheng_detail_shichang)
    TextView tv_qianzheng_detail_shichang;
    @BindView(R.id.tv_qianzheng_detail_youxiaoqi)
    TextView tv_qianzheng_detail_youxiaoqi;
    @BindView(R.id.tv_qianzheng_detail_youxiaoqi_title)
    TextView tv_qianzheng_detail_youxiaoqi_title;
    @BindView(R.id.tv_qianzheng_detail_tian)
    TextView tv_qianzheng_detail_tian;
    @BindView(R.id.tv_qianzheng_detail_tian_title)
    TextView tv_qianzheng_detail_tian_title;
    @BindView(R.id.tv_qianzheng_detail_ci)
    TextView tv_qianzheng_detail_ci;
    @BindView(R.id.tv_qianzheng_detail_ci_title)
    TextView tv_qianzheng_detail_ci_title;
    @BindView(R.id.tv_qianzheng_detail_chuqianlv)
    TextView tv_qianzheng_detail_chuqianlv;
    @BindView(R.id.tv_qianzheng_detail_service)
    TextView tv_qianzheng_detail_service;
    @BindView(R.id.tv_qianzheng_detail_shouli)
    TextView tv_qianzheng_detail_shouli;
    @BindView(R.id.tab_qianzheng_detail)
    TabLayout tab_qianzheng_detail;
    @BindView(R.id.tv_qianzheng_detail_need1)
    TextView tv_qianzheng_detail_need1;
    @BindView(R.id.tv_qianzheng_detail_need2)
    TextView tv_qianzheng_detail_need2;
    @BindView(R.id.tv_qianzheng_detail_need3)
    TextView tv_qianzheng_detail_need3;
    @BindView(R.id.tv_qianzheng_detail_need4)
    TextView tv_qianzheng_detail_need4;
    @BindView(R.id.tv_qianzheng_detail_need5)
    TextView tv_qianzheng_detail_need5;
    @BindView(R.id.rv_qianzheng_detail)
    MyRecyclerView rv_qianzheng_detail;
    @BindView(R.id.re_qianzheng_detail)
    RichEditor re_qianzheng_detail;
    @BindView(R.id.tv_qianzheng_detail_zixun)
    TextView tv_qianzheng_detail_zixun;
    @BindView(R.id.tv_qianzheng_detail_collect)
    TextView tv_qianzheng_detail_collect;
    @BindView(R.id.tv_qianzheng_detail_yuding)
    TextView tv_qianzheng_detail_yuding;

    @BindView(R.id.ll_qianzheng_detail_top)
    LinearLayout ll_qianzheng_detail_top;

    @BindView(R.id.cv_qianzhengdetail)
    CardView cv_qianzhengdetail;

    /*LinearLayout
ll_qianzheng_detail_top
CardView
cv_qianzhengdetail*/

    private String visaId;
    private String mingXi;
    private QianZhengDetailObj qianZhengDetailObj;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
        return R.layout.qianzhengdetail_act;
    }

    @Override
    protected void initView() {
        visaId = getIntent().getStringExtra(IntentParam.visaId);
        String title = getIntent().getStringExtra(IntentParam.title);
        setAppTitle(title + "签证");

    }

    @Override
    protected void initData() {
        showProgress();
        getZiXunData(new MyCallBack<ZiXunObj>(mContext) {
            @Override
            public void onSuccess(ZiXunObj obj, int errorCode, String msg) {
                ziXunObj = obj;
            }
        });
        getData(1, false);
    }



    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("visa_id", visaId);
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        ApiRequest.qianZhengDetail(map, new MyCallBack<QianZhengDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(QianZhengDetailObj obj, int errorCode, String msg) {
                qianZhengDetailObj = obj;
                setData(obj);
            }
        });
    }

    private void setData(QianZhengDetailObj obj) {
        tv_qianzheng_detail_title.setText(obj.getTitle());
        tv_qianzheng_detail_price.setText("¥" + obj.getPrice());
        tv_qianzheng_detail_shichang.setText(obj.getFor_how_long());

        if (notEmpty(obj.getCanshu_list())) {
            List<QianZhengDetailObj.CanshuListBean> list = obj.getCanshu_list();
            if (list.size() >= 1) {
                tv_qianzheng_detail_youxiaoqi.setText(list.get(0).getContent());
                tv_qianzheng_detail_youxiaoqi_title.setText(list.get(0).getTitle());
            }
            if (list.size() >= 2) {
                tv_qianzheng_detail_tian.setText(list.get(1).getContent());
                tv_qianzheng_detail_tian_title.setText(list.get(1).getTitle());
            }
            if (list.size() >= 3) {
                tv_qianzheng_detail_ci.setText(list.get(2).getContent());
                tv_qianzheng_detail_ci_title.setText(list.get(2).getTitle());
            }
        }
        tv_qianzheng_detail_chuqianlv.setText("出签率" + obj.getRate_signing() + "%");

        tv_qianzheng_detail_service.setText("服务内容: " + obj.getService_content());
        tv_qianzheng_detail_shouli.setText("受理范围: " + obj.getAccept_range());


        tv_qianzheng_detail_need1.setText("需要" + obj.getZaizhirenyuan() + "项材料");
        tv_qianzheng_detail_need2.setText("需要" + obj.getZiyouzhiyezhe() + "项材料");
        tv_qianzheng_detail_need3.setText("需要" + obj.getTuixiurenyuan() + "项材料");
        tv_qianzheng_detail_need4.setText("需要" + obj.getZaixiaoxuesheng() + "项材料");
        tv_qianzheng_detail_need5.setText("需要" + obj.getXuelingqianertong() + "项材料");

        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<QianZhengDetailObj.LiuchengListBean>(mContext, R.layout.qianzhengdetail_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, QianZhengDetailObj.LiuchengListBean bean) {
                int num = position + 1;
                holder.setText(R.id.tv_qianzheng_detail_item_num, num + "");
                if (position == getItemCount() - 1) {
                    holder.getView(R.id.tv_qianzheng_detail_item_line).setVisibility(View.INVISIBLE);
                } else {
                    holder.getView(R.id.tv_qianzheng_detail_item_line).setVisibility(View.VISIBLE);
                }
                holder.setText(R.id.tv_qianzheng_detail_item_title, bean.getTitle());
                holder.setText(R.id.tv_qianzheng_detail_item_content, bean.getContent());
            }
        };
        adapter.setList(obj.getLiucheng_list());
        rv_qianzheng_detail.setNestedScrollingEnabled(false);
        rv_qianzheng_detail.setAdapter(adapter);

        re_qianzheng_detail.setInputEnabled(false);
        re_qianzheng_detail.setHtml(obj.getVisa_instructions());
        re_qianzheng_detail.setPadding(0, PhoneUtils.dip2px(mContext, 5), PhoneUtils.dip2px(mContext, 13), PhoneUtils.dip2px(mContext, 5));
        re_qianzheng_detail.setEditorFontColor(ContextCompat.getColor(mContext, R.color.gray_66));
        re_qianzheng_detail.setEditorFontSize(13);

        mingXi = obj.getPrice_detail();
        if (obj.getIs_collect() == 1) {
            tv_qianzheng_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
        } else {
            tv_qianzheng_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
        }

    }


    @OnClick({R.id.tv_qianzheng_detail_need1,R.id.tv_qianzheng_detail_need2,R.id.tv_qianzheng_detail_need3,R.id.tv_qianzheng_detail_need4,
            R.id.tv_qianzheng_detail_need5,
            R.id.tv_qianzheng_detail_mx, R.id.tv_qianzheng_detail_zixun, R.id.tv_qianzheng_detail_collect, R.id.tv_qianzheng_detail_yuding})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_qianzheng_detail_need1:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id,visaId);
                intent.putExtra(IntentParam.type,"1");
                STActivity(intent,ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need2:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id,visaId);
                intent.putExtra(IntentParam.type,"2");
                STActivity(intent,ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need3:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id,visaId);
                intent.putExtra(IntentParam.type,"3");
                STActivity(intent,ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need4:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id,visaId);
                intent.putExtra(IntentParam.type,"4");
                STActivity(intent,ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need5:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id,visaId);
                intent.putExtra(IntentParam.type,"5");
                STActivity(intent,ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_mx:
                MyDialog.Builder mDialog = new MyDialog.Builder(mContext);
                mDialog.setTitle("明细");
                mDialog.setMessage(mingXi);
                mDialog.setPositiveButton(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mDialog.create().show();
                break;
            case R.id.tv_qianzheng_detail_zixun:
                showZiXunDialog();
                break;
            case R.id.tv_qianzheng_detail_collect:
                if (noLogin()) {
                    STActivity(LoginActivity.class);
                    return;
                }
                collect();
                break;
            case R.id.tv_qianzheng_detail_yuding:
                intent=new Intent();
                intent.putExtra(IntentParam.qianZhengObj,qianZhengDetailObj);
                intent.putExtra(IntentParam.visaId,visaId);
                STActivity(intent,DingDanTianXieActivity.class);
                break;
        }
    }







    private void collect() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("type", "1");
        map.put("all_id", visaId);
        map.put("sign", getSign(map));
        ApiRequest.collect(map, new MyCallBack<CollectObj>(mContext) {
            @Override
            public void onSuccess(CollectObj obj, int errorCode, String msg) {
                showMsg(msg);
                if (obj.getIs_collect() == 1) {
                    tv_qianzheng_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal2, 0, 0);
                } else {
                    tv_qianzheng_detail_collect.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.collect_normal, 0, 0);
                }
            }
        });

    }
}

package com.sk.maiqian.module.home.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.baseclass.permission.PermissionCallback;
import com.github.baseclass.view.MyDialog;
import com.github.baseclass.view.MyPopupwindow;
import com.github.customview.MyTextView;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.library.base.view.MyRecyclerView;
import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.bean.AppInfo;
import com.sk.maiqian.broadcast.DownloadBro;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.CollectObj;
import com.sk.maiqian.module.home.network.response.QianZhengDetailObj;
import com.sk.maiqian.module.home.network.response.ZiXunObj;
import com.sk.maiqian.module.my.activity.LoginActivity;
import com.sk.maiqian.service.MyAPPDownloadService;
import com.sk.maiqian.tools.FileUtils;
import com.sk.maiqian.tools.TablayoutUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.FlowableEmitter;

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

    @BindView(R.id.ll_qianzheng_detail_sxcl)
    LinearLayout ll_qianzheng_detail_sxcl;

    @BindView(R.id.ll_qianzheng_detail_bllc)
    LinearLayout ll_qianzheng_detail_bllc;

    @BindView(R.id.ll_qianzheng_detail_bqxz)
    LinearLayout ll_qianzheng_detail_bqxz;

    @BindView(R.id.cv_qianzhengdetail)
    CardView cv_qianzhengdetail;

    /*LinearLayout
ll_qianzheng_detail_top
CardView
cv_qianzhengdetail*/

    private String visaId;
    private String mingXi;
    private QianZhengDetailObj qianZhengDetailObj;
    private TextView sxcl;
    private TextView bllc;
    private TextView yjzl;
    private String wordDocumentUrl;

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
        setTabView();
    }

    private void setTabView() {
        TablayoutUtils.setTabWidth(tab_qianzheng_detail, 10);

        /**/
        List<View> list = new ArrayList<>();
        list.add(ll_qianzheng_detail_sxcl);
        list.add(ll_qianzheng_detail_bllc);
        list.add(ll_qianzheng_detail_bqxz);
        scrollCheckViewIsShow(nsv, list, new OnScrollAutoSelectViewInter() {
            @Override
            public void selectViewPosition(int position, View view) {
                tab_qianzheng_detail.getTabAt(position).select();
                switch (position) {
                    case 0:
                        sxcl.setSelected(true);
                        bllc.setSelected(false);
                        yjzl.setSelected(false);
                        break;
                    case 1:
                        sxcl.setSelected(false);
                        bllc.setSelected(true);
                        yjzl.setSelected(false);
                        break;
                    case 2:
                        sxcl.setSelected(false);
                        bllc.setSelected(false);
                        yjzl.setSelected(true);
                        break;
                }
            }
        });

        sxcl = new TextView(mContext);
        bllc = new TextView(mContext);
        yjzl = new TextView(mContext);

        sxcl.setGravity(Gravity.CENTER);
        int[] colors = new int[]{ContextCompat.getColor(mContext, R.color.blue_00), ContextCompat.getColor(mContext, R.color.gray_66)};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{};
        ColorStateList stateList = new ColorStateList(states, colors);
        sxcl.setTextColor(stateList);
        sxcl.setText("所需材料");
        sxcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sxcl.setSelected(true);
                bllc.setSelected(false);
                yjzl.setSelected(false);
                scrollAutoSelectView(nsv, ll_qianzheng_detail_sxcl);
            }
        });
        tab_qianzheng_detail.addTab(tab_qianzheng_detail.newTab().setCustomView(sxcl));

        bllc.setGravity(Gravity.CENTER);
        bllc.setTextColor(stateList);
        bllc.setText("办理流程");
        bllc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sxcl.setSelected(false);
                bllc.setSelected(true);
                yjzl.setSelected(false);
                scrollAutoSelectView(nsv, ll_qianzheng_detail_bllc);
            }
        });
        tab_qianzheng_detail.addTab(tab_qianzheng_detail.newTab().setCustomView(bllc));

        yjzl.setGravity(Gravity.CENTER);
        yjzl.setTextColor(stateList);
        yjzl.setText("办签须知");
        yjzl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sxcl.setSelected(false);
                bllc.setSelected(false);
                yjzl.setSelected(true);
                scrollAutoSelectView(nsv, ll_qianzheng_detail_bqxz);
            }
        });
        tab_qianzheng_detail.addTab(tab_qianzheng_detail.newTab().setCustomView(yjzl));
    }

    @Override
    protected void initData() {
        showProgress();
        getData(1, false);
    }

    public void getZiXun(String id) {
        getZiXunData(id, new MyCallBack<ZiXunObj>(mContext) {
            @Override
            public void onSuccess(ZiXunObj obj, int errorCode, String msg) {
                ziXunObj = obj;
            }
        });
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
                getZiXun(obj.getCountrie_region_id());
                qianZhengDetailObj = obj;
                setData(obj);
            }
        });
    }

    private void setData(QianZhengDetailObj obj) {
        wordDocumentUrl = obj.getWord_document();
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


    @OnClick({R.id.tv_qianzheng_detail_doc, R.id.tv_qianzheng_detail_need1, R.id.tv_qianzheng_detail_need2, R.id.tv_qianzheng_detail_need3, R.id.tv_qianzheng_detail_need4,
            R.id.tv_qianzheng_detail_need5,
            R.id.tv_qianzheng_detail_mx, R.id.tv_qianzheng_detail_zixun, R.id.tv_qianzheng_detail_collect, R.id.tv_qianzheng_detail_yuding})
    public void onViewClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_qianzheng_detail_doc:
                if(TextUtils.isEmpty(wordDocumentUrl)){
                    MyDialog.Builder mDialog=new MyDialog.Builder(mContext);
                    mDialog.setMessage("该签证暂无所需资料文档");
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
                        }
                    });
                    mDialog.create().show();
                }else{
                    if(fileIsExists(getDocFile().getAbsolutePath()+"/"+getDocName())){
                        showPopu();
                    }else{
                        downloadFile();
                    }
                }

//                STActivity(DocActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need1:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id, visaId);
                intent.putExtra(IntentParam.type, "1");
                intent.putExtra(IntentParam.title, "在职人员");
                STActivity(intent, ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need2:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id, visaId);
                intent.putExtra(IntentParam.type, "2");
                intent.putExtra(IntentParam.title, "自由职业者");
                STActivity(intent, ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need3:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id, visaId);
                intent.putExtra(IntentParam.type, "3");
                intent.putExtra(IntentParam.title, "退休人员");
                STActivity(intent, ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need4:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id, visaId);
                intent.putExtra(IntentParam.type, "4");
                intent.putExtra(IntentParam.title, "在校学生");
                STActivity(intent, ZaiZhiRenYuanActivity.class);
                break;
            case R.id.tv_qianzheng_detail_need5:
                intent = new Intent();
                intent.putExtra(IntentParam.visa_id, visaId);
                intent.putExtra(IntentParam.type, "5");
                intent.putExtra(IntentParam.title, "学龄前儿童");
                STActivity(intent, ZaiZhiRenYuanActivity.class);
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
                showZiXunDialog(qianZhengDetailObj.getCountrie_region_id());
                break;
            case R.id.tv_qianzheng_detail_collect:
                if (noLogin()) {
                    STActivity(LoginActivity.class);
                    return;
                }
                collect();
                break;
            case R.id.tv_qianzheng_detail_yuding:
                intent = new Intent();
                intent.putExtra(IntentParam.qianZhengObj, qianZhengDetailObj);
                intent.putExtra(IntentParam.visaId, visaId);
                STActivity(intent, DingDanTianXieActivity.class);
                break;
        }
    }
    private File getDocFile(){
        File file = new File(Environment.getExternalStorageDirectory(), "maiqiandoc");
        return file;
    }
    private String getDocName(){
        if(TextUtils.isEmpty(wordDocumentUrl)){
           return "";
        }
        String fileName=wordDocumentUrl.substring(wordDocumentUrl.lastIndexOf("/")+1, wordDocumentUrl.length());
        return fileName;
    }
    MyPopupwindow popupwindow;
    private void showPopu() {
        View view = getLayoutInflater().inflate(R.layout.lookdoc_popu, null);
        view.findViewById(R.id.tv_doc_cancel).setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                popupwindow.dismiss();
            }
        });
        view.findViewById(R.id.tv_doc_download).setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                popupwindow.dismiss();
                downloadFile();
            }
        });
        view.findViewById(R.id.tv_doc_open).setOnClickListener(new MyOnClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                popupwindow.dismiss();
                DownloadBro.openDoc(mContext,getDocFile().getAbsolutePath()+"/"+getDocName());
            }
        });
        popupwindow=new MyPopupwindow(mContext,view);
        popupwindow.setElevat(PhoneUtils.dip2px(mContext,10));
        popupwindow.showAtLocation(tv_qianzheng_detail_title,Gravity.CENTER,0,0);
    }

    private void downloadFile() {
        requestPermission(new String[]{Manifest.permission_group.STORAGE}, new PermissionCallback() {
            @Override
            public void onGranted() {
                MyRx.start(new MyFlowableSubscriber<Boolean>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull FlowableEmitter<Boolean> subscriber) {
                        boolean delete = FileUtils.delete(getDocFile()+"/"+getDocName());
                        if(!getDocFile().exists()){
                            getDocFile().mkdirs();
                        }
                        subscriber.onNext(delete);
                        subscriber.onComplete();
                    }
                    @Override
                    public void onNext(Boolean aBoolean) {
                        AppInfo info = new AppInfo();
                        info.setNoApk(true);
                        info.setUrl(wordDocumentUrl);
                        info.setFileName(getDocName().split("\\.")[0]);
                        info.setHouZhui(getDocName().split("\\.")[1]);
                        info.setId(wordDocumentUrl);
                        MyAPPDownloadService.intentDownload(mContext, info);
                    }
                });
            }
            @Override
            public void onDenied(String s) {
                showMsg("获取权限失败,无法正常更新,请在设置中打开相关权限");
            }
        });

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

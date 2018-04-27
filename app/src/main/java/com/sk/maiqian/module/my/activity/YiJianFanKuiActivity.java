package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyBaseRecyclerAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyEditText;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.library.base.BaseObj;
import com.library.base.tools.has.BitmapUtils;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.request.FanKuiBody;
import com.sk.maiqian.module.my.network.response.FanKuiTypeObj;
import com.sk.maiqian.network.NetApiRequest;
import com.sk.maiqian.network.request.UploadImgBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2018/3/29.
 */

public class YiJianFanKuiActivity extends BaseActivity {
    List<FanKuiTypeObj> typeList;
    @BindView(R.id.et_fankui_content)
    MyEditText et_fankui_content;

    @BindView(R.id.rv_fankui_add_img)
    RecyclerView rv_fankui_add_img;

    MyBaseRecyclerAdapter imgAdapter;

    @BindView(R.id.tv_fankui_type)
    TextView tv_fankui_type;

    @BindView(R.id.tv_fankui_shuoming)
    TextView tv_fankui_shuoming;

    @BindView(R.id.et_fankui_phone)
    EditText et_fankui_phone;
    private BottomSheetDialog sheetDialog;

    @Override
    protected int getContentView() {
        setAppTitle("意见反馈");
        setAppRightImg(R.drawable.share);
        return R.layout.yijianfankui_act;
    }

    @Override
    protected void initView() {
        getType(false);


        imgAdapter=new MyBaseRecyclerAdapter<String>(mContext,R.layout.yijianfankui_img_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {
                View view = holder.getView(R.id.fl_fankui);
                ImageView addImg = holder.getImageView(R.id.iv_fankui_add);
                if(getItemViewType(position)==1){
                    view.setVisibility(View.GONE);
                    addImg.setVisibility(View.VISIBLE);

                    addImg.setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            addImg();
                        }
                    });
                }else{
                    view.setVisibility(View.VISIBLE);
                    addImg.setVisibility(View.GONE);

                    ImageView imageView = holder.getImageView(R.id.iv_fankui_img);
                    GlideUtils.into(mContext,bean,imageView);
                    holder.getImageView(R.id.iv_fankui_delete).setOnClickListener(new MyOnClickListener() {
                        @Override
                        protected void onNoDoubleClick(View view) {
                            mList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public int getItemViewType(int position) {
                if(mList==null||(position==mList.size()&&mList.size()<3)){
                    return 1;
                }else{
                    return 0;
                }
            }

            @Override
            public int getItemCount() {
                if(isEmpty(mList)){
                    tv_fankui_shuoming.setVisibility(View.VISIBLE);
                }else{
                    tv_fankui_shuoming.setVisibility(View.GONE);
                }
                if(mList!=null&&mList.size()>=3){
                    return mList.size();
                }else{
                    return mList==null?1:mList.size()+1;
                }
            }
        };
        imgAdapter.setList(new ArrayList());
        rv_fankui_add_img.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        rv_fankui_add_img.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext,5),R.color.white));
        rv_fankui_add_img.setAdapter(imgAdapter);

    }

    private void addImg() {
        showSelectPhotoDialog();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case result_take_photo:
                    uploadImg(takePhotoImgSavePath);
                break;
                case result_select_photo:
                    String photoPath = getSelectPhotoPath(data);
                    uploadImg(photoPath);
                    break;
            }
        }
    }

    private void uploadImg(String takePhotoImgSavePath) {
        showLoading();
        RXStart(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> subscriber) {
                try {
                    List<File> files = Luban.with(mContext).load(takePhotoImgSavePath).get();
                    File file = files.get(0);
                    String imgStr = BitmapUtils.fileToString(file);
                    subscriber.onNext(imgStr);
                    subscriber.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onNext(String base64) {
                Map<String,String>map=new HashMap<String,String>();
                map.put("rnd",getRnd());
                map.put("sign",getSign(map));
                UploadImgBody body=new UploadImgBody();
                body.setFile(base64);
                NetApiRequest.uploadImg(map,body, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj, int errorCode, String msg) {
                        imgAdapter.getList().add(obj.getImg());
                        imgAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                dismissLoading();
                showToastS("图片插入失败");
            }
        });

    }

    private void getType(boolean isShow) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
        ApiRequest.getFanKuiType(map, new MyCallBack<List<FanKuiTypeObj>>(mContext) {
            @Override
            public void onSuccess(List<FanKuiTypeObj> list, int errorCode, String msg) {
                typeList = list;
                if(isShow){
                    showType();
                }
            }
        });

    }

    private void showType() {
        sheetDialog = new BottomSheetDialog(mContext);
        sheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = inflateView(R.layout.yijianfankui_popu, null);
        MyRecyclerView rv_fankui_type = (MyRecyclerView) view.findViewById(R.id.rv_fankui_type);
        MyBaseRecyclerAdapter adapter=new MyBaseRecyclerAdapter<FanKuiTypeObj>(mContext,R.layout.yijianfankui_type_item) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, FanKuiTypeObj bean) {
                holder.setText(R.id.tv_fankui_type_content,bean.getTitle());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        sheetDialog.dismiss();
                        tv_fankui_type.setText(bean.getTitle());
                    }
                });
            }
        };
        adapter.setList(typeList);
        rv_fankui_type.setAdapter(adapter);

        sheetDialog.setContentView(view);
        sheetDialog.show();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_commit_fankui,R.id.tv_fankui_type})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_commit_fankui:
                String content = getSStr(et_fankui_content);
                if(TextUtils.isEmpty(content)){
                    showMsg("请输入内容");
                    return;
                }
                fankui(content);
            break;
            case R.id.tv_fankui_type:
                if(isEmpty(typeList)){
                    showLoading();
                    getType(true);
                }else{
                    showType();
                }
            break;
        }
    }

    private void fankui(String content) {
        String phone = getSStr(et_fankui_phone);
        String type = getSStr(tv_fankui_type);
        showLoading();
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("content",content);
        map.put("feedback_type",type);
        map.put("mobile",phone);
        map.put("sign",getSign(map));
        List<FanKuiBody.BodyBean>list=new ArrayList<>();
        FanKuiBody.BodyBean bean;
        if(notEmpty(imgAdapter.getList())){
            for (int i = 0; i <imgAdapter.getList().size(); i++) {
                bean = new FanKuiBody.BodyBean();
                bean.setImg_url((String) imgAdapter.getList().get(i));
                list.add(bean);
            }
        }
        FanKuiBody body=new FanKuiBody();
        body.setBody(list);
        ApiRequest.fanKui(map,body, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj, int errorCode, String msg) {
                showMsg(msg);
                finish();
            }
        });

    }

}

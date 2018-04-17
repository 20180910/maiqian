package com.sk.maiqian.module.yingyupeixun.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.customview.MyImageView;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.yingyupeixun.network.ApiRequest;
import com.sk.maiqian.module.yingyupeixun.network.response.TeacherObj;
import com.sk.maiqian.tools.TextViewUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/3/26.
 */

public class LaoShiJieShaoActivity extends BaseActivity {
    @BindView(R.id.ll_teacher_bg)
    LinearLayout ll_teacher_bg;
    @BindView(R.id.rv_zhuyao_shouke)
    RecyclerView rv_zhuyao_shouke;
    MyLoadMoreAdapter adapter;
    @BindView(R.id.iv_teacher_img)
    MyImageView iv_teacher_img;
    @BindView(R.id.tv_teacher_name)
    TextView tv_teacher_name;
    @BindView(R.id.tv_teacher_age)
    TextView tv_teacher_age;
    @BindView(R.id.tv_teacher_fangxiang)
    TextView tv_teacher_fangxiang;
    @BindView(R.id.tv_teacher_jiguan)
    TextView tv_teacher_jiguan;
    @BindView(R.id.tv_teacher_jieshao)
    TextView tv_teacher_jieshao;
    @BindView(R.id.iv_teacher_kecheng)
    ImageView iv_teacher_kecheng;
    @BindView(R.id.tv_teacher_title)
    TextView tv_teacher_title;
    @BindView(R.id.tv_teacher_flag)
    TextView tv_teacher_flag;
    private String teacherId;

    @Override
    protected int getContentView() {
        setAppTitle("老师介绍");
        setAppRightImg(R.drawable.share);
        return R.layout.laoshijieshao_act;
    }

    @Override
    protected void initView() {
        teacherId = getIntent().getStringExtra(IntentParam.teacherId);

        adapter = new MyLoadMoreAdapter<TeacherObj.ShoukeListBean>(mContext, R.layout.laoshijieshao_item, pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, TeacherObj.ShoukeListBean bean) {
                ImageView imageView = holder.getImageView(R.id.iv_teacher_item_img);
                GlideUtils.into(mContext,bean.getImg_url(),imageView);
                holder.setText(R.id.tv_teacher_item_title,bean.getTitle());
                holder.setText(R.id.tv_teacher_item_price,"¥"+bean.getPrice());
                holder.setText(R.id.tv_teacher_item_old_price,"¥"+bean.getOriginal_price());
                TextViewUtils.underline(holder.getTextView(R.id.tv_teacher_item_old_price));
                holder.setText(R.id.tv_teacher_item_title_flag,bean.getBiaoqian());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.kechengId,bean.getEnglish_training_id());
                        intent.putExtra(IntentParam.flag,bean.getBiaoqian());
                        STActivity(intent,KeChengDetailActivity.class);
                    }
                });
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_zhuyao_shouke.setAdapter(adapter);
        rv_zhuyao_shouke.setNestedScrollingEnabled(false);

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
        map.put("teacher_id", teacherId);
        map.put("sign", getSign(map));
        ApiRequest.getTeacherDetail(map, new MyCallBack<TeacherObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(TeacherObj obj) {
                setData(obj);
            }
        });
    }

    private void setData(TeacherObj obj) {
        adapter.setList(obj.getShouke_list(),true);
        GlideUtils.into(mContext,obj.getHead_portrait(),iv_teacher_img);
        MyRx.start(new MyFlowableSubscriber<Bitmap>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Bitmap> emitter) {
                try {
                    Bitmap bitmap = Glide.with(mContext).load(obj.getImage_url())
                            .asBitmap().error(R.drawable.laoshijieshao_bg01).into(PhoneUtils.getScreenWidth(mContext), PhoneUtils.dip2px(mContext, 150))
                            .get();
                    emitter.onNext(bitmap);
                } catch (Exception e) {
                    emitter.onError(e);
                    e.printStackTrace();
                }
                emitter.onComplete();
            }
            @Override
            public void onNext(Bitmap obj) {
                BitmapDrawable drawable=new BitmapDrawable(getResources(),obj);
                ll_teacher_bg.setBackground(drawable);
            }
        });
        tv_teacher_name.setText(obj.getTeacher_name());
        tv_teacher_age.setText(obj.getSchool_age()+"年");
        tv_teacher_fangxiang.setText(obj.getMain_attack());
        tv_teacher_jiguan.setText(obj.getTeacher_nationality());
        tv_teacher_jieshao.setText(obj.getTeacher_introduction());
        GlideUtils.into(mContext,obj.getImage_url(),iv_teacher_kecheng);
        tv_teacher_title.setText(obj.getTitle());
        tv_teacher_flag.setText(obj.getBiaoqian());
    }
    @Override
    protected void onViewClick(View v) {

    }
}

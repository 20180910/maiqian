package com.sk.maiqian.module.yingyupeixun.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.yingyupeixun.network.ApiRequest;
import com.sk.maiqian.module.yingyupeixun.network.response.OnlineStudyObj;
import com.sk.maiqian.module.yingyupeixun.network.response.VideoDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ShipinDetailActivity extends BaseActivity {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.tv_shipin_title)
    TextView tv_shipin_title;
    @BindView(R.id.tv_shipin_time)
    TextView tv_shipin_time;
    @BindView(R.id.tv_shipin_renqun)
    TextView tv_shipin_renqun;
    @BindView(R.id.tv_shipin_jieshao)
    TextView tv_shipin_jieshao;
    @BindView(R.id.rv_shipin_detail)
    MyRecyclerView rv_shipin_detail;


    private String dataId;
    private MyLoadMoreAdapter  adapter;

    @Override
    protected int getContentView() {
        setAppTitle("");
        setAppRightImg(R.drawable.share);
        setTitleBackgroud(R.color.transparent);
        return R.layout.shipindetail_act;
    }

    @Override
    protected void initView() {
        dataId = getIntent().getStringExtra(IntentParam.dataId);


        adapter =new MyLoadMoreAdapter<OnlineStudyObj>(mContext,R.layout.englishpeixun_online_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, OnlineStudyObj bean) {
                ImageView imageView = holder.getImageView(R.id.iv_online);
                GlideUtils.into(mContext,bean.getImage_url(),imageView);
                holder.setText(R.id.tv_online_title,bean.getTitle());
                holder.setText(R.id.tv_online_renqun,bean.getAttachment());
                holder.setText(R.id.tv_online_shichang,bean.getTime_length());
                holder.setText(R.id.tv_online_time,bean.getAdd_time());
                holder.itemView.setOnClickListener(new MyOnClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra(IntentParam.dataId,bean.getCourseware_id());
                        STActivity(intent,ShipinDetailActivity.class);
                    }
                });
            }
        };
//        adapter.setOnLoadMoreListener(this);

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
        map.put("courseware_id", dataId);
        map.put("sign", getSign(map));
        ApiRequest.getOnlineStudyDetail(map, new MyCallBack<VideoDetailObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(VideoDetailObj obj) {
                setData(obj);
            }
        });

    }
    private void setData(VideoDetailObj obj) {
        GlideUtils.intoD(mContext, obj.getImage_url(), videoplayer.thumbImageView, R.drawable.yingyupeixun_list_img02);
        videoplayer.setUp(obj.getVideo_link(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"");
        tv_shipin_title.setText(obj.getTitle());
        tv_shipin_time.setText(obj.getAdd_time());
        tv_shipin_renqun.setText("适合人群:"+obj.getAttachment());
        tv_shipin_jieshao.setText(obj.getCourseware_introduction());

        adapter.setList(obj.getShouke_list());
        rv_shipin_detail.setLayoutManager(new LinearLayoutManager(mContext));
        rv_shipin_detail.setNestedScrollingEnabled(false);
        rv_shipin_detail.setAdapter(adapter);

    }
    @Override
    protected void onViewClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (videoplayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoplayer.releaseAllVideos();
    }
}

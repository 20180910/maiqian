package com.sk.maiqian.module.home.activity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.library.base.view.richedit.RichEditor;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.ZiXunDetailObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/30.
 */

public class ZiXunDetailActivity extends BaseActivity {
    @BindView(R.id.re_zixun_detail)
    RichEditor re_zixun_detail;

    @BindView(R.id.tv_zixun_detail_title)
    TextView tv_zixun_detail_title;

    @BindView(R.id.tv_zixun_detail_laiyuan)
    TextView tv_zixun_detail_laiyuan;

    @BindView(R.id.tv_zixun_detail_time)
    TextView tv_zixun_detail_time;
    private String ziXunId;

    @Override
    protected int getContentView() {
        setAppTitle("资讯详情");
        return R.layout.zixundetail_act;
    }

    @Override
    protected void initView() {
        ziXunId = getIntent().getStringExtra(IntentParam.ziXunId);
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
        map.put("information_id",ziXunId);
        map.put("sign",getSign(map));
        ApiRequest.getZiXunDetail(map, new MyCallBack<ZiXunDetailObj>(mContext,pl_load,pcfl) {
            @Override
            public void onSuccess(ZiXunDetailObj obj) {
                re_zixun_detail.setHtml(obj.getContent());
                re_zixun_detail.setInputEnabled(false);
                re_zixun_detail.setEditorFontSize(14);
                re_zixun_detail.setEditorFontColor(ContextCompat.getColor(mContext,R.color.gray_66));

                tv_zixun_detail_title.setText(obj.getTitle());
                tv_zixun_detail_laiyuan.setText(obj.getAuthor());
                tv_zixun_detail_time.setText(obj.getAdd_time());
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}

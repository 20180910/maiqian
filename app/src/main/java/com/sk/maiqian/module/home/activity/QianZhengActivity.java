package com.sk.maiqian.module.home.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.home.network.ApiRequest;
import com.sk.maiqian.module.home.network.response.QianZhengObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/22.
 */

public class QianZhengActivity extends BaseActivity {
    @BindView(R.id.rv_guojia_qianzheng)
    RecyclerView rv_guojia_qianzheng;

    @BindView(R.id.iv_qianzheng_guojia)
    ImageView iv_qianzheng_guojia;

    @BindView(R.id.tv_qianzheng_zhengce)
    TextView tv_qianzheng_zhengce;

    @BindView(R.id.tv_qianzheng_shuoming)
    TextView tv_qianzheng_shuoming;

    MyLoadMoreAdapter adapter;
    private String dataId, title;

    @Override
    protected int getContentView() {
        setAppRightImg(R.drawable.share);
        return R.layout.guojiaqianzheng_act;
    }

    @Override
    protected void initView() {
        dataId = getIntent().getStringExtra(IntentParam.dataId);
        title = getIntent().getStringExtra(IntentParam.title);
        setAppTitle(title + "签证");


        adapter = new MyLoadMoreAdapter<QianZhengObj.QianZhengBean>(mContext, R.layout.guojiaqianzheng_item, pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, QianZhengObj.QianZhengBean bean) {
                ImageView imageView = holder.getImageView(R.id.iv_qianzheng);
                GlideUtils.into(mContext, bean.getImg_url(), imageView);
                holder.setText(R.id.tv_qianzheng_title, bean.getTitle());
                holder.setText(R.id.tv_qianzheng_day, bean.getFor_how_long());
                holder.setText(R.id.tv_qianzheng_price, bean.getPrice() + "");
            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_guojia_qianzheng.setLayoutManager(new LinearLayoutManager(mContext));
        rv_guojia_qianzheng.addItemDecoration(getItemDivider(PhoneUtils.dip2px(mContext, 5)));
        rv_guojia_qianzheng.setAdapter(adapter);
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
        map.put("countrie_region_id", dataId);
        map.put("pagesize", pagesize + "");
        map.put("page", page + "");
        map.put("sign", getSign(map));
        ApiRequest.getQianZhengList(map, new MyCallBack<QianZhengObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(QianZhengObj obj) {
                GlideUtils.into(mContext, obj.getNational_flag(), iv_qianzheng_guojia);
                tv_qianzheng_zhengce.setText(obj.getTitle());
                tv_qianzheng_shuoming.setText(obj.getPolicys());

                if (isLoad) {
                    pageNum++;
                    adapter.addList(obj.getList2(), true);
                } else {
                    pageNum = 2;
                    adapter.setList(obj.getList2(), true);
                }
            }
        });

    }

    @Override
    protected void onViewClick(View v) {

    }
}

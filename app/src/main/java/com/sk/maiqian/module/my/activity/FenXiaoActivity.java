package com.sk.maiqian.module.my.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.module.my.network.ApiRequest;
import com.sk.maiqian.module.my.network.response.FenXiaoObj;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/28.
 */

public class FenXiaoActivity extends BaseActivity {

    MyLoadMoreAdapter adapter;
    @BindView(R.id.iv_fenxiao)
    ImageView iv_fenxiao;
    @BindView(R.id.tv_fenxiao_code)
    TextView tv_fenxiao_code;
    @BindView(R.id.tv_fenxiao_content)
    TextView tv_fenxiao_content;
    @BindView(R.id.tv_fenxiao_yaoqing)
    TextView tv_fenxiao_yaoqing;
    @BindView(R.id.rv_fenxiao)
    MyRecyclerView rv_fenxiao;

    @Override
    protected int getContentView() {
        setAppTitle("我的分销码");
        setAppRightImg(R.drawable.share);
        return R.layout.fenxiao_act;
    }

    @Override
    protected void initView() {
        adapter = new MyLoadMoreAdapter<FenXiaoObj.XiaJi>(mContext, R.layout.fenxiao_item, pageSize,nsv) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, FenXiaoObj.XiaJi bean) {
                holder.setText(R.id.tv_fenxiao_name,bean.getNick_name())
                .setText(R.id.tv_fenxiao_time,bean.getAdd_time());
            }
        };
        adapter.setOnLoadMoreListener(this);

        rv_fenxiao.setAdapter(adapter);
        rv_fenxiao.setNestedScrollingEnabled(false);

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
        ApiRequest.getFenXiao(map, new MyCallBack<FenXiaoObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(FenXiaoObj obj) {
                GlideUtils.into(mContext,obj.getDistribution_url(),iv_fenxiao);
                tv_fenxiao_code.setText(obj.getDistribution_yard());
                tv_fenxiao_content.setText(obj.getContent());
                adapter.setList(obj.getList(),true);
            }
        });

    }

    @OnClick({R.id.tv_fenxiao_yaoqing})
    protected void onViewClick(View v) {
        switch (v.getId()){
            case R.id.tv_fenxiao_yaoqing:

            break;
        }
    }
}

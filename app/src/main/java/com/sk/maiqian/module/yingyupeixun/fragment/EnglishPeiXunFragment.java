package com.sk.maiqian.module.yingyupeixun.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.library.base.view.MyRecyclerView;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23.
 */

public class EnglishPeiXunFragment extends BaseFragment {
    @BindView(R.id.rv_englishpeixundaiban)
    MyRecyclerView rv_englishpeixundaiban;
    @BindView(R.id.iv_englishpeixun_liuyan)
    ImageView iv_englishpeixun_liuyan;

    MyLoadMoreAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.englishpeixun_frag;
    }

    public static EnglishPeiXunFragment newInstance() {
        Bundle args = new Bundle();

        EnglishPeiXunFragment fragment = new EnglishPeiXunFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.englishpeixun_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setTestListSize(11);
        adapter.setOnLoadMoreListener(this);
        rv_englishpeixundaiban.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

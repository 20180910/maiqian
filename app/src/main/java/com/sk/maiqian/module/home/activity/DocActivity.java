package com.sk.maiqian.module.home.activity;

import android.view.View;

import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

/**
 * Created by Administrator on 2018/5/4.
 */

public class DocActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        setAppTitle("资料文档");
        return R.layout.doc_act;
    }

    @Override
    protected void initView() {
        String url="http://121.40.186.118:10089/upload/2222.doc";
//

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

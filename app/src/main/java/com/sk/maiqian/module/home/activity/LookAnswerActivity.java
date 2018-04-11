package com.sk.maiqian.module.home.activity;

import android.view.View;
import android.widget.TextView;

import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/30.
 */

public class LookAnswerActivity extends BaseActivity {
    @BindView(R.id.tv_answer_title)
    TextView tv_answer_title;

    @BindView(R.id.tv_answer_content)
    TextView tv_answer_content;
    private String dayijiehuo_title;
    private String dayijiehuo_content;


    @Override
    protected int getContentView() {
        setAppTitle("查看解决方案");
        setAppRightImg(R.drawable.share);
        return R.layout.lookanswer_act;
    }

    @Override
    protected void initView() {
        dayijiehuo_title = getIntent().getStringExtra(IntentParam.dayijiehuo_title);
        dayijiehuo_content = getIntent().getStringExtra(IntentParam.dayijiehuo_content);
        tv_answer_title.setText(dayijiehuo_title);
        tv_answer_content.setText(dayijiehuo_content);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

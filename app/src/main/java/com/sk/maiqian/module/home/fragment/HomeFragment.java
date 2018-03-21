package com.sk.maiqian.module.home.fragment;

import android.view.View;

import com.github.baseclass.adapter.LoadMoreAdapter;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */

public class HomeFragment extends BaseFragment {





    LoadMoreAdapter adapter;

    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected int getContentView() {
        return R.layout.home_frag;
    }

    @Override
    protected void initView() {



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
        map.put("rnd", getRnd());
        map.put("sign", getSign(map));
       /* ApiRequest.getHomeZiXunData(map, new MyCallBack<List<HomeZiXunDataObj>>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(List<HomeZiXunDataObj> list) {
                if (isLoad) {
                    pageNum++;
                    adapter.addList(list, true);
                } else {
                    pageNum = 2;
                    adapter.setList(list, true);
                }
            }
        });*/

    }



//    @OnClick({ })
    protected void onViewClick(View v) {
        switch (v.getId()) {
           /* case R.id.ll_home_tab1:
                STActivity(JiSuHuanKuanActivity.class);
                break;*/
        }
    }

    @Override
    public void onStop() {
        super.onStop();
       /* if (bn_home != null && bannerList != null) {
            bn_home.stopAutoPlay();
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if (bn_home != null && bannerList != null) {
            bn_home.startAutoPlay();
        }*/
    }


}

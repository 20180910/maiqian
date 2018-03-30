package com.sk.maiqian.module.my.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.baseclass.adapter.MyLoadMoreAdapter;
import com.github.baseclass.adapter.MyRecyclerViewHolder;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.github.rxbus.rxjava.MyRx;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import butterknife.BindView;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MyCollectFragment extends BaseFragment {
    @BindView(R.id.rv_my_collection)
    RecyclerView rv_my_collection;
    MyLoadMoreAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.mycollection_frag;
    }

    @Override
    protected void initView() {
        adapter=new MyLoadMoreAdapter<String>(mContext,R.layout.mycollection_item,pageSize) {
            @Override
            public void bindData(MyRecyclerViewHolder holder, int position, String bean) {

            }
        };
        adapter.setOnLoadMoreListener(this);
        rv_my_collection.setAdapter(adapter);

        MyRx.start(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) {
                emitter.onNext("");
                emitter.onComplete();
            }
            @Override
            public void onNext(String obj) {

            }
        });

        /*MyRxBus.getInstance().post(new LoginObj());
        MyDisposable disposable = MyRxBus.getInstance().getEvent(LoginObj.class, new MyConsumer<LoginObj>() {
            @Override
            public void onAccept(LoginObj obj) {

            }
        });
        disposable.dispose();*/
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

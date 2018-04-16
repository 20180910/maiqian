package com.sk.maiqian.module.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.androidtools.PhoneUtils;
import com.github.androidtools.inter.MyOnClickListener;
import com.github.baseclass.adapter.BaseRecyclerAdapter;
import com.github.baseclass.adapter.RecyclerViewHolder;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.module.home.bean.RiLiObj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/13.
 */

public class RiLiActivity extends BaseActivity {
    @BindView(R.id.rv_rili)
    RecyclerView rv_rili;

    BaseRecyclerAdapter adapter;
    private List<RiLiObj> list;

    @Override
    protected int getContentView() {
        setAppTitle("选择日期");
        return R.layout.rili_act;
    }

    @Override
    protected void initView() {
        long maxDate = getIntent().getLongExtra(IntentParam.qianZhengObj, 0);

        adapter=new BaseRecyclerAdapter<RiLiObj>(mContext,R.layout.rili_item) {
            private RecyclerView.RecycledViewPool recycledViewPool;
            @Override
            public void bindData(RecyclerViewHolder holder, int i, RiLiObj bean) {
                holder.setText(R.id.tv_rilidate,bean.year+"年"+bean.month+"月");
                if(recycledViewPool!=null){
                    recycledViewPool = rv_rili.getRecycledViewPool();
                }
                RecyclerView rv_qiandao = (RecyclerView) holder.getView(R.id.rv_qiandao);
                rv_qiandao.setRecycledViewPool(recycledViewPool);
                BaseRecyclerAdapter childerAdapter=new BaseRecyclerAdapter<RiLiObj>(mContext,R.layout.rili_item_item) {
                    @Override
                    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        TextView textView=new TextView(mContext);
                        int width = (PhoneUtils.getScreenWidth(mContext) - PhoneUtils.dip2px(mContext, 10)) / 7;
                        textView.setHeight(width);
                        textView.setWidth(width);
                        textView.setGravity(Gravity.CENTER);
                        RecyclerViewHolder viewHolder=new RecyclerViewHolder(mContext,textView);
                        return viewHolder;
                    }
                    @Override
                    public void bindData(RecyclerViewHolder viewHolder, int viewPosition, RiLiObj obj) {
                        TextView itemView = (TextView) viewHolder.itemView;

                        itemView.setOnClickListener(new MyOnClickListener() {
                            @Override
                            protected void onNoDoubleClick(View view) {
                                Calendar instance = Calendar.getInstance();
                                instance.setTime(new Date());
                                if(bean.year==instance.get(Calendar.YEAR)&&bean.month==(instance.get(Calendar.MONTH)+1)&&obj.day==instance.get(Calendar.DAY_OF_MONTH)){
                                    showMsg("不能选择今天");
                                    return;
                                }
                                Intent intent=new Intent();
                                RiLiObj riLiObj=new RiLiObj();
                                riLiObj.year=bean.year;
                                riLiObj.month=bean.month;
                                riLiObj.day=obj.day;
                                intent.putExtra(IntentParam.riliDate,riLiObj);
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        });
                        itemView.setText(obj.day+"");
//                        viewHolder.setText(R.id.tv_rili_item,bean.day+"");
                        if(obj.type==0||obj.type==2){
                            itemView.setVisibility(View.INVISIBLE);
                        }else{
                            itemView.setVisibility(View.VISIBLE);

                            Calendar cal = Calendar.getInstance();
                            cal.set(Calendar.YEAR,bean.year);
                            cal.set(Calendar.MONTH,bean.month-1);
                            cal.set(Calendar.DAY_OF_MONTH,obj.day);
                            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

                            Calendar instance = Calendar.getInstance();
                            instance.setTime(new Date());
                            if(w==0||w==6){
                                if(bean.year==instance.get(Calendar.YEAR)&&bean.month==(instance.get(Calendar.MONTH)+1)&&obj.day<=instance.get(Calendar.DAY_OF_MONTH)){
                                    itemView.setEnabled(false);
                                    itemView.setTextColor(Color.parseColor("#74FC353A"));
                                }else{
                                    itemView.setEnabled(true);
                                    itemView.setTextColor(ContextCompat.getColor(mContext,R.color.red));
                                }
                            }else{
                                if(bean.year==instance.get(Calendar.YEAR)&&bean.month==(instance.get(Calendar.MONTH)+1)&&obj.day<=instance.get(Calendar.DAY_OF_MONTH)){
                                    itemView.setEnabled(false);
                                    itemView.setTextColor(ContextCompat.getColor(mContext,R.color.gray_ea));
                                }else{
                                    itemView.setEnabled(true);
                                    itemView.setTextColor(ContextCompat.getColor(mContext,R.color.gray_66));
                                }
                            }

                        }
                    }
                    @Override
                    public int getItemViewType(int position) {
                        return position;
                    }
                };
                childerAdapter.setList(bean.list);
                rv_qiandao.setLayoutManager(new GridLayoutManager(mContext,7));
//                rv_qiandao.addItemDecoration(getItemDivider());
                rv_qiandao.setAdapter(childerAdapter);

            }
            @Override
            public int getItemViewType(int position) {
                return position;
            }
        };
        rv_rili.setLayoutManager(new LinearLayoutManager(mContext));
        rv_rili.setAdapter(adapter);

        setRiLiData(maxDate);
    }
    public int getYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
     return c.get(Calendar.YEAR);
    }
    public int getMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
     return c.get(Calendar.MONTH);
    }
    private void setRiLiData(long time) {
        list = new ArrayList<>();
        RiLiObj obj;
        Date newDate = new Date();
        Date maxDate = new Date(time * 1000);

        Calendar c = Calendar.getInstance();
        c.get(Calendar.YEAR);


        int jianGe=getYear(maxDate)-getYear(newDate);
        int jianGeMonth=0;
        if(jianGe>1){
            jianGeMonth=jianGe*12;
        }
        jianGeMonth=12-getMonth(newDate)+jianGeMonth;
        jianGeMonth=jianGeMonth+getMonth(maxDate)+1;

        int monthFlag=getMonth(newDate);
        int yearFlag=getYear(newDate);
        for (int i = 0; i < jianGeMonth; i++) {
            obj=new RiLiObj();
            obj.year=yearFlag;
            obj.month=monthFlag+1;
            obj.list=setDay(yearFlag, monthFlag);
            list.add(obj);
            monthFlag++;
            if(monthFlag==11){
                monthFlag=0;
                yearFlag++;
            }
        }
        adapter.setList(list,true);
    }

    private List<RiLiObj> setDay(int year,int month) {
        List<RiLiObj> list=new ArrayList<>();
        Calendar current = Calendar.getInstance();
        current.set(Calendar.YEAR,year);
        current.set(Calendar.MONTH,month);
        current.set(Calendar.DATE, 1);
        int firstWeek = current.get(Calendar.DAY_OF_WEEK) - 1;
        current.roll(Calendar.DATE, -1);
        int lastWeek = current.get(Calendar.DAY_OF_WEEK) - 1;

        Calendar before = Calendar.getInstance();
        current.set(Calendar.YEAR,year);
        before.set(Calendar.MONTH,Calendar.getInstance().get(Calendar.MONTH)-1);
        int beforeMonthLastDay = getMonthLastDay(Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.MONTH) - 1);
        int addBeforeDay=beforeMonthLastDay-firstWeek+1;
        for (int i = 0; i < firstWeek; i++) {
            RiLiObj bean=new RiLiObj();
            bean.type=0;
            bean.day=addBeforeDay;
            addBeforeDay++;
            beforeMonthLastDay--;
            list.add(bean);
        }
        for (int i = 1; i <= getCurrentMonthLastDay(); i++) {
            RiLiObj bean=new RiLiObj();
            bean.type=1;
            bean.day=i;
            list.add(bean);
        }
        int j=1;
        for (int i = lastWeek; i < 6; i++) {
            RiLiObj bean=new RiLiObj();
            bean.type=0;
            bean.day=j;
            j++;
            list.add(bean);
        }
        return list;
    }
    public int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}

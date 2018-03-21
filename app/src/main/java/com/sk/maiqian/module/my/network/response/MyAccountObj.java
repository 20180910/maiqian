package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class MyAccountObj extends BaseObj {
    /**
     * balance : 0.0
     * balance_detail_list : [{"remark":"账户余额-提现-申请","value":-50.55,"add_time":"2017年12月11日"},{"remark":"账户余额-提现-申请","value":-50,"add_time":"2017年12月11日"}]
     */

    private double balance;
    private List<BalanceDetailListBean> balance_detail_list;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<BalanceDetailListBean> getBalance_detail_list() {
        return balance_detail_list;
    }

    public void setBalance_detail_list(List<BalanceDetailListBean> balance_detail_list) {
        this.balance_detail_list = balance_detail_list;
    }

    public static class BalanceDetailListBean {
        /**
         * remark : 账户余额-提现-申请
         * value : -50.55
         * add_time : 2017年12月11日
         */

        private String remark;
        private double value;
        private String add_time;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}

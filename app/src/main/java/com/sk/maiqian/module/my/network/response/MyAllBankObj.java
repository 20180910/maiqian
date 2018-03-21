package com.sk.maiqian.module.my.network.response;

import com.library.base.BaseObj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MyAllBankObj extends BaseObj {
    private List<CreditCardListBean> credit_card_list;
    private List<CashCardListBean> cash_card_list;

    public List<CreditCardListBean> getCredit_card_list() {
        return credit_card_list;
    }

    public void setCredit_card_list(List<CreditCardListBean> credit_card_list) {
        this.credit_card_list = credit_card_list;
    }

    public List<CashCardListBean> getCash_card_list() {
        return cash_card_list;
    }

    public void setCash_card_list(List<CashCardListBean> cash_card_list) {
        this.cash_card_list = cash_card_list;
    }

    public static class CreditCardListBean implements Serializable {
        /**
         * id : 482
         * userId : 593
         * status : 1
         * cardNo : 6259********2877
         * cardType : 1
         * cardCcv : 461
         * expireYear : 25
         * expireMonth : 08
         * bankId : 4
         * bankName : 建设银行
         * bankAbbr : CCB
         * bankColor : #018ffd
         * phone : 18117352720
         * billDate : 24
         * paymentDate : 13
         * planStatus : 0
         */

        private int id;
        private int userId;
        private int status;
        private String cardNo;
        private int cardType;
        private String cardCcv;
        private String expireYear;
        private String expireMonth;
        private int bankId;
        private String bankName;
        private String bankAbbr;
        private String bankColor;
        private String phone;
        private int billDate;//账单日
        private int paymentDate;//付款日
        private int planStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getCardCcv() {
            return cardCcv;
        }

        public void setCardCcv(String cardCcv) {
            this.cardCcv = cardCcv;
        }

        public String getExpireYear() {
            return expireYear;
        }

        public void setExpireYear(String expireYear) {
            this.expireYear = expireYear;
        }

        public String getExpireMonth() {
            return expireMonth;
        }

        public void setExpireMonth(String expireMonth) {
            this.expireMonth = expireMonth;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankAbbr() {
            return bankAbbr;
        }

        public void setBankAbbr(String bankAbbr) {
            this.bankAbbr = bankAbbr;
        }

        public String getBankColor() {
            return bankColor;
        }

        public void setBankColor(String bankColor) {
            this.bankColor = bankColor;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getBillDate() {
            return billDate;
        }

        public void setBillDate(int billDate) {
            this.billDate = billDate;
        }

        public int getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(int paymentDate) {
            this.paymentDate = paymentDate;
        }

        public int getPlanStatus() {
            return planStatus;
        }

        public void setPlanStatus(int planStatus) {
            this.planStatus = planStatus;
        }
    }

    public static class CashCardListBean implements Serializable{
        /**
         * id : 48
         * bank_image : http://121.40.186.118:1145/upload/201709/18/201709181455150015.png
         * bank_name : 中国银行
         * card_type : 储蓄卡
         * bank_card : 尾号1255
         */

        private int id;
        private String bank_image;
        private String bank_name;
        private String card_type;
        private String bank_card;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank_image() {
            return bank_image;
        }

        public void setBank_image(String bank_image) {
            this.bank_image = bank_image;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }
    }
}

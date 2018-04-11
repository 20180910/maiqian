package com.sk.maiqian.module.home.network.response;

import com.library.base.BaseObj;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class QianZhengDetailObj extends BaseObj {
    /**
     * visa_id : 1
     * title : 美国三个月单次个人旅游签证<上海签证>
     * price : 300.0
     * price_detail : 价格明细价格明细
     * for_how_long : 收齐资料8-10个工作日
     * canshu_list : [{"title":"有效期限","content":"3个月"},{"title":"最长停留","content":"59天"},{"title":"入境次数","content":"1次"}]
     * rate_signing : 100
     * service_content : 提供签证咨询+签证方案评估+签证出签回寄
     * accept_range : 1.不限领取,全国受理
     * zaizhirenyuan : 5
     * ziyouzhiyezhe : 5
     * tuixiurenyuan : 5
     * zaixiaoxuesheng : 5
     * xuelingqianertong : 0
     * liucheng_list : [{"title":"预约并支付","content":"根据您实际出行时间，在线选择对应签证产品进行支付"},{"title":"准备材料","content":"支付后，客服会立即邮件您办理签证所需的材料列表，请根据材料列表及时准备相关材料。"},{"title":"材料审核及寄送","content":"麦签客服将根据您提供的材料进行初审，材料审核结果将根据电话和短信的形式通知。初审通过后，请根据麦签客服提供给您的邮寄地址，及时邮寄您的材料"},{"title":"麦签送签","content":"麦签客服根据您寄送的材料进行最终的审核和资料的整理。并及时递送使馆"},{"title":"出签及配送","content":"麦签客服将根据您提供的邮寄地址，及时将材料以及出签资料交付与您。请仔细核对出签信息，以免影响您的出行。"}]
     * visa_instructions : 未满16周岁的申请人必须要与父母一同申请
     * max_apply_time : 1530288000
     * visa_information : 签证须知签证须知</br>签证须知签证须知
     * is_collect : 1
     */

    private int visa_id;
    private String title;
    private double price;
    private String price_detail;
    private String for_how_long;
    private double rate_signing;
    private String service_content;
    private String accept_range;
    private int zaizhirenyuan;
    private int ziyouzhiyezhe;
    private int tuixiurenyuan;
    private int zaixiaoxuesheng;
    private int xuelingqianertong;
    private String visa_instructions;
    private long max_apply_time;
    private String visa_information;
    private int is_collect;
    private List<CanshuListBean> canshu_list;
    private List<LiuchengListBean> liucheng_list;

    public int getVisa_id() {
        return visa_id;
    }

    public void setVisa_id(int visa_id) {
        this.visa_id = visa_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPrice_detail() {
        return price_detail;
    }

    public void setPrice_detail(String price_detail) {
        this.price_detail = price_detail;
    }

    public String getFor_how_long() {
        return for_how_long;
    }

    public void setFor_how_long(String for_how_long) {
        this.for_how_long = for_how_long;
    }

    public double getRate_signing() {
        return rate_signing;
    }

    public void setRate_signing(double rate_signing) {
        this.rate_signing = rate_signing;
    }

    public String getService_content() {
        return service_content;
    }

    public void setService_content(String service_content) {
        this.service_content = service_content;
    }

    public String getAccept_range() {
        return accept_range;
    }

    public void setAccept_range(String accept_range) {
        this.accept_range = accept_range;
    }

    public int getZaizhirenyuan() {
        return zaizhirenyuan;
    }

    public void setZaizhirenyuan(int zaizhirenyuan) {
        this.zaizhirenyuan = zaizhirenyuan;
    }

    public int getZiyouzhiyezhe() {
        return ziyouzhiyezhe;
    }

    public void setZiyouzhiyezhe(int ziyouzhiyezhe) {
        this.ziyouzhiyezhe = ziyouzhiyezhe;
    }

    public int getTuixiurenyuan() {
        return tuixiurenyuan;
    }

    public void setTuixiurenyuan(int tuixiurenyuan) {
        this.tuixiurenyuan = tuixiurenyuan;
    }

    public int getZaixiaoxuesheng() {
        return zaixiaoxuesheng;
    }

    public void setZaixiaoxuesheng(int zaixiaoxuesheng) {
        this.zaixiaoxuesheng = zaixiaoxuesheng;
    }

    public int getXuelingqianertong() {
        return xuelingqianertong;
    }

    public void setXuelingqianertong(int xuelingqianertong) {
        this.xuelingqianertong = xuelingqianertong;
    }

    public String getVisa_instructions() {
        return visa_instructions;
    }

    public void setVisa_instructions(String visa_instructions) {
        this.visa_instructions = visa_instructions;
    }

    public long getMax_apply_time() {
        return max_apply_time;
    }

    public void setMax_apply_time(long max_apply_time) {
        this.max_apply_time = max_apply_time;
    }

    public String getVisa_information() {
        return visa_information;
    }

    public void setVisa_information(String visa_information) {
        this.visa_information = visa_information;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public List<CanshuListBean> getCanshu_list() {
        return canshu_list;
    }

    public void setCanshu_list(List<CanshuListBean> canshu_list) {
        this.canshu_list = canshu_list;
    }

    public List<LiuchengListBean> getLiucheng_list() {
        return liucheng_list;
    }

    public void setLiucheng_list(List<LiuchengListBean> liucheng_list) {
        this.liucheng_list = liucheng_list;
    }

    public static class CanshuListBean {
        /**
         * title : 有效期限
         * content : 3个月
         */

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class LiuchengListBean {
        /**
         * title : 预约并支付
         * content : 根据您实际出行时间，在线选择对应签证产品进行支付
         */

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

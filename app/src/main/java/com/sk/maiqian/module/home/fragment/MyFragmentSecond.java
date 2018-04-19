package com.sk.maiqian.module.home.fragment;

import android.content.Intent;
import android.view.View;

import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.library.base.tools.has.BitmapUtils;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseFragment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;
import top.zibin.luban.Luban;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/12/4.
 */

public class MyFragmentSecond extends BaseFragment {



    @Override
    protected int getContentView() {

        return R.layout.my_frag;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showProgress();
        getOtherData();
        getData(1, false);
    }

    @Override
    protected void getOtherData() {
        super.getOtherData();
        isHasMsg();
    }

    @Override
    protected void onMyReStart() {
        super.onMyReStart();

        /*String imgPath = SPUtils.getString(mContext, AppXml.avatar, null);
        if(!TextUtils.isEmpty(imgPath)){
            Glide.with(mContext).load(imgPath).error(R.drawable.my_people).into(civ_my);
        }*/
        getOtherData();
        getData(1, false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        super.getData(page, isLoad);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        /*ApiRequest.getUserInfo(map, new MyCallBack<LoginObj>(mContext, pl_load, pcfl) {
            @Override
            public void onSuccess(LoginObj obj) {
                Glide.with(mContext).load(obj.getAvatar()).error(R.drawable.my_people).into(civ_my);
                tv_my_name.setText(obj.getUser_name());
                tv_my_yue.setText("账户余额:" + obj.getAmount());
                loginResult(obj);
            }
        });*/

    }
    public void isHasMsg() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        map.put("sign", getSign(map));
        /*NetApiRequest.isHasNewMsg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                if (obj.getIs_check() == 1) {
                    tv_my_has_msg.setVisibility(View.VISIBLE);
                } else {
                    tv_my_has_msg.setVisibility(View.GONE);
                }
            }
        });*/
    }

    /*private void loginResult(LoginObj obj) {
        tv_my_shouyi.setText("累计奖励: ¥"+obj.getCommission()+"元");

        SPUtils.setPrefString(mContext, AppXml.user_id, obj.getUser_id());
        SPUtils.setPrefString(mContext, AppXml.mobile, obj.getMobile());
        SPUtils.setPrefString(mContext, AppXml.sex, obj.getSex());
        SPUtils.setPrefString(mContext, AppXml.avatar, obj.getAvatar());
        SPUtils.setPrefString(mContext, AppXml.birthday, obj.getBirthday());
        SPUtils.setPrefString(mContext, AppXml.user_name, obj.getUser_name());
        SPUtils.setPrefString(mContext, AppXml.nick_name, obj.getNick_name());
        SPUtils.setPrefFloat(mContext, AppXml.amount, (float) obj.getAmount());
        SPUtils.setPrefFloat(mContext, AppXml.commission, obj.getCommission());
        SPUtils.setPrefInt(mContext, AppXml.message_sink, obj.getMessage_sink());
        SPUtils.setPrefInt(mContext, AppXml.is_validation, obj.getIs_validation());
        SPUtils.setPrefInt(mContext, AppXml.cumulative_reward, obj.getCumulative_reward());

        SPUtils.setPrefString(mContext, AppXml.name, obj.getName());
        SPUtils.setPrefString(mContext, AppXml.email, obj.getEmail());
        SPUtils.setPrefString(mContext, AppXml.major, obj.getMajor());
    }
*/
//    @OnClick({})
    public void onViewClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case result_select_photo:
                    String photoPath = getSelectPhotoPath(data);
                    uploadImg(photoPath);
                    break;
                case result_take_photo:
                    uploadImg(takePhotoImgSavePath);
                break;
            }
        }
    }

    private void uploadImg(final String imgPath) {
        showLoading();
        RXStart(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> subscriber) {
                try {
                    List<File> files = Luban.with(mContext).ignoreBy(700).load(imgPath).get();
                    String imgStr = BitmapUtils.fileToString(files.get(0));
                    subscriber.onNext(imgStr);
                    subscriber.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onNext(String obj) {
                /*UploadImgBody body=new UploadImgBody();
                body.setFile(baseImg);
                String rnd = getRnd();
                Map<String,String> map=new HashMap<String,String>();
                map.put("rnd",rnd);
                map.put("sign",getSign(map));
                NetApiRequest.uploadImg(map,body, new MyCallBack<BaseObj>(mContext,true) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        String imgUrl = obj.getImg();
                        updateUserImg(imgUrl);
                    }
                });*/
            }
            @Override
            public void onError(Throwable t) {
                super.onError(t);
                dismissLoading();
                showToastS("图片处理失败");
            }
        });
    }

    private void updateUserImg(String imgUrl) {
        Map<String,String>map=new HashMap<String,String>();
        map.put("user_id",getUserId());
        map.put("avatar",imgUrl);
        map.put("sign",getSign(map));
        /*ApiRequest.updateUserImg(map, new MyCallBack<BaseObj>(mContext) {
            @Override
            public void onSuccess(BaseObj obj) {
                SPUtils.setPrefString(mContext,AppXml.avatar,imgUrl);
                Glide.with(mContext).load(imgUrl).error(R.drawable.my_people).into(civ_my);
            }
        });*/

    }
}

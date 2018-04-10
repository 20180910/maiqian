package com.sk.maiqian.module.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.androidtools.SPUtils;
import com.github.rxbus.rxjava.MyFlowableSubscriber;
import com.library.base.BaseObj;
import com.library.base.tools.has.BitmapUtils;
import com.sk.maiqian.AppXml;
import com.sk.maiqian.IntentParam;
import com.sk.maiqian.R;
import com.sk.maiqian.base.BaseActivity;
import com.sk.maiqian.base.GlideUtils;
import com.sk.maiqian.base.MyCallBack;
import com.sk.maiqian.network.NetApiRequest;
import com.sk.maiqian.network.request.UploadImgBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.FlowableEmitter;
import io.reactivex.annotations.NonNull;
import top.zibin.luban.Luban;

/**
 * Created by Administrator on 2018/3/28.
 */

public class PersonInfoActivity extends BaseActivity {
    @BindView(R.id.civ_personinfo)
    CircleImageView civ_personinfo;
    @BindView(R.id.ll_personinfo)
    LinearLayout ll_personinfo;
    @BindView(R.id.tv_personinfo_phone)
    TextView tv_personinfo_phone;
    @BindView(R.id.tv_personinfo_nickname)
    TextView tv_personinfo_nickname;

    @Override
    protected int getContentView() {
        setAppTitle("个人信息");
        setAppRightImg(R.drawable.share);
        return R.layout.personinfo_act;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInfo();
    }
    @Override
    protected void initData() {

    }
    private void setInfo() {
        String avatar = SPUtils.getString(mContext, AppXml.avatar, null);
        GlideUtils.intoD(mContext,avatar,civ_personinfo, R.drawable.default_person);

        String name = SPUtils.getString(mContext, AppXml.nick_name, null);
        String mobile = SPUtils.getString(mContext, AppXml.mobile, null);
        tv_personinfo_nickname.setText(name);
        tv_personinfo_phone.setText(mobile);
    }


    @OnClick({R.id.ll_personinfo, R.id.tv_personinfo_phone, R.id.tv_personinfo_nickname})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_personinfo:
                showSelectPhotoDialog();
                break;
            case R.id.tv_personinfo_phone:
                STActivity(EditPhoneActivity.class);
                break;
            case R.id.tv_personinfo_nickname:
                Intent intent=new Intent();
                intent.putExtra(IntentParam.nickName,getSStr(tv_personinfo_nickname));
                STActivity(intent,EditNicknameActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case result_select_photo:
                    if(data!=null){
                        String photoPath = getSelectPhotoPath(data);
                        updateIMG(photoPath);
                    }
                break;
                case result_take_photo:
                    updateIMG(takePhotoImgSavePath);
                break;
            }
        }
    }

    private void updateIMG(String photoPath) {
        showLoading();
        RXStart(new MyFlowableSubscriber<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> subscriber) {
                try {
                    List<File> files = Luban.with(mContext).load(photoPath).get();
                    File file = files.get(0);
                    String imgStr = BitmapUtils.fileToString(file);
                    subscriber.onNext(imgStr);
                    subscriber.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            @Override
            public void onNext(String base64) {
                Map<String,String>map=new HashMap<String,String>();
                map.put("rnd",getRnd());
                map.put("sign",getSign(map));
                UploadImgBody body=new UploadImgBody();
                body.setFile(base64);
                NetApiRequest.uploadImg(map,body, new MyCallBack<BaseObj>(mContext) {
                    @Override
                    public void onSuccess(BaseObj obj) {
                        SPUtils.setPrefString(mContext,AppXml.avatar,obj.getImg());
                        setInfo();
                    }
                });
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                dismissLoading();
                showToastS("更改用户头像失败");
            }
        });

    }
}

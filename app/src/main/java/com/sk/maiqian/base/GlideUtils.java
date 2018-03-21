package com.sk.maiqian.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.sk.maiqian.R;

/**
 * Created by Administrator on 2018/3/21.
 */

public class GlideUtils {
    public static void into(Context mContext, ImageView imageView){
        Glide.with(mContext).load(mContext).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(R.color.c_press).into(imageView);
    }
    public static void into(Context mContext, ImageView imageView,@ColorRes int color){
        Glide.with(mContext).load(mContext).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(color).into(imageView);
    }
    public static void intoD(Context mContext, ImageView imageView,@DrawableRes int drawable){
        Glide.with(mContext).load(mContext).asBitmap().encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG,90)).error(drawable).into(imageView);
    }
}

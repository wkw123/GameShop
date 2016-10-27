package com.example.administrator.gameshop;

import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.picasso.Transformation;

/**
 * Created by wkw on 2016/10/25.
 */

public class CropSquareTrans implements Transformation{
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size)/2;
        int y = (source.getHeight()-size)/2;
        Log.i("mains",source.getHeight() + "");
        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, 280, 280);
//        if(bitmap != source){
//            source.recycle();
//        }
        return bitmap;
    }

    @Override
    public String key() {
        return "square";
    }
}

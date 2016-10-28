package com.example.administrator.gameshop;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import okhttp3.OkHttpClient;

import static android.media.ThumbnailUtils.extractThumbnail;

/**
 * Created by Administrator on 2016/10/27.
 */

public class Detail_Acitivity extends AppCompatActivity{
    private ImageView mImageView;
    private TextView tv_name;
    private TextView tv_price;
    private TextView tv_describe;
    private Picasso mPicasso;
    private Game mGame;
    private OkHttpClient mClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        initView();
        mClient = new OkHttpClient();
        mGame = (Game) getIntent().getParcelableExtra("game");

        tv_name.setText(mGame.getName());
        tv_price.setText(mGame.getPrice() + "");
        setImage();
    }
    private void setImage(){
        mPicasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(mClient)).build();
        String url = "http://192.169.60.210:8080/game_shop/gameimages/" + mGame.getPicture_1()+".jpg";

        if(mImageView == null){
            Log.i("mains", "gameImage is null");
        }
        if(mPicasso == null){
            Log.i("mains", "mPicasso is null");
        }
        if(url == null){
            Log.i("mains", "url is null");
        }
        mPicasso.load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mImageView.setImageBitmap(extractThumbnail(bitmap, 500,  500));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
    private void initView(){
        mImageView = (ImageView) findViewById(R.id.iv_detail);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_describe = (TextView) findViewById(R.id.tv_descibe);
    }
}

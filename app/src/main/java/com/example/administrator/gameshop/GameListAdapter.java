package com.example.administrator.gameshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import static android.media.ThumbnailUtils.extractThumbnail;

/**
 * Created by Administrator on 2016/10/24.
 */

public class GameListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Game> gameList;
    Context context;
    LayoutInflater inflater;
    private Picasso mPicasso;

    public GameListAdapter(List<Game> gameList, Context context, Picasso Picasso) {
        this.gameList = gameList;
        this.context = context;
        mPicasso = Picasso;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("mains", gameList.size() + "");
        Game game = gameList.get(position);
        holder.getGameName().setText(game.getName());
        holder.getGamePrice().setText(game.getPrice() + "");
        String url = "http://192.169.60.219:8080/game_shop/gameimages/" + game.getPicture_1()+".jpg";
        final ImageView gameImage = holder.getGameImage();
        if(gameImage == null){
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
                gameImage.setImageBitmap(extractThumbnail(bitmap, 500,  500));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder  {
    private ImageView gameImage;
    private TextView gameName;
    private TextView gamePrice;
//    private MyItemClickListener mListener;
//    private MyItemLongClickListener mLongClickListener;
    public MyViewHolder(View itemView) {
        super(itemView);
        gameImage = (ImageView) itemView.findViewById(R.id.game_image);
        gameName = (TextView) itemView.findViewById(R.id.game_name);
        gamePrice = (TextView) itemView.findViewById(R.id.game_price);
    }

    public ImageView getGameImage() {
        return gameImage;
    }

    public void setGameImage(ImageView gameImage) {
        this.gameImage = gameImage;
    }

    public TextView getGameName() {
        return gameName;
    }

    public void setGameName(TextView gameName) {
        this.gameName = gameName;
    }

    public TextView getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(TextView gamePrice) {
        this.gamePrice = gamePrice;
    }

//    @Override
//    public void onClick(View v) {
//        if(mLongClickListener != null){
//            mLongClickListener.onItemLongClick(arg0, getPosition());
//        }
//        return true;
//    }


}
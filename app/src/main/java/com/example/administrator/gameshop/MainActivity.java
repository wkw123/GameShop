package com.example.administrator.gameshop;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import RecycleViewOnclick.RecyclerViewClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private TextView myTextView;
    private Toolbar mToolbar;
    private String url = "http://192.169.60.210:8080";
    private OkHttpClient mClient;
    private Picasso mPicasso;
    EditText tv_search_str;
    ImageView ivButton;
    private List<Game> mGameList = new ArrayList<>();
    private GameListAdapter adapter;
    float y_dowm = 0;
    float y_Up = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new OkHttpClient();
        tv_search_str = (EditText) findViewById(R.id.search_str);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setVisibility(View.GONE);
        mPicasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(mClient)).build();
//        Log.i("mains", "1 " + mGameList.size() + "");
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        adapter = new GameListAdapter(mGameList, this, mPicasso);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Game game = mGameList.get(position);
                Log.i("mainss", "gamess " + game + "");
                Intent intent = new Intent(MainActivity.this, Detail_Acitivity.class);
                intent.putExtra("game",game);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

        ivButton = (ImageView) findViewById(R.id.search);
        ivButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int size = mGameList.size();
                mGameList.clear();
                adapter.notifyItemRangeRemoved(0, size);
                page = 0;
                totalpage = Integer.MAX_VALUE;
                sendCloth();
            }
        });
        sendCloth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("mains", "y_dowm");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            y_dowm = event.getY();
            Log.i("mains", "y_dowm" + y_dowm);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            y_Up = event.getY();
            Log.i("mains", "y_Up" + y_Up);
        }
        if (y_Up - y_dowm > 0) {
            Log.i("mains", "shang");
            mToolbar.setVisibility(View.VISIBLE);
        } else {
            mToolbar.setVisibility(View.GONE);
            Log.i("mains", "xia");
        }


        return  false;

    }

    static int page = 0;
    static int totalpage = Integer.MAX_VALUE;

    public void sendCloth() {
        // should be a singleton
        //v.setEnabled(false);View v
        if (page >= totalpage) return;
        page++;

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("page", String.valueOf(page));
        builder.add("conditions", tv_search_str.getText().toString());
        RequestBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url + "/game_shop/jsontypography")
                .post(formBody)
                .build();


        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }


                final Gson gson = new Gson();
                final String responseData = response.body().string();

                Type type = new TypeToken<ArrayList<Game>>() {
                }.getType();
                final List<Game> gameList = gson.fromJson(responseData, type);
//                Log.i("mains", "2sendCloth" + gameList.size() + "");

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int pos = mGameList.size();
                            mGameList.addAll(pos, gameList);
                            adapter.notifyItemRangeInserted(pos, gameList.size());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }


}

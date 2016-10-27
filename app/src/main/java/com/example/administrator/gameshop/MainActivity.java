package com.example.administrator.gameshop;

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
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView myTextView;
    private Toolbar mToolbar;
    private String url = "http://192.169.60.219:8080";
    private OkHttpClient mClient;
    private Picasso mPicasso;
    EditText tv_search_str;
    ImageView ivButton;
    private List<Game> mGameList = new ArrayList<>();
    private GameListAdapter adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClient = new OkHttpClient();
        tv_search_str = (EditText) findViewById(R.id.search_str);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mPicasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(mClient)).build();
        Log.i("mains", "1 "+mGameList.size() + "");
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        adapter = new GameListAdapter(mGameList,this,mPicasso);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        ivButton = (ImageView) findViewById(R.id.search);
        ivButton.setOnClickListener(new View.OnClickListener(){

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
    float x_dowm = 0;
    float x_Up = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x_dowm = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x_Up = event.getX();
                if(x_Up - x_dowm > 0){
                    Log.i("mainns", "shang");
                    mToolbar.setVisibility(View.GONE);
                }else{
                    mToolbar.setVisibility(View.VISIBLE);
                    Log.i("mainns", "xia");
            }
                break;
        }

        return super.onTouchEvent(event);

    }

    static int page = 0;
    static int totalpage = Integer.MAX_VALUE;

    public void sendCloth(){
        // should be a singleton
        //v.setEnabled(false);View v
        if(page>=totalpage) return;
        page++;

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("page", String.valueOf(page));
        builder.add("conditions",  tv_search_str.getText().toString() );
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

                Type type = new TypeToken<ArrayList<Game>>(){}.getType();
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

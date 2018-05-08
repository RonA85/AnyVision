package com.example.mac.anyvision;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mac.anyvision.api.ApiInterface;
import com.example.mac.anyvision.api.RetrofitClient;
import com.example.mac.anyvision.model.FeedItem;
import com.example.mac.anyvision.model.RSS;
import com.example.mac.anyvision.ui.BaseActivity;
import com.example.mac.anyvision.ui.FeedAdapter;
import com.prof.rssparser.Parser;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    //url of RSS feed
    String urlString = "http://www.androidcentral.com/feed";

    @BindView(R.id.rv_feeds)
    RecyclerView rvFeeds;
    private FeedAdapter feedAdapter;
    private List<FeedItem> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
//        feedAdapter = new FeedAdapter(this, feeds);

     //   rvFeeds.setAdapter(feedAdapter);
        syncData();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private void syncData(){
//        Parser parser = new Parser();
//        parser.execute(urlString);
//        parser.onFinish(new Parser.OnTaskCompleted() {
//
//            @Override
//            public void onTaskCompleted(ArrayList<Article> list) {
//                //what to do when the parsing is done
//                //the Array List contains all article's data. For example you can use it for your adapter.
//            }
//
//            @Override
//            public void onError() {
//                //what to do in case of error
//            }
//        });
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<RSS> call = apiInterface.getRSSFeed();
        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> call, Response<RSS> response) {
                Log.d(TAG,"code: "+response.code());
                feeds = response.body().getChannel().itemList;
                feedAdapter = new FeedAdapter(MainActivity.this, feeds);
                rvFeeds.setHasFixedSize(true);
                rvFeeds.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvFeeds.setItemAnimator(new DefaultItemAnimator());
                rvFeeds.setAdapter(feedAdapter);
            }

            @Override
            public void onFailure(Call<RSS> call, Throwable t) {
                Log.d(TAG,"Failure: "+t.getMessage());
            }
        });

    }


}

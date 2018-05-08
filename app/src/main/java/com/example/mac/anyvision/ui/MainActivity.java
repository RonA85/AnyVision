package com.example.mac.anyvision.ui;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mac.anyvision.R;
import com.example.mac.anyvision.api.ApiCalls;
import com.example.mac.anyvision.api.DataObserver;
import com.example.mac.anyvision.model.FeedItem;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv_feeds)
    RecyclerView rvFeeds;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        rvFeeds.setHasFixedSize(true);
        rvFeeds.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvFeeds.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected void onStart() {
        super.onStart();
        requestFeeds();
    }

    private void requestFeeds() {
        DataObserver<List<FeedItem>> observer = new DataObserver<List<FeedItem>>() {
            @Override
            public void onReceive(List<FeedItem> feedItems) {
                feedAdapter = new FeedAdapter(MainActivity.this, feedItems);
                rvFeeds.setAdapter(feedAdapter);
            }
        };
        ApiCalls.getAllFeeds(observer);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

}

package com.example.mac.anyvision.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mac.anyvision.model.FeedItem;
import com.example.mac.anyvision.model.RSS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {
    public static void getAllFeeds(final DataObserver<List<FeedItem>> observer){
        ApiRequest apiRequest =
                RetrofitClient.getClient().create(ApiRequest.class);
        Call<RSS> call = apiRequest.getRSSFeed();
        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(@NonNull Call<RSS> call, @NonNull Response<RSS> response) {
                    Log.d("ApiCalls", "code: " + response.code());
                    //      feeds = response.body().getChannel().itemList;
                    Log.d("ApiCalls", "url: " + response.body()
                            .getChannel()
                            .itemList.get(0)
                            .getEnclosure()
                            .getImage());
                    observer.onReceive(response.body().getChannel().itemList);
            }

            @Override
            public void onFailure(@NonNull Call<RSS> call, @NonNull Throwable t) {
                Log.d("ApiCalls", t.getMessage());
            }
        });
    }

}

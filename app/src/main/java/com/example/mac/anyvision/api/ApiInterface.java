package com.example.mac.anyvision.api;

import com.example.mac.anyvision.model.RSS;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
//    @GET("photo-of-the-day")
//    Call<RSS> getRSSFeed();

    @GET("news/rss.xml")
    Call<RSS> getRSSFeed();
}

package com.example.mac.anyvision.api;

import com.example.mac.anyvision.model.RSS;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("photo-of-the-day")
    Call<RSS> getRSSFeed();
}

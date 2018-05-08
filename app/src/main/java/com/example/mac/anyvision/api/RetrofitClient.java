package com.example.mac.anyvision.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Ron on 26/03/2018.
 */

public class RetrofitClient {
//    public static final String BASE_URL = "http://feeds.nationalgeographic.com/ng/photography/";
    public static final String BASE_URL = "http://feeds.bbci.co.uk/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                    .build();
        }
        return retrofit;
    }
}

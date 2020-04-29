package com.programmersgateway.sm1999.covid19sahayak.network.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.programmersgateway.sm1999.covid19sahayak.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RFClient {

    private static String BASE_URL = null;
    private static Retrofit retrofitInstancetwo = null;
    private static Retrofit retrofitInstancethree = null;

    public RFClient(String BASE_URL) {
    }

    private static OkHttpClient.Builder getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
            mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(mLogging);
        }
        return httpClient;
    }

    public static Retrofit getClient(String BASE_URL1) {
        BASE_URL = BASE_URL1;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final OkHttpClient client = getHttpClient().build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

    }

}
package io.zouyin.musicspike.network;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.logging.Level;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestAPI {
    private Retrofit retrofit;
    private static RestAPI instance;

    private static final String BASE_URL = "http://7xj9js.com1.z0.glb.clouddn.com";

    private RestAPI() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RestAPI getInstance() {
        if (instance == null) {
            instance = new RestAPI();
        }
        return instance;
    }

    public QiniuService getQiniuService() {
        return retrofit.create(QiniuService.class);
    }

}

package io.zouyin.musicspike.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestAPI {
    private Retrofit retrofit;
    private static RestAPI instance;

    private static final String BASE_URL = "http://7xj9js.com1.z0.glb.clouddn.com";

    private RestAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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

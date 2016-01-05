package io.zouyin.musicspike.network;

import retrofit.Callback;
import retrofit.http.GET;

public interface QiniuService {

    @GET("/users/{user}/repos")
    void listSongs(Callback callback);

}

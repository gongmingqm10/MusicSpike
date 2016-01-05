package io.zouyin.musicspike.network;

import java.util.List;

import io.zouyin.musicspike.model.Song;
import retrofit.Call;
import retrofit.http.GET;

public interface QiniuService {

    @GET("/songs.json")
    Call<List<Song>> listSongs();

}

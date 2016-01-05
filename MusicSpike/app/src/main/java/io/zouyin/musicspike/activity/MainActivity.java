package io.zouyin.musicspike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import io.zouyin.musicspike.R;
import io.zouyin.musicspike.model.Song;
import io.zouyin.musicspike.network.RestAPI;
import io.zouyin.musicspike.network.callback.BaseCallBack;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(android.R.id.list)
    ListView songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        requestSongs();
    }

    private void requestSongs() {
        RestAPI.getInstance().getQiniuService().listSongs(new BaseCallBack<List<Song>>() {
            @Override
            public void onResponse(Response<List<Song>> response, Retrofit retrofit) {

            }
        });
    }
}

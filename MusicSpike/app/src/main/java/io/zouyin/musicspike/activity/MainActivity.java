package io.zouyin.musicspike.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;
import io.zouyin.musicspike.R;
import io.zouyin.musicspike.adapter.SongListAdapter;
import io.zouyin.musicspike.model.Song;
import io.zouyin.musicspike.network.RestAPI;
import io.zouyin.musicspike.network.callback.BaseCallBack;
import io.zouyin.musicspike.view.MusicPlayerPanel;
import io.zouyin.musicspike.viewholder.SongViewHolder;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {

    @Bind(android.R.id.list)
    ListView songsList;

    @Bind(R.id.player_panel)
    View playerView;

    private MusicPlayerPanel playerPanel;

    private DownloadManager downloadManager;

    private SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        songListAdapter = new SongListAdapter(new ArrayList<Song>(), songActionListener);
        songsList.setAdapter(songListAdapter);
        requestSongs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_download) {
            Intent intent = new Intent();
            intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnItemClick(android.R.id.list)
    protected void clickItemAtPosition(int position) {
        playerPanel.play(position);
    }

    private void requestSongs() {
        Call<List<Song>> songListCall = RestAPI.getInstance().getQiniuService().listSongs();
        songListCall.enqueue(new BaseCallBack<List<Song>>() {
            @Override
            public void onResponse(Response<List<Song>> response, Retrofit retrofit) {
                songListAdapter.addAll(response.body());
                playerPanel = new MusicPlayerPanel(playerView, response.body());
            }
        });
    }

    private SongViewHolder.SongActionListener songActionListener = new SongViewHolder.SongActionListener() {
        @Override
        public void downloadSong(Song song) {
            showToast(R.string.start_download_song);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(song.getUrl()));
            downloadManager.enqueue(request);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerPanel.onDestroy();
    }
}

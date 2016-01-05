package io.zouyin.musicspike.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.zouyin.musicspike.R;
import io.zouyin.musicspike.model.Song;

public class MusicPlayerPanel {
    private final List<Song> songs;
    private Context context;

    @Bind(R.id.partial_song_image)
    ImageView songImageView;

    @Bind(R.id.partial_song_title)
    TextView songTitle;

    @Bind(R.id.partial_song_next)
    ImageView songNextBtn;

    @Bind(R.id.partial_song_play)
    ImageView songPlayBtn;

    private MediaPlayer mediaPlayer;
    private int currentPosition = 0;
    private boolean isPlaying = false;
    private boolean isPrepared = false;

    public MusicPlayerPanel(View view, List<Song> songs) {
        ButterKnife.bind(this, view);
        this.songs = songs;
        this.context = view.getContext();
        this.mediaPlayer = new MediaPlayer();

        initUI();
    }

    private void initUI() {
        if (songs == null || songs.isEmpty()) return;

        Song currentSong = songs.get(currentPosition);
        Picasso.with(context).load(currentSong.getImage()).into(songImageView);
        songTitle.setText(currentSong.getName());
        updatePlayBtn();
    }

    public void play(int position) {
        isPlaying = true;
        isPrepared = false;
        currentPosition = position;
        initUI();

        try {
            //TODO should reuse the downloaded files.
            Song song = songs.get(currentPosition);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context, Uri.parse(song.getUrl()));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    isPrepared = true;
                }
            });
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            isPlaying = false;
            updatePlayBtn();
        }
    }

    @OnClick(R.id.partial_song_play)
    protected void startOrPauseSong() {
        if (isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        } else if (isPrepared) {
            mediaPlayer.start();
            isPlaying = true;
        } else {
            play(currentPosition);
        }
        updatePlayBtn();
    }

    private void updatePlayBtn() {
        songPlayBtn.setImageResource(isPlaying ? R.mipmap.playbar_btn_pause : R.mipmap.playbar_btn_play);
    }

    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}

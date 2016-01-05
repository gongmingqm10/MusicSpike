package io.zouyin.musicspike.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import io.zouyin.musicspike.R;
import io.zouyin.musicspike.model.Song;

public class SongViewHolder extends BaseViewHolder<Song> {

    @Bind(R.id.song_name)
    TextView songNameText;

    @Bind(R.id.song_image)
    ImageView songImageView;

    @Bind(R.id.song_download_btn)
    ImageView downloadBtn;

    @Bind(R.id.song_play_btn)
    ImageView playBtn;

    private SongActionListener listener;

    public SongViewHolder(View view, SongActionListener listener) {
        super(view);
        this.listener = listener;
    }

    @Override
    public void populate(final Song data) {

        songNameText.setText(data.getName());
        Picasso.with(context).load(data.getImage()).into(songImageView);

        bindActions(data);
    }

    private void bindActions(final Song data) {
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.downloadSong(data);
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.playSong(data);
                }
            }
        });
    }

    public interface SongActionListener {
        void downloadSong(Song song);
        void playSong(Song song);
    }
}

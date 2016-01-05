package io.zouyin.musicspike.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import io.zouyin.musicspike.R;
import io.zouyin.musicspike.model.Song;
import io.zouyin.musicspike.viewholder.SongViewHolder;

public class SongListAdapter extends BaseAdapter {

    private List<Song> data;
    private SongViewHolder.SongActionListener listener;

    public SongListAdapter(List<Song> data, SongViewHolder.SongActionListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public void addAll(List<Song> songs) {
        data.clear();
        data.addAll(songs);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Song getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
            viewHolder = new SongViewHolder(convertView, listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SongViewHolder) convertView.getTag();
        }
        viewHolder.populate(getItem(position));

        return convertView;
    }
}

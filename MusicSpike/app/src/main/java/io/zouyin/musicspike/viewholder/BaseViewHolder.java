package io.zouyin.musicspike.viewholder;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> {
    protected Context context;

    public BaseViewHolder(View view) {
        this.context = view.getContext();
        ButterKnife.bind(this, view);
    }

    public abstract void populate(T data);

}

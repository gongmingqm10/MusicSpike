package io.zouyin.musicspike.network.callback;

import retrofit.Callback;

public abstract class BaseCallBack<T> implements Callback<T> {
    @Override
    public void onFailure(Throwable t) {

    }
}

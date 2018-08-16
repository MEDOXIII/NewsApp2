package com.example.android.newsapp;

import android.content.Context;

import android.util.Log;
import java.util.List;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.AsyncTaskLoader;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public static final String LOG_TAG = NewsLoader.class.getSimpleName();

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");
        if (mUrl == null) {
            return null;
        }

        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}

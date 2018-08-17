package com.example.android.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {


    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
    }


    public static List<News> fetchNewsData(String requestUrl) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        URL url = createUrl(requestUrl);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }


        List<News> newsList = extractFeatureFromJson(jsonResponse);


        return newsList;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";


        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static List<News> extractFeatureFromJson(String newsJSON) {

        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }


        List<News> newsList = new ArrayList<>();

        try {


            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            JSONObject res = baseJsonResponse.getJSONObject("response");

            JSONArray newsArray = res.getJSONArray("results");


            for (int i = 0; i < newsArray.length(); i++) {


                JSONObject currentNews = newsArray.getJSONObject(i);


                String title = currentNews.getString("webTitle");


                String section = currentNews.getString("sectionName");


                String date = currentNews.getString("webPublicationDate");


                String url = currentNews.getString("webUrl");


                JSONArray tagsArray = currentNews.getJSONArray("tags");

                String author = null;

                if (tagsArray.length() == 1) {
                    JSONObject contributorTag = (JSONObject) tagsArray.get(0);
                    author = contributorTag.getString("webTitle");
                }

                News news = new News(title, section, date, url, author);


                newsList.add(news);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }


        return newsList;
    }


    public static boolean isConnected(Context context) {
        boolean connected = false;

        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            connected = true;
        }
        return connected;
    }


}

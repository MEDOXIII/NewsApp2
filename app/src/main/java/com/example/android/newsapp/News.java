package com.example.android.newsapp;

public class News {

    private String mtitle;
    private String msection;
    private String mdate;
    private String mUrl;
    private String mauthor;

    public News(String title, String section, String date, String url, String author) {


        mtitle = title;
        msection = section;
        mdate = date;
        mUrl = url;
        mauthor = author;

    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMsection() {
        return msection;
    }

    public void setMsection(String msection) {
        this.msection = msection;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getMauthor() {
        return mauthor;
    }

    public void setMauthor(String mauthor) {
        this.mauthor = mauthor;
    }
}

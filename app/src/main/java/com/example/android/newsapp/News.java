package com.example.android.newsapp;

public class News {

    private String mtitle;
    private String msection;
    private Long mdate;
    private String mUrl;

    public News(String title, String section, Long date, String url) {


        mtitle = title;
        msection = section;
        mdate = date;
        mUrl = url;

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

    public Long getMdate() {
        return mdate;
    }

    public void setMdate(Long mdate) {
        this.mdate = mdate;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}

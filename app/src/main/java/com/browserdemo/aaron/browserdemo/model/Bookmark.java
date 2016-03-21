package com.browserdemo.aaron.browserdemo.model;

/**
 * Created by Aaronke on 3/18/2016.
 */
public class Bookmark {

    private String url;
    private String bookmarkTitle;
    private String bookmarkFaviconUrl;

    public String getBookmarkTitle() {
        return bookmarkTitle;
    }

    public String getBookmarkFaviconUrl() {
        return bookmarkFaviconUrl;
    }

    public void setBookmarkTitle(String bookmarkTitle) {
        this.bookmarkTitle = bookmarkTitle;
    }

    public void setBookmarkFaviconUrl(String bookmarkFaviconUrl) {
        this.bookmarkFaviconUrl = bookmarkFaviconUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

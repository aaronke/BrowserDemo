package com.browserdemo.aaron.browserdemo.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Aaronke on 3/18/2016.
 */
public class Bookmark extends RealmObject{
    @PrimaryKey
    private String url;
    @Required
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

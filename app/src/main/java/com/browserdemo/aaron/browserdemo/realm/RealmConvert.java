package com.browserdemo.aaron.browserdemo.realm;

import com.browserdemo.aaron.browserdemo.model.Bookmark;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Aaronke on 3/20/2016.
 */
public class RealmConvert {

    public static ArrayList<Bookmark> convertRBookmarkToBookmark(RealmResults<RBookmark> list){
        ArrayList<Bookmark> bookmarks=new ArrayList<>();
        for (RBookmark rBookmark:list){
            Bookmark bookmark=new Bookmark();
            bookmark.setBookmarkTitle(rBookmark.getTitle());
            bookmark.setUrl(rBookmark.getUrl());
            bookmark.setBookmarkFaviconUrl(rBookmark.getIconUrl());
            bookmarks.add(bookmark);
        }
        return  bookmarks;
    }
}

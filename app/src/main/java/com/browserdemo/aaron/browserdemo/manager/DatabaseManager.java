package com.browserdemo.aaron.browserdemo.manager;

import android.content.Context;

import com.browserdemo.aaron.browserdemo.model.Bookmark;
import com.browserdemo.aaron.browserdemo.realm.RBookmark;
import com.browserdemo.aaron.browserdemo.realm.RealmConvert;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Aaronke on 3/20/2016.
 */
public class DatabaseManager {
    private Context context;
    private static DatabaseManager ourInstance=new DatabaseManager();
    public static DatabaseManager getOurInstance(){
        return ourInstance;
    }

    public void init(Context context){
        this.context=context;
    }

    public ArrayList<Bookmark> getAllBookmarks(){
        ArrayList<Bookmark> mBookmarkList=new ArrayList<>();
        Realm realm=Realm.getInstance(context);
        RealmResults<RBookmark> realmResults=realm.where(RBookmark.class).findAll();
        mBookmarkList= RealmConvert.convertRBookmarkToBookmark(realmResults);
        realm.close();
        return  mBookmarkList;
    }

    public void addABookmark(Bookmark bookmark){
        Realm realm=Realm.getInstance(context);
        realm.beginTransaction();
        RBookmark rBookmark=realm.createObject(RBookmark.class);
        rBookmark.setUrl(bookmark.getUrl());
        rBookmark.setIconUrl(bookmark.getBookmarkFaviconUrl());
        rBookmark.setTitle(bookmark.getBookmarkTitle());
        realm.commitTransaction();
        realm.close();

    }
    public void removeBookmark(Bookmark bookmark){
        Realm realm=Realm.getInstance(context);

        // better to store all database columns in another class
        RealmResults<RBookmark> realmResults=realm.where(RBookmark.class).equalTo("url",bookmark.getUrl()).findAll();
        realm.beginTransaction();
        realmResults.clear();
        realm.commitTransaction();
        realm.close();
    }
}

package com.browserdemo.aaron.browserdemo.manager;

import android.content.Context;

import com.browserdemo.aaron.browserdemo.model.Bookmark;

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
        RealmResults<Bookmark> realmResults=realm.where(Bookmark.class).findAll();
        for(int i=0;realmResults!=null&&i<realmResults.size();i++){
            mBookmarkList.add(realmResults.get(i));
        }
        //realm.close();
        return  mBookmarkList;
    }

    public void addABookmark(Bookmark bookmark){
        Realm realm=Realm.getInstance(context);
        realm.beginTransaction();
        realm.copyToRealm(bookmark);
        realm.commitTransaction();

    }
    public void removeBookmark(Bookmark bookmark){
            bookmark.removeFromRealm();
    }
}

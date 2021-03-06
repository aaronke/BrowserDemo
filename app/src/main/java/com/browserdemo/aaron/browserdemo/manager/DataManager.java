package com.browserdemo.aaron.browserdemo.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.browserdemo.aaron.browserdemo.model.Bookmark;

/**
 * Created by Aaronke on 3/18/2016.
 */
public class DataManager {

    private static String TAG=DataManager.class.getSimpleName();

    private static DataManager ourInstance=new DataManager();
    private Context context;
    private Bookmark mBookmark;

    //preferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String DATA_MANAGER="DATA_MANAGER";
    private static final String FIRST_RUN="FIRST_RUN";

    public static DataManager getOurInstance(){return ourInstance;}
    private DataManager(){}

    public void init(Context context){
        this.context=context;

        sharedPreferences=context.getSharedPreferences(DATA_MANAGER,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Log.v(TAG,"init finished");

    }

    public boolean checkFirstRun(){
        return sharedPreferences.getBoolean(FIRST_RUN,true);
    }
    public void setFirstRun(boolean flag){
        editor.putBoolean(FIRST_RUN,flag);
        editor.apply();
    }

    public void setBookmark(String title,String iconUrl,String url){
        mBookmark=new Bookmark();
        mBookmark.setBookmarkTitle(title);
        mBookmark.setBookmarkFaviconUrl(iconUrl);
        mBookmark.setUrl(url);
    }

    public Bookmark getBookmark(){
        return mBookmark!=null?mBookmark:null;
    }
}

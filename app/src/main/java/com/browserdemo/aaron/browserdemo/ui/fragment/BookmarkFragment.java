package com.browserdemo.aaron.browserdemo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.adapter.BookmarkGridViewAdapter;
import com.browserdemo.aaron.browserdemo.manager.DataManager;
import com.browserdemo.aaron.browserdemo.manager.DatabaseManager;
import com.browserdemo.aaron.browserdemo.model.Bookmark;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BookmarkFragment extends Fragment {

    @Bind(R.id.bookmark_grid)
    GridView mBookmarkGridView;

    private OnBookmarkFragmentInteractionListener mListener;
    private Context context;
    private BookmarkGridViewAdapter mBookmarkGridViewAdapter;
    private ArrayList<Bookmark> mBookmarkList;

    public static BookmarkFragment newInstance() {
        BookmarkFragment fragment = new BookmarkFragment();
        return fragment;
    }

    public BookmarkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBookmarkGridViewAdapter!=null) mBookmarkGridViewAdapter.notifyDataSetChanged();
    }

    private void init(){
        dataInit();
        mBookmarkGridViewAdapter=new BookmarkGridViewAdapter(mBookmarkList,context);
        mBookmarkGridView.setAdapter(mBookmarkGridViewAdapter);
    }

    // only for demo,
    private void dataInit(){
        if(DataManager.getOurInstance().checkFirstRun()){

            // initialized some bookmarks to the database;
            Bookmark bookmark1=new Bookmark();
            bookmark1.setBookmarkTitle("Facebook");
            bookmark1.setBookmarkFaviconUrl("https://www.facebook.com/favicon.ico");
            bookmark1.setUrl("http://www.facebook.com");
            Bookmark bookmark2=new Bookmark();
            bookmark2.setBookmarkTitle("Twitter");
            bookmark2.setBookmarkFaviconUrl("https://twitter.com/favicon.ico");
            bookmark2.setUrl("https://twitter.com");
            DatabaseManager.getOurInstance().addABookmark(bookmark1);
            DatabaseManager.getOurInstance().addABookmark(bookmark2);

            DataManager.getOurInstance().setFirstRun(false);
        }else {
            // do nothing here
        }
        // get the initialized bookmarks
        mBookmarkList= DatabaseManager.getOurInstance().getAllBookmarks();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onBookmarkFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context=activity;
        try {
            mListener = (OnBookmarkFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnBookmarkFragmentInteractionListener {
        // TODO: Update argument type and name
         void onBookmarkFragmentInteraction(Uri uri);
    }
}

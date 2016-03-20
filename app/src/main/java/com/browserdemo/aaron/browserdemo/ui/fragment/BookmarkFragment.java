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

    private void init(){
        mBookmarkList=new ArrayList<>();
        Bookmark bookmark=new Bookmark();
        bookmark.setBookmarkTitle("Google");
        bookmark.setBookmarkFaviconUrl("http://www.google.com/favicon.ico");
        mBookmarkList.add(bookmark);
        mBookmarkGridViewAdapter=new BookmarkGridViewAdapter(mBookmarkList,context);
        mBookmarkGridView.setAdapter(mBookmarkGridViewAdapter);
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

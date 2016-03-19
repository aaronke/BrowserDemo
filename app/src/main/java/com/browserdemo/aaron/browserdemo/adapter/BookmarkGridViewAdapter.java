package com.browserdemo.aaron.browserdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.model.Bookmark;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Aaronke on 3/18/2016.
 */
public class BookmarkGridViewAdapter extends BaseAdapter {

    private static LayoutInflater inflater=null;

    private ArrayList<Bookmark> bookmarks;
    private Context context;
    public BookmarkGridViewAdapter(ArrayList<Bookmark> bookmarks,Context context){
        this.context=context;
        this.bookmarks=bookmarks;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookmarks!=null?bookmarks.size():0;
    }

    @Override
    public Object getItem(int position) {
        return bookmarks!=null?bookmarks.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.gridview_item,null);
            viewHolder=new ViewHolder();
            viewHolder.mFaviconImageView=(ImageView)convertView.findViewById(R.id.item_favicon_img);
            viewHolder.mTitleTextView=(TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        Picasso.with(context)
                .load(Uri.parse(bookmarks.get(position).getBookmarkFaviconUrl()))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.mFaviconImageView);
        viewHolder.mTitleTextView.setText(bookmarks.get(position).getBookmarkTitle());
        return convertView;
    }
    private static  class ViewHolder{
        ImageView mFaviconImageView;
        TextView mTitleTextView;
    }
}

package com.browserdemo.aaron.browserdemo.ui;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.browserdemo.aaron.browserdemo.manager.DataManager;
import com.browserdemo.aaron.browserdemo.model.Bookmark;

/**
 * Created by Aaronke on 3/17/2016.
 */
public class MyAppWebViewClient extends WebViewClient {
    private static final String TAG=MyAppWebViewClient.class.getSimpleName();
    public MyAppWebViewClient() {
        super();
    }

    // do the customized config here for your own browser
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //view.getUrl();
        if (view!=null){
            view.getSettings().setBlockNetworkImage(false);
            view.getSettings().setJavaScriptEnabled(true);
            Log.v(TAG, view.getTitle()+"");
            // set icon url,title, url settings only works for some websites
            DataManager.getOurInstance().setBookmark(view.getTitle(),view.getOriginalUrl()+"favicon.ico"
                    ,view.getOriginalUrl());
        }
        super.onPageFinished(view, url);

    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);

    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        super.onPageCommitVisible(view, url);
    }

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    @Override
    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
        super.onReceivedLoginRequest(view, realm, account, args);
    }

}

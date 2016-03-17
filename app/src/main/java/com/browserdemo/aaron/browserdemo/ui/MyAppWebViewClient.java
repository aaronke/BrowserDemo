package com.browserdemo.aaron.browserdemo.ui;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Aaronke on 3/17/2016.
 */
public class MyAppWebViewClient extends WebViewClient {
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
        Bitmap favicon=view.getFavicon();
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

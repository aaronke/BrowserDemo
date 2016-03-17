package com.browserdemo.aaron.browserdemo.ui;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Aaronke on 3/17/2016.
 */
public class MyAppWebChromeClient extends WebChromeClient {
    public MyAppWebChromeClient() {
        super();
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
    }
}

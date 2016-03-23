package com.browserdemo.aaron.browserdemo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebIconDatabase;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.ui.MyAppWebChromeClient;
import com.browserdemo.aaron.browserdemo.ui.MyAppWebViewClient;
import com.browserdemo.aaron.browserdemo.helper.StorageHelper;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WebViewFragment extends Fragment {
    private static final String TAG=WebViewFragment.class.getSimpleName();
    @Bind(R.id.webView)
    WebView mWebView;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private Context context;
    private String url;

    private OnWebViewFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        fragment.setArguments(args);
        return fragment;
    }

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    private void init(){
        //WebIconDatabase.getInstance().open(StorageHelper.getDirs(context.getCacheDir().getAbsolutePath() + "/icons/"));

        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        //webSettings.setLoadWithOverviewMode(true);
        //webSettings.setUseWideViewPort(true);
        /*mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT>=11){
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }*/
        mWebView.setWebViewClient(new MyAppWebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.v(TAG, newProgress + "");

                onProgressBarUpdate(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.v(TAG, title);

                onUpdateBarUI(view.getUrl(), view.getOriginalUrl());
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.v(TAG,icon.toString());
            }
        });
        goToWebsite(url);
    }

    public boolean goBack(){
        boolean goBack= mWebView.canGoBack();
        mWebView.goBack();

        return goBack;
    }
    public void setWebViewUrl(String url){
        goToWebsite(url);
    }
    private void goToWebsite(String url){

        // add head to url if missing
        if (!url.startsWith("www.")&&!url.startsWith("http://")&&!url.startsWith("https://")){
            url="www."+url;
        }
        if (!url.startsWith("http://")&&!url.startsWith("https://")){
            url="http://"+url;
        }
        if(mWebView!=null) {
            try {
                mWebView.clearFormData();
                mWebView.clearHistory();
                mWebView.clearCache(true);
                mWebView.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onUpdateBarUI(String uri,String originalUrl) {
        if (mListener != null) {
            mListener.onWebViewFragmentInteraction(uri,originalUrl);
        }
    }
    public void onProgressBarUpdate(int progress){
        if (mListener!=null)mListener.updateProgressBar(progress);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
        try {
            mListener = (OnWebViewFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null) mWebView.destroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.stopLoading();
            mWebView.onPause();
            mWebView.pauseTimers();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnWebViewFragmentInteractionListener {
        // TODO: Update argument type and name
         void onWebViewFragmentInteraction(String uri,String originalUrl);
         void updateProgressBar(int progress);
    }

}

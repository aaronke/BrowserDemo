package com.browserdemo.aaron.browserdemo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebIconDatabase;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.ui.MyAppWebChromeClient;
import com.browserdemo.aaron.browserdemo.ui.MyAppWebViewClient;
import com.browserdemo.aaron.browserdemo.util.StorageHelper;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WebViewFragment extends Fragment {

    @Bind(R.id.webView)
    WebView mWebView;
    // TODO: Rename parameter arguments, choose names that match
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
        WebIconDatabase.getInstance().open(StorageHelper.getDirs(context.getCacheDir().getAbsolutePath() + "/icons/"));

        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyAppWebViewClient());
        mWebView.setWebChromeClient(new MyAppWebChromeClient());

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
        if(mWebView!=null)mWebView.loadUrl(url);
/*      Bitmap bitmap=mWebView.getFavicon();
        mFaviconView.setVisibility(View.VISIBLE);
        mFaviconView.setImageBitmap(bitmap);*/
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        public void onFragmentInteraction(Uri uri);
    }

}

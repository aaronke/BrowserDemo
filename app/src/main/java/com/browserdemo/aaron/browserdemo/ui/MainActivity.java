package com.browserdemo.aaron.browserdemo.ui;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebIconDatabase;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.util.StorageHanlder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.webView)
    WebView mWebView;
    @Bind(R.id.address_bar)
    EditText mAddressBar;
    @Bind(R.id.web_favicon)
    ImageView mFaviconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)actionBar.hide();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        WebIconDatabase.getInstance().open(StorageHanlder.getDirs(getCacheDir().getAbsolutePath()+"/icons/"));

        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyAppWebViewClient());
        mWebView.setWebChromeClient(new MyAppWebChromeClient());
    }

    private void goToWebsite(String url){

        // add head to url if missing
        if (!url.startsWith("www.")&&!url.startsWith("http://")&&!url.startsWith("https://")){
            url="www."+url;
        }
        if (!url.startsWith("http://")&&!url.startsWith("https://")){
            url="http://"+url;
        }
        mWebView.loadUrl(url);
/*      Bitmap bitmap=mWebView.getFavicon();
        mFaviconView.setVisibility(View.VISIBLE);
        mFaviconView.setImageBitmap(bitmap);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) mWebView.goBack();
        else super.onBackPressed();
    }

    @OnEditorAction(R.id.address_bar)
    public boolean onEditorAction(int actionId,KeyEvent key){
        if (actionId== EditorInfo.IME_ACTION_SEARCH){
            goToWebsite(mAddressBar.getText().toString());
            return true;
        }
        return false;
    }

}

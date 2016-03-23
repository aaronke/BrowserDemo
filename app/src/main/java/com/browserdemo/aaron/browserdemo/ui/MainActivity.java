package com.browserdemo.aaron.browserdemo.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.manager.DataManager;
import com.browserdemo.aaron.browserdemo.manager.DatabaseManager;
import com.browserdemo.aaron.browserdemo.model.Bookmark;
import com.browserdemo.aaron.browserdemo.ui.fragment.BookmarkFragment;
import com.browserdemo.aaron.browserdemo.ui.fragment.WebViewFragment;
import com.browserdemo.aaron.browserdemo.util.IdTagUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity implements WebViewFragment.OnWebViewFragmentInteractionListener,
        BookmarkFragment.OnBookmarkFragmentInteractionListener {

    private static String TAG= MainActivity.class.getSimpleName();
    @Bind(R.id.address_bar)
    EditText mAddressBar;
    @Bind(R.id.web_favicon)
    ImageView mFaviconView;
    @Bind(R.id.favor_checkbox)
    CheckBox mFavorCheckBox;
    @Bind(R.id.progressbar)
    ProgressBar mProgressBar;

    private FragmentManager fragmentManager;
    private WebViewFragment webViewFragment;
    private BookmarkFragment bookmarkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        bookmarkFragment = BookmarkFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.container, bookmarkFragment).commit();

        mProgressBar.setMax(100);
    }


   /* @Override
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
*/
    @Override
    public void onBackPressed() {
        Fragment webView = fragmentManager.findFragmentByTag(IdTagUtil.WEBVIEW_FRAGMENT_TAG);
        if (webView instanceof WebViewFragment) {
            boolean goBack = ((WebViewFragment) webView).goBack();
            if (!goBack) {
                super.onBackPressed();
                initUI();
            }
        }else super.onBackPressed();
    }
    private void initUI(){
        mFavorCheckBox.setVisibility(View.GONE);
        mAddressBar.setText("");
    }
    @OnEditorAction(R.id.address_bar)
    public boolean onEditorAction(int actionId, KeyEvent key) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            renderWebContent(mAddressBar.getText().toString());
            return true;
        }
        return false;
    }

    private void renderWebContent(String url){
        if (webViewFragment != null && webViewFragment.isAdded()) {
            webViewFragment.setWebViewUrl(url);
        } else {
            webViewFragment = WebViewFragment.newInstance(url);
            fragmentManager.beginTransaction().add(R.id.container, webViewFragment, IdTagUtil.WEBVIEW_FRAGMENT_TAG)
                    .addToBackStack(null).commit();
        }
    }

    @OnCheckedChanged(R.id.favor_checkbox)
    public void favorCurrentSite(boolean checked){

        // set current website to be a bookmark, update database, UI
        Bookmark tempBookmark=DataManager.getOurInstance().getBookmark();
        if (checked){
            if (tempBookmark!=null){
                DatabaseManager.getOurInstance().addABookmark(tempBookmark);
            }
        }else{
            if (tempBookmark!=null){
                DatabaseManager.getOurInstance().removeBookmark(tempBookmark);
            }
        }
        if (bookmarkFragment!=null){
            bookmarkFragment.updateUI();
        }
    }
    // webView fragment callback
    @Override
    public void onWebViewFragmentInteraction(String url,String originalUrl) {
        if (DatabaseManager.getOurInstance().checkIfExist(originalUrl))
            mFavorCheckBox.setChecked(true);
        mAddressBar.setText(url);
    }

    @Override
    public void updateProgressBar(int progress) {
        if (progress==100) {

            mFavorCheckBox.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }else {
            if (mProgressBar.getVisibility() == View.INVISIBLE)
                mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(progress);
        }
    }

    // bookmark fragment callback
    @Override
    public void onBookmarkFragmentInteraction(String uri) {
        // render the content of the url
        renderWebContent(uri);
    }
}

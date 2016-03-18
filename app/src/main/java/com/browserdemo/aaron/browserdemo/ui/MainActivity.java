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
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.browserdemo.aaron.browserdemo.R;
import com.browserdemo.aaron.browserdemo.ui.fragment.WebViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;

public class MainActivity extends AppCompatActivity implements WebViewFragment.OnWebViewFragmentInteractionListener{


    @Bind(R.id.address_bar)
    EditText mAddressBar;
    @Bind(R.id.web_favicon)
    ImageView mFaviconView;

    private FragmentManager fragmentManager;
    private WebViewFragment webViewFragment;
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
        fragmentManager=getSupportFragmentManager();
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
        Fragment webView=fragmentManager.findFragmentByTag("webview");
        if (webView instanceof WebViewFragment){
            boolean goBack=((WebViewFragment)webView).goBack();
            if (!goBack) super.onBackPressed();
        }

    }

    @OnEditorAction(R.id.address_bar)
    public boolean onEditorAction(int actionId,KeyEvent key){
        if (actionId== EditorInfo.IME_ACTION_SEARCH){
            String url=mAddressBar.getText().toString();
            if (webViewFragment!=null && webViewFragment.isAdded()){
                webViewFragment.setWebViewUrl(url);
            }else{
                webViewFragment=WebViewFragment.newInstance(url);
                fragmentManager.beginTransaction().add(R.id.container,webViewFragment,"webview").commit();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

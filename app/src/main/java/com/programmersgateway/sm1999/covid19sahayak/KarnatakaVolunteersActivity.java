package com.programmersgateway.sm1999.covid19sahayak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KarnatakaVolunteersActivity extends AppCompatActivity {

    private WebView mWebView;
    SharedPrefs sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        sharedPrefs = new SharedPrefs(this);
        if(sharedPrefs.loadNightModeState())
        {
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPrimary)));
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.darkPrimaryDark));
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_karnataka_volunteers);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView = (WebView) findViewById(R.id.karnatakavolunteersWebView);
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        final String url = "https://covid19.karnataka.gov.in/coronawarrior.html/";
        mWebView.loadUrl(url);
        // needed in order to support javascript alerts and other javascript operations

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                KarnatakaVolunteersActivity.this.setTitle("Become to Corona Warrior");
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.webview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.shareWebview:
                shareData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void shareData(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mWebView.getUrl());
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

}

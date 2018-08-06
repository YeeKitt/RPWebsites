package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWebViewActivity extends AppCompatActivity {

    WebView wvMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_view);

        wvMyPage = findViewById(R.id.webViewMyPage);

        wvMyPage.setWebViewClient(new WebViewClient());

        // Enable JavaScript
        //wvMyPage.getSettings().setJavaScriptEnabled(true);

        // Enable built-in zoom control
        wvMyPage.getSettings().setDisplayZoomControls(true);

        // Get intent
        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        wvMyPage.loadUrl(url);
    }
}

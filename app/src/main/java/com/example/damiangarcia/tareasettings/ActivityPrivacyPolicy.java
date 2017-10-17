package com.example.damiangarcia.tareasettings;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ActivityPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        WebView mWebView = (WebView) findViewById(R.id.activity_privacy_policy_webView);

        mWebView.loadUrl("file:///android_asset/PrivacyPolicy.html");
    }
}

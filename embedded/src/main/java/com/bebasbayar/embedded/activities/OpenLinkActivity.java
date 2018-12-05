package com.bebasbayar.embedded.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.bebasbayar.embedded.R;
import com.bebasbayar.embedded.clients.AppWebChromeClient;
import com.bebasbayar.embedded.clients.AppWebViewClient;
import com.bebasbayar.embedded.constants.Extra;
import com.bebasbayar.embedded.jsinterfaces.WinPayJSInterface;
import com.bebasbayar.embedded.params.EmbedParameters;
import com.bebasbayar.embedded.params.PartnerSecurityParameters;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenLinkActivity extends AppCompatActivity {
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_open_link);

        WebView webView = findViewById(R.id.webView);

        webView.setWebViewClient(new AppWebViewClient(this));
        webView.setWebChromeClient(new AppWebChromeClient(this));

        webView.getSettings().setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra(Extra.URL);

        Log.i("URL", url);

        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

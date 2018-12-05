package com.bebasbayar.embedded.clients;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AppWebViewClient extends WebViewClient {
    private AppCompatActivity context;

    public AppWebViewClient(AppCompatActivity context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("mailto:") || url.startsWith("tel:") || url.startsWith("download:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.startsWith("download:") ? url.replace("download:", "") : url));

            context.startActivity(intent);
        } else {
            view.loadUrl(url);
        }

        return false;
    }
}
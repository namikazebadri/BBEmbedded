package com.bebasbayar.embedded.clients;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bebasbayar.embedded.R;
import com.bebasbayar.embedded.activities.BBEmbedActivity;

public class AppWebChromeClient extends WebChromeClient {
    private BBEmbedActivity context;

    public AppWebChromeClient(BBEmbedActivity context) {
        this.context = context;
    }

    public void onProgressChanged(WebView webView, int newProgress) {
        if(newProgress >= 100) {
            context.findViewById(R.id.progressBarContainer).setVisibility(View.GONE);
            context.findViewById(R.id.webView).setVisibility(View.VISIBLE);
        } else {
            ((ProgressBar) context.findViewById(R.id.progressBar)).setProgress(newProgress);
            ((TextView) context.findViewById(R.id.progressBarText)).setText(context.getResources().getString(R.string.progress_text, String.valueOf(newProgress)));
        }
    }
}

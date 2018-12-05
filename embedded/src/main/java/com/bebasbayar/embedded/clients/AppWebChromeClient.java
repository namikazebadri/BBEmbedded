package com.bebasbayar.embedded.clients;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bebasbayar.embedded.R;

public class AppWebChromeClient extends WebChromeClient {
    private AppCompatActivity context;

    public AppWebChromeClient(AppCompatActivity context) {
        this.context = context;
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        if(newProgress >= 100) {
            context.findViewById(R.id.progressBarContainer).setVisibility(View.GONE);
            context.findViewById(R.id.webView).setVisibility(View.VISIBLE);
        } else {
            ((ProgressBar) context.findViewById(R.id.progressBar)).setProgress(newProgress);
            ((TextView) context.findViewById(R.id.progressBarText)).setText(context.getResources().getString(R.string.bb_embed_progress_text, String.valueOf(newProgress)));
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        AlertDialog dialog = new AlertDialog.Builder(view.getContext()).
            setTitle(context.getResources().getString(R.string.bb_embed_custom_alert_title)).
            setMessage(message).
            setPositiveButton(context.getResources().getString(R.string.bb_embed_custom_alert_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create();

        dialog.show();

        result.confirm();

        return true;
    }
}
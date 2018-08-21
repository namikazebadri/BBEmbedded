package com.bebasbayar.embedded.activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.bebasbayar.embedded.R;
import com.bebasbayar.embedded.clients.AppWebChromeClient;
import com.bebasbayar.embedded.clients.AppWebViewClient;
import com.bebasbayar.embedded.constants.Extra;
import com.bebasbayar.embedded.params.EmbedParameters;
import com.bebasbayar.embedded.params.PartnerSecurityParameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class BBEmbedActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_embedded);

        webView = findViewById(R.id.webView);

        new URLEndPointTask().execute(this);
    }

    private static class URLEndPointTask extends AsyncTask<Object, Void, String> {

        @SuppressLint("StaticFieldLeak")
        BBEmbedActivity context;

        @Override
        protected String doInBackground(Object... params) {
            context = (BBEmbedActivity) params[0];

            try {
                StringBuilder stringBuilder = new StringBuilder();
                URL url = new URL("https://www.bebasbayar.com/scconf/embed.html");

                BufferedReader bufferedReader;
                bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) stringBuilder.append(inputLine);

                bufferedReader.close();

                return stringBuilder.toString();
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                context.finish();
            }

            return null;
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onPostExecute(String content) {
            try {
                EmbedParameters embedParameters = context.getIntent().getParcelableExtra(Extra.EMBED_PARAMETERS);
                PartnerSecurityParameters partnerSecurityParameters = context.getIntent().getParcelableExtra(Extra.SECURITY_PARAMETERS);

                WebView webView = context.findViewById(R.id.webView);

                webView.setWebViewClient(new AppWebViewClient(context));
                webView.setWebChromeClient(new AppWebChromeClient(context));

                String postData = "api_key=" + URLEncoder.encode(embedParameters.getApiKey(), "UTF-8") + "&user_id=" + URLEncoder.encode(embedParameters.getPartnerUserId(), "UTF-8") + "&partner_security_key=" + URLEncoder.encode(partnerSecurityParameters.getSignature(), "UTF-8");

                webView.getSettings().setDatabaseEnabled(true);
                webView.getSettings().setAppCacheMaxSize(1024*1024*8);
                webView.getSettings().setAppCachePath(context.getCacheDir().getAbsolutePath());
                webView.getSettings().setAppCacheEnabled(true);
                webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                webView.getSettings().setJavaScriptEnabled(true);

                JSONObject urls = new JSONObject(content);

                webView.postUrl(embedParameters.isDevelopment() ? urls.getString("development") : urls.getString("production"), postData.getBytes());
            } catch (UnsupportedEncodingException | JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                context.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.isFocused() && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

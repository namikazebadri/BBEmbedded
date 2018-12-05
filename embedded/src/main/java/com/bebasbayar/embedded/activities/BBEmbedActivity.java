package com.bebasbayar.embedded.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

        EmbedParameters embedParameters = getIntent().getParcelableExtra(Extra.EMBED_PARAMETERS);
        PartnerSecurityParameters partnerSecurityParameters = getIntent().getParcelableExtra(Extra.SECURITY_PARAMETERS);

        if(!embedParameters.isDevelopment() && (!embedParameters.isProperParameters() || !partnerSecurityParameters.isProperParameters())) {
            Toast.makeText(this, "Parameters is not valid, please check your call implementation.", Toast.LENGTH_LONG).show();

            finish();
        }

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
                URL url = new URL(com.bebasbayar.embedded.constants.URL.URL_POOL);

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

        @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
        @Override
        protected void onPostExecute(String content) {
            try {
                EmbedParameters embedParameters = context.getIntent().getParcelableExtra(Extra.EMBED_PARAMETERS);
                PartnerSecurityParameters partnerSecurityParameters = context.getIntent().getParcelableExtra(Extra.SECURITY_PARAMETERS);

                WebView webView = context.findViewById(R.id.webView);

                webView.setWebViewClient(new AppWebViewClient(context));
                webView.setWebChromeClient(new AppWebChromeClient(context));

                String postData = "api_key=" + URLEncoder.encode(embedParameters.getApiKey(), "UTF-8")
                        + "&user_id=" + URLEncoder.encode(embedParameters.getPartnerUserId(), "UTF-8")
                        + "&partner_security_key=" + URLEncoder.encode(partnerSecurityParameters.getSignature(), "UTF-8")
                        + "&email=" + URLEncoder.encode(embedParameters.getUserEmail(), "UTF-8")
                        + "&app_name=" + URLEncoder.encode(embedParameters.getClientId(), "UTF-8")
                        + "&phone_number=" + URLEncoder.encode(embedParameters.getUserPhoneNumber(), "UTF-8")
                        + "&full_name=" + URLEncoder.encode(embedParameters.getUserFullName(), "UTF-8");

                JSONObject urls = new JSONObject(content);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.addJavascriptInterface(new WinPayJSInterface(context, embedParameters, partnerSecurityParameters, urls), "BB");

                webView.postUrl(embedParameters.isDevelopment() ? urls.getString("development") : urls.getString("production"), postData.getBytes());
            } catch (UnsupportedEncodingException | JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                context.finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    JSONObject result = new JSONObject(data.getStringExtra(Extra.PAYMENT_RESULT));

                    AlertDialog dialog = new AlertDialog.Builder(this).
                            setTitle(getResources().getString(R.string.bb_embed_custom_payment_title)).
                            setMessage(result.getString("rd")).
                            setPositiveButton(getResources().getString(R.string.bb_embed_custom_payment_button), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {}
                            }).create();

                    dialog.show();

                    while(webView.canGoBack()) {
                        webView.goBack();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
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

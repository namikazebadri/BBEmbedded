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

public class WinPayActivity extends AppCompatActivity {
    private String paymentResult;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_winpay);

        try {
            EmbedParameters embedParameters = getIntent().getParcelableExtra(Extra.EMBED_PARAMETERS);
            PartnerSecurityParameters partnerSecurityParameters = getIntent().getParcelableExtra(Extra.SECURITY_PARAMETERS);

            JSONObject urls = new JSONObject(getIntent().getStringExtra(Extra.URLS_PARAMETERS));

            WebView webView = findViewById(R.id.webView);

            webView.setWebViewClient(new AppWebViewClient(this));
            webView.setWebChromeClient(new AppWebChromeClient(this));

            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new WinPayJSInterface(this, embedParameters, partnerSecurityParameters, urls), "BB");

            String url = embedParameters.isDevelopment() ? urls.getString("payment_development") : urls.getString("payment_production");
            String postData = getIntent().getStringExtra(Extra.PAYMENT_PARAMETERS);

            Log.i("PAYMENT POST DATA", postData);

            webView.postUrl(url, postData.getBytes());
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

        if(getPaymentResult() != null) {
            intent.putExtra(Extra.PAYMENT_RESULT, getPaymentResult());
            setResult(AppCompatActivity.RESULT_OK, intent);
        } else {
            setResult(AppCompatActivity.RESULT_CANCELED, intent);
        }

        finish();
    }

    public String getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(String paymentResult) {
        this.paymentResult = paymentResult;
    }
}

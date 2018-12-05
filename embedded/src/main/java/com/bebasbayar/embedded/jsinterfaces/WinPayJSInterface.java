package com.bebasbayar.embedded.jsinterfaces;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.bebasbayar.embedded.activities.OpenLinkActivity;
import com.bebasbayar.embedded.activities.WinPayActivity;
import com.bebasbayar.embedded.constants.Extra;
import com.bebasbayar.embedded.params.EmbedParameters;
import com.bebasbayar.embedded.params.PartnerSecurityParameters;

import org.json.JSONObject;

public class WinPayJSInterface {
    private AppCompatActivity context;

    private EmbedParameters embedParameters;
    private PartnerSecurityParameters partnerSecurityParameters;
    private JSONObject urls;

    public WinPayJSInterface(AppCompatActivity context, EmbedParameters embedParameters, PartnerSecurityParameters partnerSecurityParameters, JSONObject urls) {
        this.context = context;
        this.embedParameters = embedParameters;
        this.partnerSecurityParameters = partnerSecurityParameters;
        this.urls = urls;
    }

    @JavascriptInterface
    public void pushRequest(String jsonRequest) {
        try {
            Intent intent = new Intent(context, WinPayActivity.class);

            JSONObject request = new JSONObject(jsonRequest);

            if(!request.has("client")) request.put("client", embedParameters.getClientId() == null ? "" : embedParameters.getClientId());
            if(!request.has("phonenumber")) request.put("phonenumber", embedParameters.getUserPhoneNumber() == null ? "" : embedParameters.getUserPhoneNumber());
            if(!request.has("email")) request.put("email", embedParameters.getUserEmail() == null ? "" : embedParameters.getUserEmail());
            if(!request.has("customername")) request.put("customername", embedParameters.getUserFullName() == null ? "" : embedParameters.getUserFullName());
            if(!request.has("customeruuid")) request.put("customeruuid", embedParameters.getPartnerUserId() == null ? "" : embedParameters.getPartnerUserId());

            intent.putExtra(Extra.PAYMENT_PARAMETERS, request.toString());
            intent.putExtra(Extra.EMBED_PARAMETERS, embedParameters);
            intent.putExtra(Extra.SECURITY_PARAMETERS, partnerSecurityParameters);
            intent.putExtra(Extra.URLS_PARAMETERS, urls.toString());

            context.startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public void pushResult(String jsonResult, boolean isFinished) {
        if(context instanceof WinPayActivity) {
            ((WinPayActivity) context).setPaymentResult(jsonResult);

            if(isFinished) {
                context.onBackPressed();
            }
        }
    }

    @JavascriptInterface
    public void copyToClipBoard(String copiedText) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Text Copied", copiedText);

        clipboard.setPrimaryClip(clip);
    }

    @JavascriptInterface
    public void openLink(String url) {
        Intent intent = new Intent(context, OpenLinkActivity.class);

        intent.putExtra(Extra.URL, url);

        context.startActivity(intent);
    }

    @JavascriptInterface
    public void openBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        context.startActivity(intent);
    }
}

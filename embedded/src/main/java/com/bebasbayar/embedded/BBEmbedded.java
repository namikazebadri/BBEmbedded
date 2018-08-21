package com.bebasbayar.embedded;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.bebasbayar.embedded.activities.BBEmbedActivity;
import com.bebasbayar.embedded.constants.Extra;
import com.bebasbayar.embedded.params.EmbedParameters;
import com.bebasbayar.embedded.params.PartnerSecurityParameters;

public class BBEmbedded {
    public static void show(AppCompatActivity context, EmbedParameters embedParameters, PartnerSecurityParameters partnerSecurityParameters) {
        Intent intent = new Intent(context, BBEmbedActivity.class);

        intent.putExtra(Extra.EMBED_PARAMETERS, embedParameters);
        intent.putExtra(Extra.SECURITY_PARAMETERS, partnerSecurityParameters);

        context.startActivity(intent);
    }
}

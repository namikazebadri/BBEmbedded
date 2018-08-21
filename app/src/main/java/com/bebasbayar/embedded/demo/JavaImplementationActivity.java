package com.bebasbayar.embedded.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bebasbayar.embedded.BBEmbedded;
import com.bebasbayar.embedded.params.EmbedParameters;
import com.bebasbayar.embedded.params.PartnerSecurityParameters;

public class JavaImplementationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Java Example");
        }

        Button openEmbedded = findViewById(R.id.openEmbedded);

        openEmbedded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BBEmbedded.show(JavaImplementationActivity.this, new EmbedParameters("APIKEY", "1", true), new PartnerSecurityParameters("PARTNERSECURITYKEY"));
            }
        });
    }
}

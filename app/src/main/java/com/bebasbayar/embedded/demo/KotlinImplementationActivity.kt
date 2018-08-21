package com.bebasbayar.embedded.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bebasbayar.embedded.BBEmbedded
import com.bebasbayar.embedded.params.EmbedParameters
import com.bebasbayar.embedded.params.PartnerSecurityParameters

class KotlinImplementationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val openEmbedded = findViewById<Button>(R.id.openEmbedded)

        openEmbedded.setOnClickListener {
            BBEmbedded.show(this, EmbedParameters("APIKEY", "1", true), PartnerSecurityParameters("PARTNERSECURITYKEY"))
        }

        supportActionBar!!.title = "Kotlin Example"
    }
}

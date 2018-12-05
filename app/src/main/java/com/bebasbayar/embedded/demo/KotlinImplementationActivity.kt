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
            BBEmbedded.show(this, EmbedParameters("2b9329b62ad269a611a2488658f93fab", "1", true, "uzumaki.unis@gmail.com", "085655141001", "Unis Badri", "BPJSTKAPP"), PartnerSecurityParameters("6806b1dc789b3359f4c9c9fa9c3d0e6b"))
        }

        supportActionBar!!.title = "Kotlin Example"
    }
}

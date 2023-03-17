package com.example.helthcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.helthcalculator.register.RegisterActivity

class ActivitySplash : AppCompatActivity() {
    lateinit var mainSplash: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mainSplash = findViewById(R.id.mainSplash)

        mainSplash.alpha = 0F
        mainSplash.animate().setDuration(300).alpha(1f).withEndAction {
            val intentRegister = Intent(this, InfoActivity::class.java)
            startActivity(intentRegister)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}
package com.example.helthcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BasicOfDiet : AppCompatActivity() {

    lateinit var tVOfPPM: TextView
    lateinit var tVOfCPM: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_of_diet)

        tVOfPPM = findViewById(R.id.TVOfPPM)
        tVOfCPM = findViewById(R.id.TVOfCPM)

        val valueOfPPM = intent.getStringExtra("PPM")
        val valueOfCPM = intent.getStringExtra("CPM")

        tVOfPPM.text = valueOfPPM
        tVOfCPM.text = valueOfCPM

    }
}
/*
   Dieta podstawowa
   - 15,5% Białko / 4
   - 29,5% Tłuszcz / 9
   - 55% Węglowodany / 4
   */
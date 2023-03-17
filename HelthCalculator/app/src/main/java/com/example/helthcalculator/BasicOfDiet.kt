package com.example.helthcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlin.math.roundToInt

class BasicOfDiet : AppCompatActivity() {

    lateinit var tVOfPPM: TextView
    lateinit var tVOfCPM: TextView
    lateinit var tVOfProtein: TextView
    lateinit var tvFatValue: TextView
    lateinit var tvCarbohydratesValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_of_diet)



        tVOfPPM = findViewById(R.id.TVOfPPM)
        tVOfCPM = findViewById(R.id.TVOfCPM)
        tVOfProtein = findViewById(R.id.tvProteinValue)
        tvFatValue = findViewById(R.id.tvFatValue)
        tvCarbohydratesValue = findViewById(R.id.tvCarbohydratesValue)

        val valueOfPPM = intent.getStringExtra("ppmValue")

        val valueOfCPM = intent.getStringExtra("cpmValue")

        tVOfPPM.text = valueOfPPM.toString()
        tVOfCPM.text = valueOfCPM.toString()

        val protein = ((valueOfCPM?.toInt()!! * 0.155) / 4).roundToInt()
        val fat = ((valueOfCPM.toInt() * 0.295) / 9).roundToInt()
        val carbohydrate = ((valueOfCPM.toInt() * 0.55) / 4).roundToInt()

        tVOfProtein.text = protein.toString()
        tvFatValue.text = fat.toString()
        tvCarbohydratesValue.text = carbohydrate.toString()
    }
}
/*
   Dieta podstawowa
   - 15,5% Białko / 4
   - 29,5% Tłuszcz / 9
   - 55% Węglowodany / 4
   */
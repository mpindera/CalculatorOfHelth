package com.example.helthcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.math.roundToInt

class InfoActivity : AppCompatActivity() {

    private lateinit var imageViewMan: ImageView
    private lateinit var imageViewWoman: ImageView
    private lateinit var buttonToCalculate: Button

    private lateinit var username: TextInputEditText
    private lateinit var userAge: TextInputEditText
    private lateinit var userWeight: TextInputEditText
    private lateinit var userHeight: TextInputEditText

    private var usernameText: String = ""
    private var userAgeText: String = ""
    private var userWeightText: String = ""
    private var userHeightText: String = ""

    var valueOfPAL = ""
    var selectImageMan = false
    var selectImageWoman = false
    var gender: String? = null

    private var valueOfPPM: Int = 0
    private var valueOfCPM: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        FirebaseApp.initializeApp(this)
        imageViewMan = findViewById(R.id.imageViewMan)
        imageViewWoman = findViewById(R.id.imageViewWoman)
        buttonToCalculate = findViewById(R.id.buttonToCalculate)

        username = findViewById(R.id.userNameText)
        userAge = findViewById(R.id.userAgeText)
        userWeight = findViewById(R.id.userWeightText)
        userHeight = findViewById(R.id.userHeightText)

        val activityRate = resources.getStringArray(R.array.pal)
        val arrayAdapter = ArrayAdapter(
            this,
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            activityRate
        )
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        autocompleteTV.setAdapter(arrayAdapter)
        autocompleteTV.setOnItemClickListener { parent, view, position, id ->
            valueOfPAL = parent.getItemAtPosition(position).toString()
        }



        imageViewMan.setOnClickListener {
            selectImageWoman = false
            selectImageMan = true
            imageViewMan.setImageResource(R.drawable.selected_image_man)
            imageViewWoman.setImageResource(R.drawable.woman)
            gender = "Men"
        }
        imageViewWoman.setOnClickListener {
            selectImageWoman = true
            selectImageMan = false
            imageViewWoman.setImageResource(R.drawable.selected_image_woman)
            imageViewMan.setImageResource(R.drawable.man)
            gender = "Woman"
        }

        buttonToCalculate.setOnClickListener {

            if (checkTextString()) {

                calculatePPM()
                calculateCPM()

                val patientID = UUID.randomUUID().toString()
                val patientInfo = hashMapOf(
                    "Gender" to gender,
                    "patientID" to patientID,
                    "Name" to usernameText,
                    "Age" to userAgeText.toInt(),
                    "Weight" to userWeightText.toDouble(),
                    "Height" to userHeightText.toDouble()
                )



                FirebaseFirestore.getInstance().collection("Patient").add(patientInfo)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pacjent został dodany", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAG2", "Error adding document", e)
                    }

            } else {
                Toast.makeText(this, "Uzupełnij wszystkie dane", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkTextString(): Boolean {
        usernameText = username.text.toString()
        userAgeText = userAge.text.toString()
        userWeightText = userWeight.text.toString()
        userHeightText = userHeight.text.toString()

        return (((usernameText.isNotEmpty())
                && (userHeightText.isNotEmpty())
                && (userAgeText.isNotEmpty())
                && (userWeightText.isNotEmpty())
                && (valueOfPAL.isNotEmpty()))
                && ((!selectImageMan && selectImageWoman)
                || (selectImageMan && !selectImageWoman)))
    }

    private fun calculatePPM() {
        //man: PPM = 66,5 + (13,75 x masa ciała [kg]) + (5,003 x wzrost [cm]) - (6,775 x wiek w latach)
        //woman: PPM = 655 + (9,563 x masa ciała [kg]) + (1,85 x wzrost [cm]) - (4,676 x wiek w latach)

        valueOfPPM = if (gender == "Men") {
            (66.5 + (13.75 * userWeightText.toDouble()) + (5.003 * userHeightText.toInt()) - (6.775 * userAgeText.toInt())).toInt()
        } else {
            (655 + (9.563 * userWeightText.toDouble()) + (1.85 * userHeightText.toInt()) - (4.676 * userAgeText.toInt())).toInt()
        }
    }

    private fun calculateCPM() {
        valueOfCPM = valueOfPAL.toInt() * valueOfPPM
    }
}
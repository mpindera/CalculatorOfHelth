package com.example.helthcalculator.data

data class DataAboutPatient(
    val patientID: String,
    val name: String,
    val gender: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    val ppm: Int,
    val cpm: Int
)


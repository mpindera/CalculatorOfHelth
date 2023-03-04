package com.example.helthcalculator

class InfoAboutPerson {
    private var gender: String
    private var patientID: String
    private var userNameText: String
    private var userAgeText: Int
    private var userWeightText: Double
    private var userHeightText: Double

    constructor() {
        this.gender = ""
        this.patientID = ""
        this.userNameText = ""
        this.userAgeText = 0
        this.userWeightText = 0.0
        this.userHeightText = 0.0
    }

    constructor(
        gender: String,
        patientID: String,
        userNameText: String,
        userAgeText: Int,
        userWeightText: Double,
        userHeightText: Double
    ) {
        this.gender = gender
        this.patientID = patientID
        this.userNameText = userNameText
        this.userAgeText = userAgeText
        this.userWeightText = userWeightText
        this.userHeightText = userHeightText
    }

    fun getGender(): String {
        return gender
    }

    fun getPatientID(): String {
        return patientID
    }

    fun userNameText(): String {
        return userNameText
    }

    fun userAgeText(): Int {
        return userAgeText
    }

    fun userWeightText(): Double {
        return userWeightText
    }

    fun userHeightText(): Double {
        return userHeightText
    }
}
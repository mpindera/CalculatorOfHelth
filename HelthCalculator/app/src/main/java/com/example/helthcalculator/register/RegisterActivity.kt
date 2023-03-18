package com.example.helthcalculator.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.helthcalculator.R
import com.example.helthcalculator.registerFragment.FragmentRegister
import com.example.helthcalculator.registerFragment.FragmentRegisterNext

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val firstFragment = FragmentRegister()
        val secondFragment = FragmentRegisterNext()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, firstFragment)
            commit()
        }

        val btn1 = findViewById<Button>(R.id.btn_next_to_)
        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, secondFragment)
                commit()
            }
        }

    }
}
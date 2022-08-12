package com.example.lab4_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mensajeToast: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mensajeToast = findViewById(R.id.iniciarbtn)
        initlisteners()

    }

    private fun initlisteners() {
        mensajeToast.setOnClickListener {
            Toast.makeText(this, "Javier Alejandro Azurdia Arrecis", Toast.LENGTH_LONG).show()
        }
    }


}
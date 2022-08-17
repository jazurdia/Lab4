package com.example.lab4_3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mensajeToast: Button
    lateinit var botonMaps: ImageButton
    lateinit var botonGP: Button
    lateinit var detallesActividad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mensajeToast = findViewById(R.id.iniciarbtn)
        initlisteners()

        botonMaps = findViewById(R.id.btn_imageDirections)
        mapsbtn()

        botonGP = findViewById(R.id.btn_download)
        gpbtn()

        detallesActividad = findViewById(R.id.detallesbtn)
        detallesbtn()

    }

    private fun initlisteners() {
        mensajeToast.setOnClickListener {
            Toast.makeText(this, "Javier Alejandro Azurdia Arrecis", Toast.LENGTH_LONG).show()
        }
    }

    private fun mapsbtn() {
        botonMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://goo.gl/maps/SwcekbfPwPxfuBRD8")
            startActivity(intent)
        }
    }

    private fun gpbtn(){
        botonGP.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.mojang.minecraftpe&hl=es")
            startActivity(intent)
        }
    }

    private fun detallesbtn(){
        detallesActividad.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }


}
package com.example.lab4_3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {

    lateinit var llamar: Button
    lateinit var permisoCamara: Button
    private lateinit var rootLayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        llamar = findViewById(R.id.llamar)
        llamar()

        permisoCamara = findViewById(R.id.permisoCamara)
        setListeners()

    }

    private fun llamar() {
        llamar = findViewById(R.id.llamar)
        llamar.setOnClickListener {
            val phoneNumber = "5555 5555"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, // El codigo que mandamos nosotros en el metodo requestPermissions
        permissions: Array<out String>, // Estos son los permisos que solicitamos
        grantResults: IntArray // Aqui vienen los valores (GRANTED o DENIED) de los N permisos que solicitamos
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Usamos el requestCode que enviamos en requestPermissions, en nuestro caso fue 0
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "${permissions[i]} was granted")
                }
            }
        }
    }

    private fun setListeners() {
        permisoCamara.setOnClickListener {
            checkCameraPermission()
        }
    }

    // Método que retorna true en caso el usuario ya haya aceptado el permiso. False si no.
    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission() {
        if (!hasCameraPermission()) {
            checkRequestRationale()
            Toast.makeText(this, "Permiso necesario para usar la cámara", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Permiso otorgadp, abrir camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Verificamos si debemos mostrar algun mensale racional
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                // En mi caso, lo mostraré en un SnackBar. Asi crean uno básico
                val snackbar = Snackbar.make(
                    rootLayout,
                    "Acceso a cámara es necesario para poder tomar fotos",
                    Snackbar.LENGTH_INDEFINITE
                )
                // Así pueden asignarle una acción (no es obligatorio, pero para nuestro caso sí lo será)
                snackbar.setAction("Ok"){
                    // Método a ejecutar al apachar el botón del snackbar
                    requestCameraPermission()
                }
                // Y por último, lo deben mostrar
                snackbar.show()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, // Siempre deben poner el contexto del activity
            arrayOf(Manifest.permission.CAMERA), // La lista con 1 o mas permisos a solicitar
            0 // Codigo que usan en onRequestPermissionsResult
        )
    }






}
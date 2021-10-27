    package com.luisleyva.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

    class MainActivity : AppCompatActivity() {
     var cuenta: Int = 0
     var objecto: String? = "pichadas"
     lateinit var tv_contador: TextView
     lateinit var et_pichadas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val btn_aumenta: ImageButton = findViewById(R.id.BotonAumenta)
    val btn_reduce: ImageButton = findViewById(R.id.BotonReduce)
    val btn_restaura: ImageButton = findViewById(R.id.BotonRestaura)
    tv_contador = findViewById(R.id.contador)
    et_pichadas = findViewById(R.id.pichadas)


    btn_aumenta.setOnClickListener {
        cuenta++
        tv_contador.setText("${cuenta}")
    }

    btn_reduce.setOnClickListener {

      try {
          if (cuenta <= 0) {
              Toast.makeText(this, "Contador en 0", Toast.LENGTH_SHORT).show()
              tv_contador.setText("0")
          } else {
              cuenta--
              tv_contador.setText("${cuenta}")
          }
      } catch (e: Exception) {
          e.message
      }

    }

    btn_restaura.setOnClickListener {
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            val apply = builder.apply {
                setPositiveButton("Borrar",
                    DialogInterface.OnClickListener { dialog, id ->
                        cuenta = 0
                        tv_contador.setText("$cuenta")

                    })
                setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            // Set other dialog properties
            // 2. Chain together various setter methods to set the dialog characteristics
            builder?.setMessage("Seguro desea eliminar la cuenta?")
                .setTitle("Accion Irreversible")

            // Create the AlertDialog
            builder.create()
        }

        alertDialog?.show()

    }

    }

        override fun onPause() {
            super.onPause()


            val sharedPref2 = this?.getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref2.edit()

            objecto = et_pichadas.text.toString()
            editor.putInt("contador", cuenta)
            editor.putString("objeto", objecto)
            editor.commit()

        }

        override fun onResume() {
            super.onResume()

        val sharePref = this.getPreferences(Context.MODE_PRIVATE)
        cuenta = sharePref.getInt("contador", 0)
        objecto = sharePref.getString("objeto", "pichadas")
        et_pichadas.setText(objecto)
        tv_contador.setText("$cuenta")
        }

}
package com.example.lindsay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
            mostrarMensaje()
        }, 5000)
    }

    private fun mostrarMensaje() {
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
    }
}

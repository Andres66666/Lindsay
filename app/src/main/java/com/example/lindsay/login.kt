package com.example.lindsay

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.text.method.ScrollingMovementMethod
import android.widget.*
import pl.droidsonroids.gif.GifImageButton
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import android.widget.ImageButton


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val btn_consultar = findViewById<GifImageButton>(R.id.LoginIngresar)

        btn_consultar.setOnClickListener {
            comprobar()
        }

        val crear = findViewById<GifImageButton>(R.id.LoginCrear)
        crear.setOnClickListener {
            val intent = Intent(this, RegistroUsuarios::class.java)
            startActivity(intent)
        }
        val facebookButton = findViewById<GifImageButton>(R.id.btnFacebook)
        facebookButton.setOnClickListener {
            val facebookUrl = "https://www.facebook.com/people/Est%C3%A9tica-Lindsay/100064177051974/"
            val uri = Uri.parse(facebookUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        val WhatsappButton = findViewById<GifImageButton>(R.id.btnWhatsapp)
        WhatsappButton.setOnClickListener {
            val facebookUrl = "https://chat.whatsapp.com/KwpTaL01vq8Itk7FLUTRBH"
            val uri = Uri.parse(facebookUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        val InstagramButton = findViewById<GifImageButton>(R.id.btnInstagram)
        InstagramButton.setOnClickListener {
            val facebookUrl = "https://www.instagram.com/estetica_lindsay/"
            val uri = Uri.parse(facebookUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }
    fun conexion_base_de_datos(): Connection? {
        val url = "jdbc:mysql://207.244.255.46/ratiosof12x_bd_salon"
        val user = "ratiosof12x_salon"
        val contrasenia = "2Wm0ozoOE@Uu"
        Class.forName("com.mysql.jdbc.Driver")
        return try {
            DriverManager.getConnection(url, user, contrasenia)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }


    fun comprobar() {
        val conexion = conexion_base_de_datos()

        val Usuario = findViewById<EditText>(R.id.LoginUsuario)
        val edtPassword = findViewById<EditText>(R.id.LoginPassword)

        val usuario = Usuario.text.toString()
        val password = edtPassword.text.toString()

        if (conexion != null) {
            val query: Statement = conexion.createStatement()
            val result: ResultSet = query.executeQuery("SELECT usuario, password FROM usuarios WHERE usuario = '$usuario' AND password = '$password'")

            if (result.next()) {
                // Coincidencia encontrada, cambiar de actividad
                val intent = Intent(this, Main3Activity::class.java)
                startActivity(intent)
            } else {
                // No se encontró coincidencia, mostrar mensaje de error
                Toast.makeText(applicationContext, "Datos no válidos", Toast.LENGTH_SHORT).show()
            }

            result.close()
            conexion.close()
        } else {
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show()
        }
    }
}

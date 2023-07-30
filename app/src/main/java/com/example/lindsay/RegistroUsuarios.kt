package com.example.lindsay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class RegistroUsuarios : AppCompatActivity() {
    private lateinit var idEditText: EditText
    private lateinit var usuarioEditText: EditText
    private lateinit var nombreEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var direccionEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var crearLoginButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuarios)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Inicializar las referencias a los elementos de la interfaz
        idEditText = findViewById(R.id.id)
        usuarioEditText = findViewById(R.id.usuario)
        nombreEditText = findViewById(R.id.nombre)
        telefonoEditText = findViewById(R.id.telefono)
        direccionEditText = findViewById(R.id.direccion)
        passwordEditText = findViewById(R.id.edtPassword2)
        crearLoginButton = findViewById(R.id.btnCrearLogin)

        crearLoginButton.setOnClickListener {
            // Obtener los datos ingresados por el usuario
            val id = idEditText.text.toString()
            val usuario = usuarioEditText.text.toString()
            val nombre = nombreEditText.text.toString()
            val telefono = telefonoEditText.text.toString()
            val direccion = direccionEditText.text.toString()
            val contraseña = passwordEditText.text.toString()

            if (id.isNotEmpty() && usuario.isNotEmpty() && nombre.isNotEmpty() &&
                telefono.isNotEmpty() && direccion.isNotEmpty() && contraseña.isNotEmpty()) {
                insertData(id, usuario, nombre, telefono, direccion, contraseña)
            } else {
                Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun connToDatabase(): Connection? {
        val url = "jdbc:mysql://207.244.255.46/ratiosof12x_bd_salon"
        val username = "ratiosof12x_salon"
        val password = "2Wm0ozoOE@Uu"

        return try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            DriverManager.getConnection(url, username, password)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun insertData(id: String, usuario: String, nombre: String, telefono: String, direccion: String, contraseña: String) {
        val conn = connToDatabase()

        if (conn != null) {
            val preparedStatement = conn.prepareStatement("INSERT INTO usuarios (id, usuario, nombre, telefono, direccion, password) VALUES (?, ?, ?, ?, ?, ?)")
            preparedStatement.setString(1, id)
            preparedStatement.setString(2, usuario)
            preparedStatement.setString(3, nombre)
            preparedStatement.setString(4, telefono)
            preparedStatement.setString(5, direccion)
            preparedStatement.setString(6, contraseña)
            preparedStatement.executeUpdate()

            preparedStatement.close()
            conn.close()
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            // Redireccionar a la interfaz de inicio de sesión
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish() // Opcional: Finalizar la actividad actual si no se desea volver atrás
        } else {
            Toast.makeText(this, "Problemas de conexión.", Toast.LENGTH_LONG).show()
        }
    }
}

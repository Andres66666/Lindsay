package com.example.lindsay.ui.productos


import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lindsay.databinding.FragmentProductosBinding
import java.io.ByteArrayInputStream
import java.sql.*

class ProductosFragment : Fragment() {

    private var _binding: FragmentProductosBinding? = null
    private val binding get() = _binding!!

    private var connection: Connection? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productosViewModel = ViewModelProvider(this).get(ProductosViewModel::class.java)

        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Establecer conexión con la base de datos
        connection = conexionBaseDeDatos()

        if (connection != null) {
            // Recuperar datos de la base de datos
            val statement = connection!!.createStatement()
            val query = "SELECT codigo, nombre, compra, venta, existencia FROM productos"
            val resultSet: ResultSet = statement.executeQuery(query)

            // Obtener el LinearLayout del layout
            val linearLayout: LinearLayout = binding.listado

            // Iterar sobre los resultados y mostrar los datos
            while (resultSet.next()) {
                val codigo = resultSet.getString("codigo")
                val nombre = resultSet.getString("nombre")
                val compra = resultSet.getBigDecimal("compra")
                val venta = resultSet.getBigDecimal("venta")
                val existencia = resultSet.getInt("existencia")

                // Hacer algo con los datos recuperados, por ejemplo, mostrarlos en un TextView
                val textView = TextView(requireContext())
                textView.text = "Indigo: $codigo\nNombre: $nombre\nCompra: $compra\nVenta: $venta\nExistencia: $existencia\n"

                linearLayout.addView(textView) // Agregar el TextView al LinearLayout


            }
        } else {
            // Error al establecer la conexión con la base de datos
            // Puedes mostrar un mensaje de error o realizar otra acción
            // Ejemplo:
            Toast.makeText(requireContext(), "Error de conexión", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        // Cerrar la conexión con la base de datos al destruir la vista
        if (connection != null) {
            try {
                connection!!.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    private fun conexionBaseDeDatos(): Connection? {
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
}

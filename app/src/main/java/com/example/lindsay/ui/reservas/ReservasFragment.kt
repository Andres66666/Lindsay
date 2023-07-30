package com.example.lindsay.ui.reservas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lindsay.Main3Activity
import com.example.lindsay.R
import com.example.lindsay.camara
import com.example.lindsay.databinding.FragmentReservasBinding
import com.example.lindsay.login
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*



class ReservasFragment : Fragment() {

    private lateinit var registrarButton: ImageButton

    private var _binding: FragmentReservasBinding? = null
    private val binding get() = _binding!!

    private val selectedCalendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCuadrado2D = binding.registrar
        btnCuadrado2D.setOnClickListener {
            val intent = Intent(requireContext(), camara::class.java)
            startActivity(intent)
        }

        binding.fechaInicio.setOnClickListener {
            showDatePickerDialog()
        }
        binding.fechafin.setOnClickListener {
            showDatePickerDialog1()
        }
        binding.horaInicio.setOnClickListener {
            showTimePickerDialog()
        }
        binding.Horafin.setOnClickListener {
            showTimePickerDialog1()
        }
        registrarButton = binding.registrar

        registrarButton.setOnClickListener {
            // Obtener los datos ingresados por el usuario
            val id = binding.id.text.toString()
            val estilista_id = binding.Idestilista.selectedItem.toString()
            val evento = binding.Evento.text.toString()
            val tipo_evento = binding.tipoEvento.text.toString()
            val fecha_inicio = binding.fechaInicio.text.toString()
            val fecha_fin = binding.fechafin.text.toString()
            val hora_inicio = binding.horaInicio.text.toString()
            val hora_fin = binding.Horafin.text.toString()

            if (id.isNotEmpty() && estilista_id.isNotEmpty() && evento.isNotEmpty() &&
                tipo_evento.isNotEmpty() && fecha_inicio.isNotEmpty() && fecha_fin.isNotEmpty()
                && hora_inicio.isNotEmpty() && hora_fin.isNotEmpty()
            ) {
                insertData(
                    id,
                    estilista_id,
                    evento,
                    tipo_evento,
                    fecha_inicio,
                    fecha_fin,
                    hora_inicio,
                    hora_fin
                )
            } else {
                Toast.makeText(requireContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT)
                    .show()
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
    private fun insertData(
        id: String,
        estilista_id: String,
        evento: String,
        tipo_evento: String,
        fecha_inicio: String,
        fecha_fin: String,
        hora_inicio: String,
        hora_fin: String
    ) {
        val conn = connToDatabase()

        if (conn != null) {
            try {
                val statement = conn.prepareStatement(
                    "INSERT INTO eventoscalendar (id, estilista_id, evento, tipo_evento, fecha_inicio, fecha_fin, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                )
                statement.setString(1, id)
                statement.setString(2, estilista_id)
                statement.setString(3, evento)
                statement.setString(4, tipo_evento)
                statement.setDate(5, java.sql.Date(parseDate(fecha_inicio).time))
                statement.setDate(6, java.sql.Date(parseDate(fecha_fin).time))
                statement.setTime(7, java.sql.Time(parseTime(hora_inicio).time))
                statement.setTime(8, java.sql.Time(parseTime(hora_fin).time))

                val rowsInserted = statement.executeUpdate()

                if (rowsInserted > 0) {
                    Toast.makeText(
                        requireContext(),
                        "Registro con exito",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Redireccionar a la interfaz de inicio de sesión
                    val intent = Intent(requireContext(), Main3Activity::class.java)
                    startActivity(intent)
                    requireActivity().finish() // Opcional: Finalizar la actividad actual si no se desea volver atrás
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error al insertar los datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                statement.close()
            } catch (e: SQLException) {
                e.printStackTrace()
                Toast.makeText(
                    requireContext(),
                    "Error al insertar los datos",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                conn.close()
            }
        } else {
            Toast.makeText(requireContext(), "Problemas de conexión.", Toast.LENGTH_LONG).show()
        }
    }

    private fun parseDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.parse(dateString)
    }

    private fun parseTime(timeString: String): Date {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.parse(timeString)
    }


    private fun showTimePickerDialog() {
        val etScheduledTime = requireView().findViewById<EditText>(R.id.horaInicio)

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                etScheduledTime.setText(selectedTime)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }
    private fun showTimePickerDialog1() {
        val etScheduledTime = requireView().findViewById<EditText>(R.id.Horafin)

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                etScheduledTime.setText(selectedTime)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
    }

    private fun showDatePickerDialog() {
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val day = selectedCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                binding.fechaInicio.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }
    private fun showDatePickerDialog1() {
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val day = selectedCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                binding.fechafin.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

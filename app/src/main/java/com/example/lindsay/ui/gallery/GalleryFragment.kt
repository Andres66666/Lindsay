package com.example.lindsay.ui.gallery

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lindsay.R
import com.example.lindsay.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private var isMarked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val c1ImageButton = binding.c1
        c1ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "Este corte tiene un degrado de 0 a 4  un depilado de frente"
            showDialog(message)
        }
        val c2ImageButton = binding.c2
        c2ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "Este corte tiene un degrado de 2 a 4  un depilado de frente"
            showDialog(message)
        }
        val c5ImageButton = binding.c5
        c5ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "Este corte dama tiene tiene un degrado de 8 a los lados y la parte superior tiene un 25 de degrade "
            showDialogDamas(message)
        }
        val c6ImageButton = binding.c6
        c6ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "Este corte dama tiene tiene un cerquillo de 20 cm  en recta "
            showDialogDamas(message)
        }
        val c9ImageButton = binding.c9
        c9ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "Peinado estilo chilindrina con un serquillo al costado"
            showDialogninos(message)
        }
        val c12ImageButton = binding.c12
        c12ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "ondulado de cabello con unos tintes especiales de color a eleccion"
            showDialogninos(message)
        }
        val c13ImageButton = binding.c13
        c13ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "tinte de de pelo con un color degradado morado"
            showDialogtintes(message)
        }

        val c14ImageButton = binding.c14
        c14ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "tinte de de pelo con un color degradado rojo"
            showDialogtintes(message)
        }
        val ctr1ImageButton = binding.c17
        ctr1ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "trenzas con dos guias muy bonito por cierto "
            showDialogtrenzas(message)
        }
        val ctr2ImageButton = binding.c19
        ctr2ImageButton.setOnClickListener {
            // Mostrar un diálogo con el mensaje
            val message = "doble trenzas con dos guias muy bonito por cierto "
            showDialogtrenzas(message)
        }

        return root
    }

    private fun showDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Detalle del corte Caballeros")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Marcar") { _, _ ->
            isMarked = !isMarked
            updateImageBorder()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showDialogDamas(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Detalle del corte Dama")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Marcar") { _, _ ->
            isMarked = !isMarked
            updateImageBorder()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showDialogninos(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Detalle del corte Niños")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Marcar") { _, _ ->
            isMarked = !isMarked
            updateImageBorder()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showDialogtintes(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Detalle del corte Niños")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Marcar") { _, _ ->
            isMarked = !isMarked
            updateImageBorder()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showDialogtrenzas(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Detalle del corte Niños")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Marcar") { _, _ ->
            isMarked = !isMarked
            updateImageBorder()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    private fun updateImageBorder() {
        if (isMarked) {
            binding.c1.setBackgroundResource(R.drawable.border_marked)
        } else {
            binding.c1.setBackgroundResource(R.drawable.border_unmarked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

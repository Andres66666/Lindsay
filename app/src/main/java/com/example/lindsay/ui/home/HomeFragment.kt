package com.example.lindsay.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lindsay.InterfaceUsurious
import com.example.lindsay.R
import com.example.lindsay.camara
import com.example.lindsay.databinding.FragmentHomeBinding

import java.io.File
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var ivFoto: ImageView? = null
    private var btnTomarFoto: Button? = null
    private val COD_FOTO = 20
    private val CARPETA_RAIZ = "MisFotosApp"
    private val CARPETA_IMAGENES = "imagenes"
    private val RUTA_IMAGEN = "$CARPETA_RAIZ/$CARPETA_IMAGENES"
    private var path: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCuadrado2D = binding.btnTomarFoto
        btnCuadrado2D.setOnClickListener {
            val intent = Intent(requireContext(), InterfaceUsurious::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

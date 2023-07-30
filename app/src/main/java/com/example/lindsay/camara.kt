package com.example.lindsay

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
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.annotation.Nullable
import java.io.File

class camara : AppCompatActivity() {
    private var ivFoto: ImageView? = null
    private var btnTomarFoto: Button? = null
    private val COD_FOTO = 20
    private val CARPETA_RAIZ = "MisFotosApp"
    private val CARPETA_IMAGENES = "imagenes"
    private val RUTA_IMAGEN = "$CARPETA_RAIZ/$CARPETA_IMAGENES"
    private var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)
        ivFoto = findViewById(R.id.ivFoto)
        btnTomarFoto = findViewById(R.id.btnTomarFoto)

        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        }

        btnTomarFoto?.setOnClickListener(View.OnClickListener { TomarFoto() })
    }

    private fun TomarFoto() {
        var nombreImagen = ""
        val fileImagen = File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN)
        val isCreada = fileImagen.exists() || fileImagen.mkdirs()

        if (isCreada) {
            nombreImagen = "${System.currentTimeMillis() / 1000}.jpg"
        }

        path = "${Environment.getExternalStorageDirectory()}/$RUTA_IMAGEN/$nombreImagen"
        val imagen = File(path)

        var intent: Intent? = null
        intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val authorities = "$packageName.provider"
            val imageUri = FileProvider.getUriForFile(this, authorities, imagen)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen))
        }

        startActivityForResult(intent, COD_FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                COD_FOTO -> {
                    MediaScannerConnection.scanFile(
                        this, arrayOf(path), null
                    ) { path, uri ->
                        // Guardar imagen en la galería
                        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                        val file = File(path)
                        val contentUri = Uri.fromFile(file)
                        mediaScanIntent.data = contentUri
                        this.sendBroadcast(mediaScanIntent)
                    }
                    val bitmap = BitmapFactory.decodeFile(path)
                    ivFoto?.setImageBitmap(bitmap)
                }
            }
        }
    }
}

package com.example.lindsay.ui.ubicacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UbicacionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hola mundo soy ubicaion"
    }
    val text: LiveData<String> = _text
}

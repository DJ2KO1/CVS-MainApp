package com.example.findmyip.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.findmyip.Network.IPRepository
import com.example.findmyip.model.UISTATE
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus



class IPViewModel(private val repo: IPRepository): ViewModel() {
    private val _ipData = MutableStateFlow<UISTATE>(UISTATE.EMPTY)
    val ipData: StateFlow<UISTATE>
        get() = _ipData

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                "IPViewModel",
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    init {
        getIP()
    }

    fun getIP() {
        viewModelSafeScope.launch {
            repo.getIP().collect() {
                _ipData.value = it

            }
        }
    }
}

class IpViewModelFactory(private val repository: IPRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IPViewModel::class.java)) {
            return IPViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

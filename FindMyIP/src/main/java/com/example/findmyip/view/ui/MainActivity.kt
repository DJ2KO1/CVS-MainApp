package com.example.findmyip.view.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.findmyip.Network.IPRepositoryImpl
import com.example.findmyip.Network.Provider
import com.example.findmyip.view.ui.theme.FindMyIPTheme
import com.example.findmyip.viewmodel.IPViewModel
import com.example.findmyip.viewmodel.IpViewModelFactory


class MainActivity : ComponentActivity() {
    private val repo  = IPRepositoryImpl(Provider.retrofitService)
    private val viewModel: IPViewModel by viewModels {
        IpViewModelFactory(repo)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindMyIPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayScreen(viewModel)
                }
            }
        }
    }
}

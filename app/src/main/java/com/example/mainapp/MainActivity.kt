package com.example.mainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.findmyip.Network.IPRepositoryImpl
import com.example.findmyip.Network.Provider
import com.example.findmyip.view.ui.DisplayScreen
import com.example.findmyip.viewmodel.IPViewModel
import com.example.findmyip.viewmodel.IpViewModelFactory
import com.example.mainapp.ui.theme.MainAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: IPViewModel by viewModels {
        IpViewModelFactory(IPRepositoryImpl(Provider.retrofitService))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppTheme {
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
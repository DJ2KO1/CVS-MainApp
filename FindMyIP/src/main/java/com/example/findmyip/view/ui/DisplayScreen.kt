package com.example.findmyip.view.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.findmyip.model.IPResponseModel
import com.example.findmyip.model.UISTATE
import com.example.findmyip.viewmodel.IPViewModel

@Composable
fun DisplayScreen(viewModel: IPViewModel) {
    val uiState = viewModel.ipData.collectAsState().value
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is UISTATE.LOADING -> {
                LinearProgressIndicator()
            }

            is UISTATE.SUCCESS -> {
                uiState.response?.let { DisplaySuccess(response = it) }
            }

            is UISTATE.ERROR ->     {
                DisplayError(errorMessage = uiState.error.localizedMessage)
                Log.d("Display", uiState.error.message.toString())
            }

            else -> {}
        }
    }


}

@Composable
fun DisplayError(errorMessage: String) {
    Text(text = errorMessage, color = Color.Black, textAlign = TextAlign.Center)
}

@Composable
fun DisplaySuccess(response: IPResponseModel) {
    Column {

        createText("ASN" ,response.asn)
        createText("City" ,response.city)
        createText("Continent Code" ,response.continentCode)
        createText("Country",response.country)
        createText("Country Area" ,response.countryArea.toString())
        createText("Country CallingCode",response.countryCallingCode)
        createText("Country Capital" ,response.countryCapital)
        createText("Country Code",response.countryCode)
        createText("Country CodeISO3" ,response.countryCodeISO3)
        createText("Country Name",response.countryName)
        createText("Country Population" ,response.countryPopulation.toString())
        createText("Country TLD",response.countryTLD)
        createText("Currency" ,response.currency)
        createText("Currency Name",response.currencyName)
        createText("In EU" ,response.inEU.toString())
        createText("IP",response.ip)
        createText("Languages" ,response.languages)
        createText("Latitude",response.latitude.toString())
        createText("Longitude" ,response.longitude.toString())
        createText("Org",response.org)
        createText("Postal" ,response.postal)
        createText("Region",response.region)
        createText("Region Code" ,response.regionCode)
        createText("Timezone",response.timezone)
        createText("UTC Offset" ,response.utcOffset)
        createText("Version",response.version)
    }
}

@Composable
fun createText(str: String, value: String?) {
    Column {
        Text(text = "$str: $value", color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
    }


}
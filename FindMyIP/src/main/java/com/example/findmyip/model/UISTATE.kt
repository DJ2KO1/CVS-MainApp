package com.example.findmyip.model

sealed class UISTATE {
    object EMPTY: UISTATE()
    object LOADING : UISTATE()
    class ERROR(val error: Exception) : UISTATE()
    class SUCCESS(val response: IPResponseModel?) : UISTATE()

}
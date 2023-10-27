package com.example.findmyip.Network

import android.util.Log
import com.example.findmyip.model.UISTATE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface IPRepository {

    suspend fun getIP(): Flow<UISTATE>

}

class IPRepositoryImpl(private val service: IPAPI): IPRepository {

    override suspend fun getIP(): Flow<UISTATE> =
        flow{

            emit(UISTATE.LOADING)
            try {
                val response = service.getIP()

                if (response.isSuccessful){
                    emit(response.body()?.let {
                        UISTATE.SUCCESS(it)
                    }?: throw Exception("Empty Response"))
                } else throw Exception("Failed Network Response")
            } catch (e: Exception){
                emit(UISTATE.ERROR(e))
            }
        }
    }
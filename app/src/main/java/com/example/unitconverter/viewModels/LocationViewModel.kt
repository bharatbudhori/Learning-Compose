package com.example.unitconverter.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconverter.models.GeocodingResult
import com.example.unitconverter.models.LocationData
import com.example.unitconverter.services.RetrofitClient
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel(){
    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address : State<List<GeocodingResult>> = _address

    fun updateLocation(locationData: LocationData){
        _location.value = locationData
    }

    fun fetchAddress(
        latlng : String
    ){
        try {
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    "AIzaSyCQIsETF_T2gFokW1ZBo790JYJGMROdI-Y"
                )
                _address.value = result.results
            }
        }catch (e : Exception){
            Log.d("Bharat", "${e.cause} ${e.message}")
        }
    }
}
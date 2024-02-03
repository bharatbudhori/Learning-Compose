package com.example.unitconverter.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unitconverter.models.LocationData

class LocationViewModel : ViewModel(){
    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    fun updateLocation(locationData: LocationData){
        _location.value = locationData
    }
}
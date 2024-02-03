package com.example.unitconverter.screens

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.unitconverter.MainActivity
import com.example.unitconverter.services.LocationUtils
import com.example.unitconverter.viewModels.LocationViewModel


@Composable
fun LocationScreen(
    viewModel: LocationViewModel
) {
    val context = LocalContext.current
    val locationUtils = LocationUtils(context = context)
    LocationDisplay(locationUtils = locationUtils, viewModel = viewModel,  context = context)
}

@Composable
fun LocationDisplay(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    context: Context
) {

    val location = viewModel.location.value

    val address = location?.let {
        locationUtils.reverseGeocodeLocation(it)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                // Permission granted, update location
            } else {
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if (rationalRequired) {
                    Toast.makeText(
                        context,
                        "Location permission required for this feature",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Location permission denied, go to settings to grant access",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(location != null){
            Text(text = "Address: ${location.latitude} , ${location.longitude} \n $address" )
        }
        else{
            Text(text = "Location not available")
        }



        Button(onClick = {
            if (locationUtils.hasLocationPermission(context)) {
                // Permission granted, update location
                locationUtils.requestLocationUpdates(viewModel)
            } else {
                // Request permission
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )

            }
        }) {
            Text(text = "Get Location")
        }
    }
}
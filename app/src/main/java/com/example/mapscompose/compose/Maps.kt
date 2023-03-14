package com.example.mapscompose.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mapscompose.ui.theme.MapsComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Google_Maps_Screen() {
    val location = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(location, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ){
        Marker(
            state = MarkerState(position = location),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Google_Maps__Screen_Preview() {
    MapsComposeTheme {
        Google_Maps_Screen()
    }
}
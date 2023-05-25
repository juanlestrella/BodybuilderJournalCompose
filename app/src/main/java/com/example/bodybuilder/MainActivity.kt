package com.example.bodybuilder

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.bodybuilder.navigation.AppBottomNavigation
import com.example.bodybuilder.navigation.AppNavHost
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.jar.Manifest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if(isGranted){
            Log.i("Camera Permission", "Permission Granted")
        } else {
            Log.i("Camera Permission", "Permission Denied")
        }
    }

    private fun requestCameraPermission(){
        when{
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED -> {
                        Log.i("Permission", "Permission Previously Granted")
                    }
            ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)
                    -> Log.i("Permission", "Show camera permissions dialog")
            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bodybuilder {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                Scaffold(
                    scaffoldState = scaffoldState,

                    topBar = {
                        TopAppBar(
                            title = { Text("Body Building Journal", color = MaterialTheme.colors.primary) },
                            backgroundColor = MaterialTheme.colors.background,
                        )
                    },

                    content = { paddingValues ->
                        AppNavHost(
                            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding().minus(28.dp)),//.minus(28.dp)
                            navController = navController
                        )
                    },

                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                )
            }
        }
        requestCameraPermission()
    }
}




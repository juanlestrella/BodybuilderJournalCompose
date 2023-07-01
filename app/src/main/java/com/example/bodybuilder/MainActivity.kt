package com.example.bodybuilder

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.bodybuilder.navigation.AppBottomNavigation
import com.example.bodybuilder.navigation.AppNavHost
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var cameraExecutor: ExecutorService

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted ->
        if(isGranted){
            Log.i("Camera Permission", "Permission Granted")
            shouldShowCamera.value = true
        } else {
            Log.i("Camera Permission", "Permission Denied")
        }
    }

    private fun requestCameraPermission(){
        when{
            ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Permission", "Permission Previously Granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            ) -> Log.i("Permission", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            requestCameraPermission()
            cameraExecutor = Executors.newSingleThreadExecutor()
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
                            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding().minus(28.dp)),
                            navController = navController,
                            shouldShowCamera = shouldShowCamera,
                            executor = cameraExecutor,
                            directory = getOutputDirectory()
                        )
                    },

                    bottomBar = {
                        AppBottomNavigation(navController = navController)
                    }
                )
            }
        }
    }
}
//            if(shouldShowCamera.value) {
//                CameraViewScreen(
//                    executor = cameraExecutor
//                )
//            }
//            if (shouldShowPhoto.value){
//                Image(
//                    painter = rememberAsyncImagePainter(photoUri),
//                    contentDescription = null,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
/////////////////////////////////////////////////////////////////////////////////////
//            Bodybuilder {
//                val scaffoldState = rememberScaffoldState()
//                val navController = rememberNavController()
//                Scaffold(
//                    scaffoldState = scaffoldState,
//
//                    topBar = {
//                        TopAppBar(
//                            title = { Text("Body Building Journal", color = MaterialTheme.colors.primary) },
//                            backgroundColor = MaterialTheme.colors.background,
//                        )
//                    },
//
//                    content = { paddingValues ->
//                        AppNavHost(
//                            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding().minus(28.dp)),//.minus(28.dp)
//                            navController = navController
//                        )
//                    },
//
//                    bottomBar = {
//                        AppBottomNavigation(navController = navController)
//                    }
//                )
//            }
/////////////////////////////////////////////////////////////////////////////////////
//            CameraViewScreen(
//                shouldShowCamera,
//                executor = cameraExecutor,
//                directory = getOutputDirectory()
//            )



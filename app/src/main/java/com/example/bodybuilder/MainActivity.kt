package com.example.bodybuilder

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bodybuilder.compose.CameraView
import com.example.bodybuilder.navigation.AppBottomNavigation
import com.example.bodybuilder.navigation.AppNavHost
import com.example.bodybuilder.ui.theme.Bodybuilder
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
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

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private fun handleImageCapture(uri: Uri){
        Log.i("HandleImageCapture", "Image captured: $uri")
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
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
            if(shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = {Log.e("Camera View", "View error:", it)})
                if (shouldShowPhoto.value){
                    Image(
                        painter = rememberAsyncImagePainter(photoUri),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
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
        }
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }
}




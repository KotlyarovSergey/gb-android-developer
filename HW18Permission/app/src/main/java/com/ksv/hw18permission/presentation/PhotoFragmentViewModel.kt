package com.ksv.hw18permission.presentation

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss"
class PhotoFragmentViewModel: ViewModel() {
    private var _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted get() = _isPermissionGranted.asStateFlow()
    private var _photoUri = MutableStateFlow<Uri?>(null)
    val photoUri get() = _photoUri.asStateFlow()


    fun setPermissionState(isGranted: Boolean) {
        _isPermissionGranted.value = isGranted
    }

    private fun addToBase(uri: Uri){

    }

//    fun takePhotoUri(uri: Uri?){
//        _photoUri.value = uri
//    }

    private val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
        .format(System.currentTimeMillis())
    private lateinit var executor: Executor

    fun takePhoto(imageCapture: ImageCapture, context: Context){
        executor = ContextCompat.getMainExecutor(context)
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).build()

        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    _photoUri.value = outputFileResults.savedUri
                }

                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )
    }

}
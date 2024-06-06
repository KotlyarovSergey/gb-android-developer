package com.ksv.hw18permission.presentation

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksv.hw18permission.data.PhotoItemDao
import com.ksv.hw18permission.entity.PhotoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

class PhotoFragmentViewModel(private val photoItemDao: PhotoItemDao) : ViewModel() {
    private var _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted get() = _isPermissionGranted.asStateFlow()
    private var _photoUri = MutableStateFlow<Uri?>(null)
    val photoUri get() = _photoUri.asStateFlow()
    private lateinit var executor: Executor

    fun setPermissionState(isGranted: Boolean) {
        _isPermissionGranted.value = isGranted
    }

    fun takePhoto(imageCapture: ImageCapture, context: Context){
        executor = ContextCompat.getMainExecutor(context)
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis())
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
                    addToDatabase()
                }
                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                }
            }
        )
    }

    private fun addToDatabase(){
        val date = SimpleDateFormat(DATE_FORMAT, Locale.US).format(System.currentTimeMillis())
        val photo = PhotoItem(
            uri = photoUri.value.toString(),
            date = date
        )
        viewModelScope.launch {
            photoItemDao.insert(photo)
        }
    }

    companion object{
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss"
        private const val DATE_FORMAT = "yyyy-MM-dd"
    }
}
package com.ksv.hw18permission.presentation

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ksv.hw18permission.databinding.FragmentPhotoBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss"
class PhotoFragment : Fragment() {
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            startCamera()
//            Toast.makeText(requireContext(), "permission is $isGranted", Toast.LENGTH_SHORT).show()
        }
    private var imageCapture: ImageCapture? = null
    private lateinit var executor: Executor
    private val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
        .format(System.currentTimeMillis())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        executor = ContextCompat.getMainExecutor(requireContext())
        checkPermission()

        binding.photoButton.setOnClickListener {
//            requireActivity().supportFragmentManager.popBackStack()
            takePhoto()
        }
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }

        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            ).build()

        imageCapture.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Photo saved on: ${outputFileResults.savedUri}",
//                        Toast.LENGTH_SHORT
//                    ).show()

                    Glide.with(requireContext())
                        .load(outputFileResults.savedUri)
                        .circleCrop()
                        .into(binding.imagePreview)
                }

                override fun onError(exception: ImageCaptureException) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Photo failed ${exception.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    exception.printStackTrace()
                }
            }
        )    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
//            Toast.makeText(requireContext(), "permission is Granted", Toast.LENGTH_SHORT).show()
        } else {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(binding.viewFinder.surfaceProvider)
            imageCapture = ImageCapture.Builder().build()

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                imageCapture
            )
        }, executor)
    }




}
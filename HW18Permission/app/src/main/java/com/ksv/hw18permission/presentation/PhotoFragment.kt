package com.ksv.hw18permission.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ksv.hw18permission.data.App
import com.ksv.hw18permission.data.PhotoItemDao
import com.ksv.hw18permission.databinding.FragmentPhotoBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.Executor


class PhotoFragment : Fragment() {
//    private val viewModel: PhotoFragmentViewModel by viewModels()
    private val viewModel: PhotoFragmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val photoItemDao: PhotoItemDao = (requireActivity().application as App).db.photoDao()
                return PhotoFragmentViewModel(photoItemDao) as T
            }
        }
    }
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            viewModel.setPermissionState(isGranted)
            if (isGranted) {
                startCamera()
            }
        }
    private var imageCapture: ImageCapture? = null
    private lateinit var executor: Executor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        checkPermission()

        binding.photoButton.setOnClickListener {
            takePhoto()
        }

        viewModel.photoUri.onEach {uri ->
            Glide
                .with(requireContext())
                .load(uri)
                .circleCrop()
                .into(binding.imagePreview)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        viewModel.takePhoto(imageCapture, requireContext())
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.setPermissionState(true)
            startCamera()
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
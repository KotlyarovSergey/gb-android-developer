package com.ksv.hw17recyclerview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ksv.hw17recyclerview.databinding.FragmentFullPhotoBinding
import com.ksv.hw17recyclerview.entity.PhotoItem
import com.ksv.hw17recyclerview.util.LinkCorrector


class FullPhotoFragment : Fragment() {
    private var url: String? = null
    private var sol: String? = null
    private var date: String? = null
    private var rover: String? = null
    private var camera: String? = null

    private var _binding: FragmentFullPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(PARAM_URL)
            sol = it.getString(PARAM_SOL)
            date = it.getString(PARAM_DATE)
            rover = it.getString(PARAM_ROVER)
            camera = it.getString(PARAM_CAMERA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        url?.let {
            Glide
                .with(this)
                .load(LinkCorrector.change(url!!))
                .into(binding.fullImage)
        }

        binding.tvCamera.text = "Camera: $camera"
        binding.tvDate.text = "Date: $date"
        binding.tvSol.text = "Sol: $sol"
        binding.tvRover.text = "Rover: $rover"

    }

    companion object {
        const val PARAM_URL = "PARAM_URL"
        const val PARAM_CAMERA = "PARAM_CAMERA"
        const val PARAM_DATE = "PARAM_DATE"
        const val PARAM_ROVER = "PARAM_ROVER"
        const val PARAM_SOL = "PARAM_SOL"
    }
}
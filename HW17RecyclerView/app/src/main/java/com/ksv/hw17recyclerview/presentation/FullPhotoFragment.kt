package com.ksv.hw17recyclerview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ksv.hw17recyclerview.databinding.FragmentFullPhotoBinding
import com.ksv.hw17recyclerview.util.LinkCorrector


class FullPhotoFragment : Fragment() {
    private var url: String? = null
    private var _binding: FragmentFullPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(PARAM_URL)
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

    }

    companion object {
        const val PARAM_URL = "PARAM_URL"
    }
}
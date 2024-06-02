package com.ksv.hw17recyclerview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.ksv.hw17recyclerview.data.PhotosRepository
import com.ksv.hw17recyclerview.databinding.FragmentPhotosListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PhotosListFragment : Fragment() {
    private val viewModel : PhotoListViewModel by viewModels()
    private var _binding: FragmentPhotosListBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = photoAdapter

        viewModel.photos.onEach {
            photoAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach { isLoading ->
            binding.progress.visibility = if(isLoading)
                View.VISIBLE
            else
                View.GONE
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }
}
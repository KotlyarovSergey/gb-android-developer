package com.ksv.hw17recyclerview.presentation

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksv.hw17recyclerview.R
import com.ksv.hw17recyclerview.databinding.FragmentPhotosListBinding
import com.ksv.hw17recyclerview.entity.PhotoItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PhotosListFragment : Fragment() {
    private val viewModel : PhotoListViewModel by viewModels()
    private var _binding: FragmentPhotosListBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotoAdapter{photoItem -> onItemClick(photoItem) }

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
        binding.recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

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

    private fun onItemClick(item: PhotoItem){
        parentFragmentManager.commit {
            val bundle = bundleOf(FullPhotoFragment.PARAM_URL to item.url)
            replace<FullPhotoFragment>(R.id.fragmentContainer, args = bundle)
            addToBackStack(FullPhotoFragment::javaClass.name)
        }
    }
}
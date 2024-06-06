package com.ksv.hw18permission.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksv.hw18permission.R
import com.ksv.hw18permission.data.App
import com.ksv.hw18permission.data.PhotoItemDao
import com.ksv.hw18permission.databinding.FragmentViewerBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ViewerFragment : Fragment() {
    private var _binding: FragmentViewerBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotoAdapter()
    private val viewModel: ViewerFragmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val photoItemDao: PhotoItemDao =
                    (requireActivity().application as App).db.photoDao()
                return ViewerFragmentViewModel(photoItemDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingButton.setOnClickListener {
            parentFragmentManager.commit {
                replace<PhotoFragment>(R.id.frame_container)
                addToBackStack(PhotoFragment::javaClass.name)
            }
        }
        binding.recyclerView.adapter = photoAdapter
        viewModel.photos.onEach {
            photoAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}
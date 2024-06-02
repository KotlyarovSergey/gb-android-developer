package com.ksv.hw17recyclerview.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import coil.load
import com.bumptech.glide.Glide
import com.ksv.hw17recyclerview.R
import com.ksv.hw17recyclerview.data.PhotosRepository
import com.ksv.hw17recyclerview.databinding.FragmentRecyclerBinding
import com.ksv.hw17recyclerview.util.LinkCorrector
import kotlinx.coroutines.launch

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = photoAdapter

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                val repo = PhotosRepository()
//                val photo = repo.getPhotos(1000)
                val photo = repo.getPhotos(3344)
//                val txt = photo?.photos?.first()?.url ?: "null"
                binding.textView.text = photo?.photos?.size.toString()

                photoAdapter.setData(photo?.photos!!)


//


            }
        }
    }
}
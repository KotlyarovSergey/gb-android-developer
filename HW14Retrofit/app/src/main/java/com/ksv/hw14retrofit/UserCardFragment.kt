package com.ksv.hw14retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.ksv.hw14retrofit.databinding.FragmentUserCardBinding
import kotlinx.coroutines.launch

class UserCardFragment : Fragment() {
    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.firstLaunch()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCardBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.refreshButton.setOnClickListener {
            viewModel.getDataFromServer()
        }

        listenToModel()
    }



    private fun listenToModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.person
                    .collect { person ->
                        if(person != null)
                            updateViews(person.results.first())
                    }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state
                    .collect{state ->
                        if(state == State.Error)
                            clearViews()
                    }
            }
        }
    }

    private fun updateViews(person: Result){
        val pictureUrl = person.pictureUrl.large
        val gender = person.gender
        val nameSet = person.name
        val name = "${nameSet.title} ${nameSet.first} ${nameSet.last}"
        val email = person.email
        val locationData = person.location
        val location =
            "${locationData.street.name} ${locationData.street.number},\n" +
                    "${locationData.city},\n" +
                    "${locationData.state}, \n" +
                    "${locationData.country}."

        binding.photoImage.load(pictureUrl)
        binding.tvGender.text = gender
        binding.tvName.text = name
        binding.tvEmail.text = email
        binding.tvLocation.text = location
    }

    private fun clearViews(){
        val context = requireContext()
        binding.photoImage.load(requireActivity().getDrawable(R.mipmap.ic_launcher))
        binding.tvGender.text = context.getString(R.string.gender_default)
        binding.tvName.text = context.getString(R.string.name_default)
        binding.tvEmail.text = context.getString(R.string.email_default)
        binding.tvLocation.text = context.getString(R.string.location_default)
    }

}
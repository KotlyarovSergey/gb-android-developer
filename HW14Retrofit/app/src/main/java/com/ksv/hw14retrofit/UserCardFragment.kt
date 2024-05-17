package com.ksv.hw14retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.ksv.hw14retrofit.databinding.FragmentUserCardBinding
import kotlinx.coroutines.launch

class UserCardFragment : Fragment() {
    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshDataCoroutine()
        binding.refreshButton.setOnClickListener {
            refreshDataCoroutine()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun refreshDataCoroutine(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val response = RetrofitInstance.getPersonApiResponse.getPersonData()
//                delay(2000L)
                if(response.isSuccessful){
                    val person = response.body()
                    if(person != null)
                        updateViews(person.results.first())
                } else {
                    Log.d("ksvlog", "Person getting failure")
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
}
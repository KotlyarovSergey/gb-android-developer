package com.ksv.hw14retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.ksv.hw14retrofit.databinding.FragmentUserCardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserCardFragment : Fragment() {
    private var _binding: FragmentUserCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserCardBinding.inflate(layoutInflater)
//        return inflater.inflate(R.layout.fragment_user_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshData()
        binding.refreshButton.setOnClickListener {
            refreshData()
        }
    }

    private fun refreshData() {
//        val t = System.currentTimeMillis()
//        binding.tvResponse.text = t.toString()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val current = LocalDateTime.now().format(formatter)
        Log.d("ksvlog", current)
        binding.tvResponse.text = current

        RetrofitInstance.getPersonApi.getPersonData()
            .enqueue(object : Callback<Person> {
                override fun onResponse(
                    call: Call<Person>,
                    response: Response<Person>
                ) {
                    //binding.tvResponse.text = response.body()?.info?.seed
//                    val gender = response.body()?.results?.first()?.gender
//                    val name = "${response.body()?.results?.first()?.name?.title} " +
//                            "${response.body()?.results?.first()?.name?.first} " +
//                            "${response.body()?.results?.first()?.name?.last}"

//                    val email = response.body()?.results?.first()?.email
//                    val pictureUrl = response.body()?.results?.first()?.pictureUrl?.large

                    val person = response.body()?.results?.first()
                    val pictureUrl = person?.pictureUrl?.large
                    val gender = person?.gender
                    val nameSet = person?.name
                    val name = "${nameSet?.title} ${nameSet?.first} ${nameSet?.last}"
                    val email = person?.email
                    val locationData = person?.location
                    val location =
                        "${locationData?.street?.name} ${locationData?.street?.number},\n" +
                                "${locationData?.city},\n" +
                                "${locationData?.state}, \n" +
                                "${locationData?.country}"



                    binding.photoImage.load(pictureUrl)
                    binding.tvResponse.text = gender
                    binding.tvName.text = name
                    binding.tvEmail.text = email
                    binding.tvLocation.text = location
                }

                override fun onFailure(call: Call<Person>, t: Throwable) {
                    binding.tvResponse.text = "get Peron failure"
                }

            }
            )
    }
}
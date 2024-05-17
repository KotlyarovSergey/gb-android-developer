package com.ksv.lesson14

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class RetrofitFragment : Fragment() {
    private lateinit var tv: TextView
    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_retrofit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv = requireActivity().findViewById<TextView>(R.id.retroHead)
        imageView = requireActivity().findViewById<ImageView>(R.id.image)

//        methodOne()
//        methodTwo()
//        methodThree()
        methodFour()

    }

    private fun methodOne() {
        val tv = requireActivity().findViewById<TextView>(R.id.retroHead)
        val imageView = requireActivity().findViewById<ImageView>(R.id.image)

        RetrofitInstance.searchImageApi.getCatImageList()
            .enqueue(object : Callback<List<CatMoshi>> {
                override fun onResponse(
                    call: Call<List<CatMoshi>>,
                    response: Response<List<CatMoshi>>
                ) {
                    if (response.isSuccessful) {
                        val cat = response.body()?.first() ?: return
                        val status = response.code()
                        Log.d("ksvlog", "response code: $status")

                        imageView.load(cat.url)

                        tv.text = cat.id
                    } else {
                        tv.text = "response no successful"
                    }
                }

                override fun onFailure(call: Call<List<CatMoshi>>, t: Throwable) {
                    Log.d("ksvlog", "something went wrong", t)
                }
            }
            )
    }

    private fun methodTwo(){
        val tv = requireActivity().findViewById<TextView>(R.id.retroHead)
        val imageView = requireActivity().findViewById<ImageView>(R.id.image)

        thread {
            val response = RetrofitInstance.searchImageApi.getCatImageList().execute()
            imageView.load(response.body()?.first()?.url)
            tv.text = response.body()?.first()?.id
        }
    }

    private fun methodThree(){
        val tv = requireActivity().findViewById<TextView>(R.id.retroHead)
        val imageView = requireActivity().findViewById<ImageView>(R.id.image)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val catImageList = RetrofitInstance.searchImageApiCorutine.getCatImageList()
                imageView.load(catImageList.first().url)
                tv.text = catImageList.first().id
            }
        }
    }

    private fun methodFour(){
//        val tv = requireActivity().findViewById<TextView>(R.id.retroHead)
//        val imageView = requireActivity().findViewById<ImageView>(R.id.image)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                val response = RetrofitInstance.searchImageApiResponse.getCatImageList()
                imageView.load(response.body()?.first()?.url)
                tv.text = response.body()?.first()?.id
            }
        }
    }
}
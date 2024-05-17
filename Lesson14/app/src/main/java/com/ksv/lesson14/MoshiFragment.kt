package com.ksv.lesson14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.moshi.Moshi

class MoshiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moshi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvFromJson = requireActivity().findViewById<TextView>(R.id.tvMoshiFromJson)
        val tvToJson = requireActivity().findViewById<TextView>(R.id.tvMoshiToJson)

        val jsonCat = """{
            "id":12,
            "url":"FromJson"
        }""".trimIndent()

        val catImageAdapter = Moshi.Builder().build().adapter(CatMoshi::class.java)

        val catMoshi = catImageAdapter.fromJson(jsonCat) ?: error("Unable to convert")
        tvFromJson.text = catMoshi.toString()

        val deserializableString = catImageAdapter.toJson(catMoshi)
        tvToJson.text = deserializableString
    }
}
package com.ksv.lesson14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson

class GsonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonCat = """{
            "id":12,
            "url":"FromJson"
        }""".trimIndent()

        val catImageAdapter = Gson().getAdapter(CatGson::class.java)
        val catGson = catImageAdapter.fromJson(jsonCat)
        val tvFromGson = requireActivity().findViewById<TextView>(R.id.tvGsonFromJson)
        tvFromGson.text = catGson.toString()

        val deserializedString = catImageAdapter.toJson(catGson)
        val tvToGson = requireActivity().findViewById<TextView>(R.id.tvGsonToJson)
        tvToGson.text = deserializedString

    }

}
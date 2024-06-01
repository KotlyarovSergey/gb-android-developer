package com.ksv.lesson17recyclerview

import com.ksv.lesson17recyclerview.databinding.FragmentFirstBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.random.Random

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.recyclerView.layoutManager = LinearLayoutManager(
//            requireContext(),
//            LinearLayoutManager.VERTICAL,
//            false
//        )
        binding.recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            3,
            GridLayoutManager.VERTICAL,
            false
        )

        val data = (0..100).map { it.toString() }
        val adapter = MySimpleAdapter(data)
        binding.recyclerView.adapter = adapter


        binding.insButton.setOnClickListener {
            val item = Random.nextInt(100, 200).toString()
            val position = 5
            adapter.addItem(index = position, value = item)
        }

        binding.delButton.setOnClickListener {
            adapter.removeItem(2)
        }

        binding.setButton.setOnClickListener {
            val data = List<String>(100) { Random.nextInt(100, 200).toString() }
            adapter.setData(data)
        }
    }

}
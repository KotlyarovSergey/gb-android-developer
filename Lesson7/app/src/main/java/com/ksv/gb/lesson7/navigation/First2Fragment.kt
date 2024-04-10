package com.ksv.gb.lesson7.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ksv.gb.lesson7.R
import com.ksv.gb.lesson7.databinding.FragmentFirst2Binding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment() {

    private var _binding: FragmentFirst2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val sendInt = 468465
            val action = First2FragmentDirections.actionFirst2FragmentToSecondFragment()
            action.agrInt1 = sendInt
//            findNavController().navigate(resId = R.id.action_First2Fragment_to_SecondFragment)
            findNavController().navigate(directions = action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
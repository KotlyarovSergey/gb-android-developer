package com.ksv.hw16architect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ksv.hw16architect.data.UsefulActivitiesRepository
import com.ksv.hw16architect.databinding.FragmentExerciseBinding
import com.ksv.hw16architect.domain.GetUsefulActivityUseCase

class ExerciseFragment : Fragment() {
    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!
//    private val viewModel: MainViewModel by viewModels{
//        DaggerAppComponent.create().mainViewModel()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = UsefulActivitiesRepository()
        val useCase = GetUsefulActivityUseCase(repo)
//        binding.viewModel = DaggerAppComponent.create().mainViewModel()
//        binding.viewModel = viewModel
//        binding.viewModel = MainViewModel(useCase)
        binding.lifecycleOwner = viewLifecycleOwner
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.ksv.gb.hw12mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.ksv.gb.hw12mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        restoreSavedState(savedInstanceState)
        setViewsListeners()
        setViewModelListeners()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SEARCH_BUTTON_ENABLED, binding.searchButton.isEnabled)
        outState.putBoolean(SEARCH_EDIT_ENABLED, binding.searchEdit.isEnabled)
        outState.putString(SEARCH_RESULT, binding.textView.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun restoreSavedState(savedInstanceState: Bundle?){
        binding.searchButton.isEnabled =
            savedInstanceState?.getBoolean(SEARCH_BUTTON_ENABLED, false) ?: false
        binding.searchEdit.isEnabled =
            savedInstanceState?.getBoolean(SEARCH_EDIT_ENABLED, true) ?: true
        val defaultResult = getString(R.string.tv_default)
        binding.textView.text =
            savedInstanceState?.getString(SEARCH_RESULT, defaultResult) ?: defaultResult
    }

    private fun setViewsListeners(){
        binding.searchEdit.addTextChangedListener {
            viewModel.searchEditChange(it.toString())
        }

        binding.searchButton.setOnClickListener {
            val searchedText = binding.searchEdit.text.toString()
            viewModel.onSearchClick(searchedText)
        }
    }

    private fun setViewModelListeners(){
        lifecycleScope.launchWhenStarted {
            viewModel.state
                .collect { state ->
                    setViewsWithSearchState(state)
                }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.result
                .collect {
                    showResult(it)
                }
        }
    }

    private fun showResult(result: String?){
        binding.textView.text = when (result) {
            null -> getString(R.string.tv_default)
            else -> {
                StringBuilder()
                    .append(getString(R.string.tv_result_prefix))
                    .append(result)
                    .append(getString(R.string.tv_result_postfix))
                    .toString()
            }
        }
    }

    private fun setViewsWithSearchState(state: State) {
        when (state) {
            State.Search -> {
                with(binding) {
                    progress.visibility = View.VISIBLE
                    searchEdit.isEnabled = false
                    searchButton.isEnabled = false
                }
            }
            State.Normal -> {
                with(binding) {
                    searchEdit.isEnabled = true
                    searchButton.isEnabled = true
                    progress.visibility = View.GONE
                }
            }
            State.Insufficient -> {
                with(binding) {
                    searchEdit.isEnabled = true
                    searchButton.isEnabled = false
                    progress.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        private const val SEARCH_BUTTON_ENABLED = "search_button_enable"
        private const val SEARCH_EDIT_ENABLED = "search_edit_enable"
        private const val SEARCH_RESULT= "search_result"
    }
}
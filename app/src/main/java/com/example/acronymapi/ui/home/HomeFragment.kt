package com.example.acronymapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.acronymapi.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.acronymList.adapter = AcronymAdapter()

        binding.editTextField.doOnTextChanged { _, _, _, _ ->
            if(!viewModel.checkLength()){
                binding.textField.isErrorEnabled = true
                binding.textField.error = "Seacrh Term must be 2 Characters or More"
                binding.searchButton.isEnabled = false
            }else{
                binding.textField.isErrorEnabled = false
                binding.searchButton.isEnabled = true
            }
        }

        viewModel.goodSearch.observe(viewLifecycleOwner){
            if(viewModel.goodSearch.value == false){
                binding.badSearch.visibility = View.VISIBLE
                binding.acronymList.visibility = View.GONE
            }else {
                binding.badSearch.visibility = View.GONE
                binding.acronymList.visibility = View.VISIBLE
            }
        }

        binding.searchButton.setOnClickListener {
            viewModel.getAcronymResults()
        }

        return binding.root
    }
}

package com.github.lastachkin.employeeapp.presentation.view.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.FragmentBottomSheetBinding
import com.github.lastachkin.employeeapp.presentation.viewmodel.BottomSheetViewModel
import com.github.lastachkin.employeeapp.util.Constants
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: BottomSheetViewModel
    private lateinit var binding: FragmentBottomSheetBinding

    companion object{
        val isPausedState = MutableLiveData<Boolean>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.optionsObservable.observe(viewLifecycleOwner) { options ->
            if (options != null) {
                binding.optionAlphabet.isChecked = options[Constants.OPTION_ALPHABET] ?: false
                binding.optionBirthDate.isChecked = options[Constants.OPTION_BIRTH_DATE] ?: false
            }
        }

        binding = FragmentBottomSheetBinding.bind(
            inflater.inflate(
                R.layout.fragment_bottom_sheet,
                container
            )
        )

        val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
        binding.radioGroup.startAnimation(anim)

        binding.optionAlphabet.setOnClickListener { dismiss() }

        binding.optionBirthDate.setOnClickListener { dismiss() }

        binding.arrowBack.setOnClickListener { dismiss() }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSortingOptions()

        isPausedState.value = false
    }

    override fun onPause() {
        super.onPause()

        viewModel.setSortingOptions(
            binding.optionAlphabet.isChecked,
            binding.optionBirthDate.isChecked
        )

        isPausedState.value = true
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme
}
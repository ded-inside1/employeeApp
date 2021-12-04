package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.FragmentBottomSheetBinding
import com.github.lastachkin.employeeapp.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.bind(
            inflater.inflate(
                R.layout.fragment_bottom_sheet,
                container
            )
        )

        return binding.root
    }

    // TODO: 04.12.2021 move logic to viewmodel
    override fun onResume() {
        super.onResume()

        binding.optionAlphabet.isChecked =
            requireActivity().getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)
                .getBoolean(Constants.OPTION_ALPHABET, false)

        binding.optionBirthDate.isChecked =
            requireActivity().getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)
                .getBoolean(Constants.OPTION_BIRTH_DATE, false)
    }

    // TODO: 04.12.2021 move logic to viewmodel
    override fun onPause() {
        super.onPause()

        val sharedPrefsEditor =
            requireActivity().getSharedPreferences(Constants.SHARED_PREFS_NAME, 0).edit()

        sharedPrefsEditor.putBoolean(Constants.OPTION_ALPHABET, binding.optionAlphabet.isChecked)

        sharedPrefsEditor.putBoolean(Constants.OPTION_BIRTH_DATE, binding.optionBirthDate.isChecked)

        sharedPrefsEditor.apply()
    }
}
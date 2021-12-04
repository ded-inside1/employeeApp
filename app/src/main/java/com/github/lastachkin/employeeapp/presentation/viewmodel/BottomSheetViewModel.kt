package com.github.lastachkin.employeeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.service.Repository

class BottomSheetViewModel: ViewModel() {

    val optionsObservable = MutableLiveData<Map<String, Boolean>>()

    internal fun getSortingOptions() {
        optionsObservable.value = Repository.getSortingOptions()
    }

    internal fun setSortingOptions(optionAlphabet: Boolean, optionBirthDate: Boolean) =
        Repository.setSortingOptions(optionAlphabet, optionBirthDate)
}
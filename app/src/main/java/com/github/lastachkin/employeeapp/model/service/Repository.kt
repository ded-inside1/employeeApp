package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Repository {
    private val employeeAPI: EmployeeAPI = EmployeeAPI.create()
    private val sharedPrefs =
        EmployeeApp.instance.getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)

    fun getEmployees(department: EmployeeListType) = employeeAPI.getEmployees()
        .subscribeOn(Schedulers.io())
        .map { it.employees }
        .flatMapIterable { it }
        .filter {
            if (department == EmployeeListType.All) true else it.department == department.title
        }
        .toList()
        .observeOn(AndroidSchedulers.mainThread())

    fun getSortingOptions() = mapOf(
        Constants.OPTION_ALPHABET to sharedPrefs.getBoolean(
            Constants.OPTION_ALPHABET,
            false
        ),
        Constants.OPTION_BIRTH_DATE to sharedPrefs.getBoolean(
            Constants.OPTION_BIRTH_DATE,
            false
        )
    )

    fun setSortingOptions(optionAlphabet: Boolean, optionBirthDate: Boolean) =
        sharedPrefs.edit().apply {
            putBoolean(
                Constants.OPTION_ALPHABET,
                optionAlphabet
            )
            putBoolean(
                Constants.OPTION_BIRTH_DATE,
                optionBirthDate
            )
            apply()
        }!!
}
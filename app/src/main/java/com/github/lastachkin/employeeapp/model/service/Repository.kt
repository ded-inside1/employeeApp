package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Repository : BaseRepository() {
    private val employeeAPI: EmployeeAPI = EmployeeAPI.create()
    private val sharedPrefs =
        EmployeeApp.instance.getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)

    suspend fun getEmployees(department: EmployeeListType) : List<Employee>?{
        val movieResponse = safeApiCall(
            call = {employeeAPI.getEmployeesAsync().await()},
            errorMessage = "Error Fetching Employees"
        )

        return movieResponse?.employees?.filter {
            if (department == EmployeeListType.All) true else it.department == department.title
        }?.toList()
    }

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
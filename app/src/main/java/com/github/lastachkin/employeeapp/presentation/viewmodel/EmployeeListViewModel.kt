package com.github.lastachkin.employeeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.Repository
import com.github.lastachkin.employeeapp.util.Constants
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class EmployeeListViewModel : ViewModel() {
    var employeeListObservable = MutableLiveData<List<Employee>>()
    private val employeeList = mutableListOf<Employee>()

    internal fun getData(department: EmployeeListType) {
        val sortingOption = Repository.getSortingOptions().filter { it.value }

        CoroutineScope(Job() + Dispatchers.Default).launch {
            val employees = Repository.getEmployees(department)

            employeeListObservable.postValue(employees?.sortedBy {
                if (sortingOption.keys.contains(Constants.OPTION_ALPHABET)) it.lastName
                else it.birthday
            })

            employeeList.clear()

            employeeList.addAll(employees?.sortedBy {
                if (sortingOption.keys.contains(Constants.OPTION_ALPHABET)) it.lastName
                else it.birthday
            }!!)
        }
    }

    internal fun filterData(value: String) {
        employeeListObservable.value = employeeList.filter {
            it.firstName?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true ||
                it.lastName?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true ||
                it.userTag?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true
        }
    }
}
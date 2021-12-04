package com.github.lastachkin.employeeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.Repository
import com.github.lastachkin.employeeapp.util.Constants
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EmployeeListViewModel : ViewModel() {
    var employeeListObservable = MutableLiveData<List<Employee>>()
    private val employeeList = mutableListOf<Employee>()
    private val compositeDisposable = CompositeDisposable()

    internal fun getData(department: EmployeeListType) {
        val sortingOption = Repository.getSortingOptions().filter { it.value }

        compositeDisposable.add(Repository.getEmployees(department).subscribe({
            employeeListObservable.value = it.sortedBy { item ->
                if (sortingOption.keys.contains(Constants.OPTION_ALPHABET)) item.lastName
                else item.birthday
            }
            employeeList.clear()
            employeeList.addAll(it.sortedBy { item ->
                if (sortingOption.keys.contains(Constants.OPTION_ALPHABET)) item.lastName
                else item.birthday
            })
        }, { it.printStackTrace() }))
    }

    internal fun filterData(value: String) {
        employeeListObservable.value = employeeList.filter {
            it.firstName?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true ||
                it.lastName?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true ||
                it.userTag?.toLowerCase(Locale.ROOT)?.contains(value.toLowerCase(Locale.ROOT)) == true
        }
    }
}
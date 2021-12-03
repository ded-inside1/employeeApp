package com.github.lastachkin.employeeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.Repository
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EmployeeListViewModel : ViewModel() {
    var employeeListObservable = MutableLiveData<List<Employee>>()
    private val employeeList = mutableListOf<Employee>()
    private val compositeDisposable = CompositeDisposable()

    internal fun getData(department: EmployeeListType) {
        compositeDisposable.add(Repository.getEmployees(department).subscribe({
            // TODO: 03.12.2021 sorting?
            employeeListObservable.value = it.sortedBy { it.lastName }
            employeeList.clear()
            employeeList.addAll(it.sortedBy { it.lastName })
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
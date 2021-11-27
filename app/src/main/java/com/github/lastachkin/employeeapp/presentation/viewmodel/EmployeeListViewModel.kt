package com.github.lastachkin.employeeapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.Repository
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class EmployeeListViewModel : ViewModel() {
    private val employeeList = mutableListOf<Employee>()
    var employeeListObservable = MutableLiveData<List<Employee>>()
    private val compositeDisposable = CompositeDisposable()

    internal fun getData(department: EmployeeListType) {
        compositeDisposable.add(Repository.getEmployees(department).subscribe({
            employeeListObservable.value = it
            employeeList.clear()
            employeeList.addAll(it)
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
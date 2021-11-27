package com.github.lastachkin.employeeapp.presentation.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.Repository

class EmployeeListViewModel : ViewModel() {
    var employeeListObservable = MutableLiveData<List<Employee>>()

    @SuppressLint("CheckResult")
    internal fun getData(department: EmployeeListType) {
        Repository.getInstance().getEmployees(department).subscribe({
            employeeListObservable.value = it },
                {
                    it.printStackTrace() })
    }
}
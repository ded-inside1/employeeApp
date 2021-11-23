package com.github.lastachkin.employeeapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.service.Repository

class EmployeeListViewModel: ViewModel() {
    var employeeListObservable: LiveData<List<Employee>>? = null

    init {
        employeeListObservable = Repository.getInstance().getEmployees()
    }

//    fun getEmployeesObservable(): LiveData<List<Employee>>{
//        return employeeListObservable!!
//    }
}
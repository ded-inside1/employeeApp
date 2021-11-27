package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Repository{
    private val employeeAPI: EmployeeAPI = EmployeeAPI.create()

    fun getEmployees(department: EmployeeListType) = employeeAPI.getEmployees()
        .subscribeOn(Schedulers.io())
        .map { it.employees }
        .flatMapIterable { it }
        .filter {
            if (department == EmployeeListType.All) true else it.department == department.title
        }
        .toList()
        .observeOn(AndroidSchedulers.mainThread())

}
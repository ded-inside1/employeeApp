package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.model.entity.Employee
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository(
        private val employeeAPI: EmployeeAPI = EmployeeAPI.create()
) {

    fun getEmployees(department: EmployeeListType): Single<List<Employee>> {
        val a = employeeAPI.getEmployees().subscribeOn(Schedulers.io())
                .map { it.employees }
                .flatMapIterable { it }
                .filter {
                    if (department == EmployeeListType.All) true else it.department == department.title }
                .toList()
                .doOnSuccess {
                    println(it.size)
                    println(department)
                }
                .observeOn(AndroidSchedulers.mainThread())
        return a
    }

    companion object {
        fun getInstance(): Repository {
            return Repository()
        }
    }
}
package com.github.lastachkin.employeeapp.model.entity

import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("items")
    var employees: List<Employee>? = null
)
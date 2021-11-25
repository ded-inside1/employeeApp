package com.github.lastachkin.employeeapp.util

import com.github.lastachkin.employeeapp.model.entity.EmployeeListType

object Constants {
    val departments = mapOf(EmployeeListType.Designers.name to "design",
                            EmployeeListType.Analysts.name to "analytics",
                            EmployeeListType.Managers.name to "management",
                            EmployeeListType.Android.name to "android",
                            EmployeeListType.IOS.name to "ios",
                            EmployeeListType.QA.name to "qa",
                            EmployeeListType.Backend.name to "back_office",
                            EmployeeListType.Frontend.name to "frontend",
                            EmployeeListType.HR.name to "hr",
                            EmployeeListType.PR.name to "pr",
                            EmployeeListType.Support.name to "support")
}
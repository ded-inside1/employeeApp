package com.github.lastachkin.employeeapp.model.entity

enum class EmployeeListType(val title: String) {
    All("all"),
    Designers("design"),
    Analysts("analytics"),
    Managers("management"),
    Android("android"),
    IOS("ios"),
    QA("qa"),
    Backend("back_office"),
    Frontend("frontend"),
    HR("hr"),
    PR("pr"),
    Support("support")
}
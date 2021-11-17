package com.github.lastachkin.employeeapp

import android.app.Application
import android.content.Context

class EmployeeApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: EmployeeApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context = EmployeeApp.applicationContext()
    }
}
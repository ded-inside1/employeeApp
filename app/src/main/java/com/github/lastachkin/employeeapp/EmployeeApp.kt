package com.github.lastachkin.employeeapp

import android.app.Application
import android.content.Context
import com.github.lastachkin.employeeapp.util.Constants

class EmployeeApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: EmployeeApp? = null

        fun applicationContext(): Context = instance!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        getSharedPreferences(Constants.SHARED_PREFS_NAME, 0).edit().apply {
            clear()
            apply()
        }
    }
}
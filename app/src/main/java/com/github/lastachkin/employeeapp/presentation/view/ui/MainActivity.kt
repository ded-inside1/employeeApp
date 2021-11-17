package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.lastachkin.employeeapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val repository = Repository.getInstance()
//        repository.getEmployees()

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = EmployeeListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "ProjectListFragment").commit()
        }
    }
}
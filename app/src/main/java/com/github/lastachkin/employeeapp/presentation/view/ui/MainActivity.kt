package com.github.lastachkin.employeeapp.presentation.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.model.service.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository.getInstance()
        repository.getEmployees()
    }
}
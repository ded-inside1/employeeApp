package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.ActivityMainBinding
import com.github.lastachkin.employeeapp.presentation.view.adapter.FragmentPagerAdapterImpl
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.viewpager.adapter = FragmentPagerAdapterImpl(this)
//        TabLayoutMediator(
//            binding.tabLayout, binding.viewpager
//        ) { tab, position -> tab.text = "Tab " + (position + 1) }.attach()

        if (savedInstanceState == null) {
            val fragment = EmployeeListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "ProjectListFragment").commit()
        }
    }
}
package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val binding = ActivityMainBinding.inflate(layoutInflater)
        val viewPager = findViewById<View>(R.id.viewPager) as ViewPager2
        val tabs = findViewById<View>(R.id.tabLayout) as TabLayout
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)

        viewPager.adapter = EmployeeViewPagerAdapter(this)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = when(position){
                0 -> "All"
                1 -> "Designers"
                2 -> "Managers"
                3 -> "Developers"
                4 -> "Analysts"
                else -> "Template"
            }
        }.attach()

//        if (savedInstanceState == null) {
//            val fragment = EmployeeListFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, fragment, "ProjectListFragment").commit()
//        }
    }
}
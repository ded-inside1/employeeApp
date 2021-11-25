package com.github.lastachkin.employeeapp.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.ui.EmployeeListFragment

class EmployeeViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return EmployeeListFragment.newInstance(EmployeeListType.values()[position])
    }

    override fun getItemCount(): Int {
        return EmployeeListType.values().size
    }
}
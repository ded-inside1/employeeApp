package com.github.lastachkin.employeeapp.presentation.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.ui.EmployeeListFragment

class EmployeeViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return EmployeeListFragment.newInstance(EmployeeListType.All)
            1 -> return EmployeeListFragment.newInstance(EmployeeListType.Analysts)
            2 -> return EmployeeListFragment.newInstance(EmployeeListType.Designers)
            3 -> return EmployeeListFragment.newInstance(EmployeeListType.Developers)
            4 -> return EmployeeListFragment.newInstance(EmployeeListType.Managers)
        }
        return EmployeeListFragment.newInstance(EmployeeListType.All)
    }

    override fun getItemCount(): Int {
        return 5
    }
}
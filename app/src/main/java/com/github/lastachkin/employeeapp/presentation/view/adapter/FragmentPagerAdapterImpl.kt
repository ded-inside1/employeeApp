package com.github.lastachkin.employeeapp.presentation.view.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.lastachkin.employeeapp.presentation.view.ui.EmployeeListFragment

class FragmentPagerAdapterImpl(fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val tabTitles = arrayOf("All", "Designers", "Analysts", "Managers", "Developers")

    override fun getItemCount(): Int {
        return tabTitles.size
    }

//    override fun createFragment(position: Int): Fragment = EmployeeListFragment().apply {
//        arguments = bundleOf(
//            "color" to tabTitles[position],
//            "position" to position
//        )
//    }
    override fun createFragment(position: Int): Fragment{
        return EmployeeListFragment.getInstance()
    }

}
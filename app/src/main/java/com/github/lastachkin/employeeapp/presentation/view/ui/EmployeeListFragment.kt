package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeAdapter
import com.github.lastachkin.employeeapp.presentation.viewmodel.EmployeeListViewModel

class EmployeeListFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)

        viewModel.employeeListObservable!!.observe(viewLifecycleOwner) { employees ->
            if (employees != null) {
                binding.employeeList.adapter = EmployeeAdapter(employees)
                binding.loadingEmployees.visibility = View.GONE
            }
        }

        return binding.root
    }

    companion object{
        private const val ARG_LIST_TYPE = "LIST_TYPE"

        fun newInstance(employeeListType: EmployeeListType): EmployeeListFragment {
            val fragment = EmployeeListFragment()
            val args = Bundle()
            args.putSerializable(ARG_LIST_TYPE, employeeListType)
            fragment.arguments = args

            return fragment
        }
    }
}
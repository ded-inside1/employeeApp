package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeAdapter
import com.github.lastachkin.employeeapp.presentation.viewmodel.EmployeeListViewModel
import com.github.lastachkin.employeeapp.util.Constants

class EmployeeListFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)

        val departmentType = arguments?.getSerializable(ARG_LIST_TYPE)

        viewModel.employeeListObservable!!.observe(viewLifecycleOwner) { employees ->
            if (employees != null) {
                val employeesByDepartment = if(departmentType == EmployeeListType.All)
                    employees
                else
                    employees.filter {
                        it.department == Constants.departments[departmentType.toString()]
                    }

                binding.employeeList.adapter = EmployeeAdapter(employeesByDepartment, EmployeeListType.All.name)
                binding.loadingEmployees.visibility = View.GONE
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = activity?.findViewById<View>(R.id.searchView) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: 25.11.2021 set list adapter
                return false
            }
        })
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
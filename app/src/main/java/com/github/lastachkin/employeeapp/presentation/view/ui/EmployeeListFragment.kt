package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeAdapter
import com.github.lastachkin.employeeapp.presentation.viewmodel.EmployeeListViewModel

class EmployeeListFragment : Fragment() {

    private val rvAdapter = EmployeeAdapter()
    private var departmentType: EmployeeListType? = null
    private var viewModel: EmployeeListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        departmentType = arguments?.getSerializable(ARG_LIST_TYPE) as EmployeeListType

        binding.employeeList.adapter = rvAdapter

        viewModel?.employeeListObservable?.observe(viewLifecycleOwner) { employees ->
            if (employees != null) {
                rvAdapter.setData(employees)

                binding.loadingEmployees.visibility = View.GONE
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        val searchView = requireActivity().findViewById<SearchView>(R.id.searchView)

        searchView.setQuery("", false)

        departmentType?.let { viewModel?.getData(it) }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel?.filterData(it) }
                return false
            }
        })
    }

    companion object {
        private const val ARG_LIST_TYPE = "LIST_TYPE"

        fun newInstance(employeeListType: EmployeeListType) = EmployeeListFragment().apply {
            arguments = bundleOf(ARG_LIST_TYPE to employeeListType)
        }
    }
}
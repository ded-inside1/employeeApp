package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.util.Log
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
import com.github.lastachkin.employeeapp.util.Constants
import java.io.Serializable

class EmployeeListFragment: Fragment() {

    private val rvAdapter = EmployeeAdapter()
    var departmentType: EmployeeListType? = null

    private var viewModel: EmployeeListViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)

        departmentType = arguments?.getSerializable(ARG_LIST_TYPE) as EmployeeListType

        binding.employeeList.adapter = rvAdapter

        val searchView = activity?.findViewById<View>(R.id.searchView) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean { return false }

            override fun onQueryTextChange(newText: String?): Boolean {
                //rvAdapter.filter.filter(newText)
                return false
            }
        })

        viewModel?.employeeListObservable?.observe(viewLifecycleOwner) { employees ->
            if (employees != null) {
                rvAdapter.setData(employees)

                binding.loadingEmployees.visibility = View.GONE
            }
        }

        Log.i("TAG", "onCreateView $departmentType")



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TAG", "onCreate $departmentType")
    }

    override fun onStart() {
        super.onStart()
        Log.i("TAG", "onStart $departmentType")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        departmentType?.let { viewModel?.getData(it) }
        Log.i("TAG", "onViewCreated $departmentType")
    }



    override fun onResume() {
        super.onResume()



        Log.i("TAG", "onResume $departmentType")
    }

    override fun onPause() {
        super.onPause()
        Log.i("TAG", "onPause $departmentType")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAG", "onStop $departmentType")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG", "onDestroy $departmentType")
    }

    companion object{
        private const val ARG_LIST_TYPE = "LIST_TYPE"

        fun newInstance(employeeListType: EmployeeListType) = EmployeeListFragment().apply {
                arguments = bundleOf(ARG_LIST_TYPE to employeeListType)
        }
    }
}
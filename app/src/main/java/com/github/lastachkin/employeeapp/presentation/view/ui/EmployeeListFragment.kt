package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.model.service.EmployeeAPI
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeAdapter
import com.github.lastachkin.employeeapp.presentation.viewmodel.EmployeeListViewModel
import io.reactivex.disposables.CompositeDisposable

class EmployeeListFragment: Fragment() {

    //redundant
    private var employeeAPI: EmployeeAPI? = null
    private var myCompositeDisposable: CompositeDisposable? = null

    init {
        employeeAPI = EmployeeAPI.create()
        myCompositeDisposable = CompositeDisposable()
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)

        viewModel.employeeListObservable!!.observe(viewLifecycleOwner) { employees ->
            if (employees != null) {
                val adapter = EmployeeAdapter(employees)
                binding.employeeList.adapter = adapter
                binding.loadingEmployees.visibility = View.GONE
            }
        }

        return binding.root
    }
}
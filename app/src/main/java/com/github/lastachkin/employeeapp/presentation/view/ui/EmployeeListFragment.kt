package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
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
        fun getInstance(): EmployeeListFragment {
            return EmployeeListFragment()
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
                binding.loadingEmployees.visibility = View.GONE
                val adapter = EmployeeAdapter(employees)
                binding.employeeList.adapter = adapter
            }
        }

        return binding.root
    }
}
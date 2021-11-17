package com.github.lastachkin.employeeapp.presentation.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.databinding.FragmentEmployeeListBinding
import com.github.lastachkin.employeeapp.model.service.EmployeeAPI
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.employee_list_item.view.*

class EmployeeListFragment: Fragment() {

    //redundant
    private var employeeAPI: EmployeeAPI? = null
    private var myCompositeDisposable: CompositeDisposable? = null

    init {

        employeeAPI = EmployeeAPI.create()
        myCompositeDisposable = CompositeDisposable()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        myCompositeDisposable?.add(employeeAPI!!.getEmployees()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { list ->
                if (list != null) {
                    val adapter = EmployeeAdapter(list.employees!!)
                    binding.employeeList.adapter = adapter
                    binding.loadingEmployees.visibility = View.GONE
                }
            }, {
                Log.e("LOG", it.message.toString())}))

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
package com.github.lastachkin.employeeapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.databinding.EmployeeListItemBinding
import com.github.lastachkin.employeeapp.model.entity.Employee
import kotlinx.android.synthetic.main.employee_list_item.view.*


class EmployeeAdapter(_employeeList: List<Employee>):
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employeeList: List<Employee?>? = null

    init {
        employeeList = _employeeList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val inflater = inflater.inflate(R.layout.employee_list_item, null);
        val binding = EmployeeListItemBinding.inflate(inflater)
        return EmployeeViewHolder(binding);
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        holder.binding.name.text = (employeeList!![position]!!.firstName + " " + employeeList!![position]!!.lastName)
        holder.binding.userTag.text = employeeList!![position]!!.userTag
        holder.binding.position.text = employeeList!![position]!!.position
        Glide.with(EmployeeApp.applicationContext())
            .load(employeeList!![position]!!.avatarUrl)
            .into(holder.binding.profileImg)
    }

    override fun getItemCount(): Int {
        return if (employeeList == null) 0 else employeeList!!.size
    }

    class EmployeeViewHolder(val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}
package com.github.lastachkin.employeeapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.databinding.EmployeeListItemBinding
import com.github.lastachkin.employeeapp.model.entity.Employee
import java.util.*

class EmployeeAdapter(private val employeeList: MutableList<Employee> = mutableListOf()) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    fun setData(list: List<Employee>) {
        employeeList.clear()
        employeeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EmployeeListItemBinding.inflate(inflater)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bindData(employeeList[position])
    }

    override fun getItemCount() = employeeList.size

    class EmployeeViewHolder(private val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bindData(employee: Employee) {
            binding.name.text = (employee.firstName + " " + employee.lastName)
            binding.userTag.text = employee.userTag?.toLowerCase(Locale.ROOT)
            binding.position.text = employee.position

            Glide.with(EmployeeApp.applicationContext())
                .load(employee.avatarUrl)
                .into(binding.profileImg)
        }
    }
}
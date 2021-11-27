package com.github.lastachkin.employeeapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.lastachkin.employeeapp.EmployeeApp
import com.github.lastachkin.employeeapp.databinding.EmployeeListItemBinding
import com.github.lastachkin.employeeapp.model.entity.Employee
import java.util.*

class EmployeeAdapter(private val employeeList: MutableList<Employee> = mutableListOf()):
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    fun setData(list: List<Employee>){
        employeeList.clear()
        employeeList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EmployeeListItemBinding.inflate(inflater)
        return EmployeeViewHolder(binding);
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        holder.binding.name.text = (employeeList[position].firstName + " " + employeeList!![position].lastName)
        holder.binding.userTag.text = employeeList!![position].userTag!!.toLowerCase(Locale.ROOT)
        holder.binding.position.text = employeeList!![position].position
        Glide.with(EmployeeApp.applicationContext())
            .load(employeeList!![position].avatarUrl)
            .into(holder.binding.profileImg)
    }

    override fun getItemCount() = employeeList?.size ?: 0

    class EmployeeViewHolder(val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                employeeList = if (charSearch.isEmpty()) {
//                    employeeList
//                } else {
//                    val resultList = mutableListOf<Employee>()
//                    for (employee in employeeList)
//                        if (employee.firstName!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) ||
//                            employee.lastName!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) ||
//                            employee.userTag!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)))
//                            resultList.add(employee)
//                    resultList
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = employeeList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                employeeList = results?.values as List<Employee>
//                notifyDataSetChanged()
//            }
//        }
//    }
}
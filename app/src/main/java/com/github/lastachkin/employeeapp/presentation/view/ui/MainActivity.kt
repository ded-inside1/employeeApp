package com.github.lastachkin.employeeapp.presentation.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.ActivityMainBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeViewPagerAdapter
import com.github.lastachkin.employeeapp.util.FilterManager
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var filterManager: FilterManager

    init {
        instance = this
        filterManager = FilterManager()
    }

    companion object{
        private var instance: MainActivity? = null

        fun getFilterManager(): FilterManager{
            return instance!!.filterManager
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: 27.11.2021 move to xml 
        window.statusBarColor = Color.WHITE
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = true

//        binding.cancelTextView.setOnClickListener {
//            binding.searchView.setQuery("1132131", false)
//        }

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            val margin8inDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
            ).toInt()
            val margin94inDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 94f, resources.displayMetrics
            ).toInt()
            if(hasFocus){
                //val layoutParams = binding.searchView.layoutParams as ViewGroup.MarginLayoutParams
                //layoutParams.setMargins(margin8inDp, 0, margin94inDp, 0)
                //binding.searchView.layoutParams = layoutParams

                binding.cancelTextView.visibility = View.VISIBLE
            }
            else{
                val layoutParams = binding.searchView.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(margin8inDp, 0, margin8inDp, 0)
                binding.searchView.layoutParams = layoutParams

                binding.cancelTextView.visibility = View.GONE
            }
        }

        binding.viewPager.adapter = EmployeeViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = EmployeeListType.values()[position].name
        }.attach()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null)
            findViewById<SearchView>(R.id.searchView).clearFocus()

        return super.dispatchTouchEvent(ev)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean { return false }

    override fun onQueryTextChange(newText: String?): Boolean {
        filterManager.setQuery(newText)
        return true
    }
}
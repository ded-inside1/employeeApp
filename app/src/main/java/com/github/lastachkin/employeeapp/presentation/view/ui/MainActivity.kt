package com.github.lastachkin.employeeapp.presentation.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.github.lastachkin.employeeapp.R
import com.github.lastachkin.employeeapp.databinding.ActivityMainBinding
import com.github.lastachkin.employeeapp.model.entity.EmployeeListType
import com.github.lastachkin.employeeapp.presentation.view.adapter.EmployeeViewPagerAdapter
import com.github.lastachkin.employeeapp.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.viewPager.adapter = EmployeeViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = EmployeeListType.values()[position].name
        }.attach()

        binding.cancel.setOnClickListener { binding.searchView.clearFocus() }

        binding.sortOptionMenu.setOnClickListener { showBottomSheetDialog() }

        // TODO: 27.11.2021 move to xml 
        window.statusBarColor = Color.WHITE
        val wic = WindowInsetsControllerCompat(window, window.decorView)
        wic.isAppearanceLightStatusBars = true

        // TODO: 04.12.2021 move margin setup to xml?
        binding.searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            val margin8inDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics
            ).toInt()
            val margin94inDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 94f, resources.displayMetrics
            ).toInt()

            if (hasFocus) {
                val layoutParams = binding.searchView.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(margin8inDp, 0, margin94inDp, 0)
                binding.searchView.layoutParams = layoutParams

                binding.sortOptionMenu.visibility = View.GONE
                binding.cancel.visibility = View.VISIBLE
            } else {
                val layoutParams = binding.searchView.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(margin8inDp, 0, margin8inDp, 0)
                binding.searchView.layoutParams = layoutParams

                binding.cancel.visibility = View.GONE
                binding.sortOptionMenu.visibility = View.VISIBLE
            }
        }
    }

    private fun showBottomSheetDialog() {
        // TODO: 03.12.2021 move logic to viewModel
        BottomSheetDialog(this).apply {
            setContentView(R.layout.bottom_sheet_dialog)

            setOnShowListener {
                findViewById<RadioButton>(R.id.option_alphabet)?.isChecked =
                    getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)
                        .getBoolean(Constants.OPTION_ALPHABET, false)

                findViewById<RadioButton>(R.id.option_birth_date)?.isChecked =
                    getSharedPreferences(Constants.SHARED_PREFS_NAME, 0)
                        .getBoolean(Constants.OPTION_BIRTH_DATE, false)
            }

            setOnCancelListener {
                val sharedPrefsEditor = getSharedPreferences(Constants.SHARED_PREFS_NAME, 0).edit()

                findViewById<RadioButton>(R.id.option_alphabet)?.let {
                    sharedPrefsEditor
                        .putBoolean(Constants.OPTION_ALPHABET, it.isChecked)
                }

                findViewById<RadioButton>(R.id.option_birth_date)?.let {
                    sharedPrefsEditor
                        .putBoolean(Constants.OPTION_BIRTH_DATE, it.isChecked)
                }

                sharedPrefsEditor.apply()
            }

            show()
        }
    }
}
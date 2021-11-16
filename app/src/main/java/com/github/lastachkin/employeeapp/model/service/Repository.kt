package com.github.lastachkin.employeeapp.model.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.lastachkin.employeeapp.model.entity.Employee
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    var employeeAPI: EmployeeAPI? = null
    private var myCompositeDisposable: CompositeDisposable? = null

    init {

        employeeAPI = EmployeeAPI.create()
        myCompositeDisposable = CompositeDisposable()

    }

    fun getEmployees(){//<List<Employee>>
        var data: MutableLiveData<List<Employee>>? = null


        myCompositeDisposable?.add(employeeAPI!!.getEmployees()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe( { list ->
                if (list != null) {
                    for (employee in list.employees!!)
                        Log.i("LOG", employee.lastName!!)
                }
                        }, {
                Log.e("LOG", it.message.toString())}))
    }

    companion object{
        var repository: Repository? = null

        @JvmStatic
        fun getInstance(): Repository {
            if (this.repository == null )
                repository = Repository()

            return repository as Repository
        }
    }
}
package com.github.lastachkin.employeeapp.model.service

import com.github.lastachkin.employeeapp.model.entity.EmployeeResponse
import com.github.lastachkin.employeeapp.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EmployeeAPI {

    @GET("users")
    fun getEmployeesAsync(): Deferred<Response<EmployeeResponse>>

    companion object{
        fun create() : EmployeeAPI {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .baseUrl(Constants.API_BASE_URL)
                .build()

            return retrofit.create(EmployeeAPI::class.java)
        }
    }
}
package com.github.lastachkin.employeeapp.util

import java.util.*

class FilterManager: Observable() {
    private var query: String? = null

    fun setQuery(query: String?) {
        this.query = query
        setChanged()
        notifyObservers()
    }

    fun getQuery(): String? {
        return query
    }
}
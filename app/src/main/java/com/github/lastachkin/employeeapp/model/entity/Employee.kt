package com.github.lastachkin.employeeapp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("avatarUrl")
    @Expose
    var avatarUrl: String? = null,

    @SerializedName("firstName")
    @Expose
    var firstName: String? = null,

    @SerializedName("lastName")
    @Expose
    var lastName: String? = null,

    @SerializedName("userTag")
    @Expose
    var userTag: String? = null,

    @SerializedName("department")
    @Expose
    var department: String? = null,

    @SerializedName("position")
    @Expose
    var position: String? = null,

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,
)
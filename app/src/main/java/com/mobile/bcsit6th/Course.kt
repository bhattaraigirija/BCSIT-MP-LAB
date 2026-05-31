package com.mobile.bcsit6th

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("title") val title: String,
    @SerializedName("body") val description: String,
    val duration: String = "3 Months"
)
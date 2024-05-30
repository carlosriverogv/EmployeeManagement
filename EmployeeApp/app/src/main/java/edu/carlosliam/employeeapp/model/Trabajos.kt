package edu.carlosliam.employeeapp.model


import com.google.gson.annotations.SerializedName

data class Trabajos(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("result")
    val result: List<Trabajo>
)
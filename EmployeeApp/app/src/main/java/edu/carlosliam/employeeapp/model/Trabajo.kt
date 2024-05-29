package edu.carlosliam.employeeapp.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Trabajo(
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("codTrab")
    @PrimaryKey val codTrab: String,
    @SerializedName("descripcion")
    val descipcin: String,
    @SerializedName("fecFin")
    val fecFin: String?,
    @SerializedName("fecIni")
    val fecIni: String,
    @SerializedName("prioridad")
    val prioridad: Int,
    @SerializedName("tiempo")
    val tiempo: Int?,
    @SerializedName("trabajador")
    val trabajador: Trabajador
)
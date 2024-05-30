package edu.carlosliam.employeeapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trabajo")
data class Trabajo(
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("codTrab")
    @PrimaryKey val codTrab: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("fecFin")
    val fecFin: String? = null,
    @SerializedName("fecIni")
    val fecIni: String,
    @SerializedName("prioridad")
    val prioridad: Int,
    @SerializedName("tiempo")
    val tiempo: Double? = null,
    /*@SerializedName("trabajador")
    val trabajador: Trabajador?*/
)
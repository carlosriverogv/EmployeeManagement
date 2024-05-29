package edu.carlosliam.employeeapp.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Trabajador(
    @SerializedName("apellidos")
    val apellidos: String,
    @SerializedName("contraseña")
    val password: String,
    @SerializedName("dni")
    val dni: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("especialidad")
    val especialidad: String,
    @SerializedName("idTrabajador")
    @PrimaryKey val idTrabajador: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("trabajos")
    val trabajos: List<Trabajo>?
)
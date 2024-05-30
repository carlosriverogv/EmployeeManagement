package edu.carlosliam.employeeapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trabajador")
data class Trabajador(
    @SerializedName("apellidos")
    val apellidos: String,
    @SerializedName("contraseña")
    val contraseña: String,
    @SerializedName("dni")
    val dni: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("especialidad")
    val especialidad: String,
    @SerializedName("idTrabajador")
    @PrimaryKey val idTrabajador: String,
    @SerializedName("nombre")
    val nombre: String
)
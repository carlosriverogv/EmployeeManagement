package edu.carlosliam.employeeapp.data

import android.util.Log
import kotlinx.coroutines.flow.flow

class RemoteDataSource {
    private val api = SpringAPI.getRetrofit2Api()

    /*suspend fun getTrabajos(): List<Trabajo> = api.getTrabajos()^*/

    fun getTrabajos() = flow {
        try {
            val trabajos = api.getTrabajos()
            emit(trabajos)
            Log.d("RemoteDataSource", "Datos obtenidos correctamente: $trabajos")
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error al obtener datos: ${e.message}")
            throw e
        }
    }

    /*suspend fun getTrabajosPendientesOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosOrderByPrioridad(trabajadorId)

    suspend fun getTrabajosFinalizadosOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosFinalizadosOrderByPrioridad(trabajadorId)*/
}
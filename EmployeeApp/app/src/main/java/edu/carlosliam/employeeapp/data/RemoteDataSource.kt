package edu.carlosliam.employeeapp.data

import android.util.Log
import kotlinx.coroutines.flow.flow
import edu.carlosliam.employeeapp.utils.currentTrabajador

class RemoteDataSource {
    private val api = SpringAPI.getRetrofit2Api()

    fun getTrabajosPendientesLogin() = flow {
        try {
            val trabajos = api.getTrabajosPendientesByTrabajadorLogin(currentTrabajador.idTrabajador, currentTrabajador.contraseña)
            emit(trabajos)
            Log.d("RemoteDataSource", "Datos obtenidos correctamente 2: $trabajos")
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error al obtener datos 2: ${e.message}")
            throw e
        }
    }

    fun getTrabajosFinalizadosLogin() = flow {
        try {
            val trabajos = api.getTrabajosFinalizadosByTrabajadorLogin(currentTrabajador.idTrabajador, currentTrabajador.contraseña)
            emit(trabajos)
            Log.d("RemoteDataSource", "Datos obtenidos correctamente 2: $trabajos")
        } catch (e: Exception) {
            Log.e("RemoteDataSource", "Error al obtener datos 2: ${e.message}")
            throw e
        }
    }

    /*suspend fun getTrabajosPendientesOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosOrderByPrioridad(trabajadorId)

    suspend fun getTrabajosFinalizadosOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosFinalizadosOrderByPrioridad(trabajadorId)*/
}
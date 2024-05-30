package edu.carlosliam.employeeapp.data

import android.util.Log
import edu.carlosliam.employeeapp.model.Trabajos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(db: SpringRoomDB, val ds: RemoteDataSource) {
    val TAG = Repository::class.java.simpleName
    private val localDataSource = LocalDataSource(db.trabajoDao())

    val currentTrabajos: Flow<Trabajos> = ds.getTrabajosPendientesLogin()

    fun fetchTrabajos(): Flow<Trabajos> {
        try {
            val trabajos = ds.getTrabajos()
            Log.d(TAG, "Datos obtenidos correctamente: $trabajos")
            return trabajos
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener datos: ${e.message}")
            throw e
        }
    }

    fun fetchTrabajosPendientes(): Flow<Trabajos> {
        try {
            val trabajos = ds.getTrabajosPendientesLogin()
            Log.d(TAG, "Datos obtenidos correctamente: $trabajos")
            return trabajos
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener datos: ${e.message}")
            throw e
        }
    }

    /*fun fetchTrabajos(): Flow<Trabajos> {
        return flow {
            var resultAPI = emptyList<Trabajos>()

            try {
                resultAPI = ds.getTrabajos()

                // Se recorren los trabajos y se guardan los finalizados en la base de datos.
                for (trabajo in resultAPI) {
                    if (trabajo.result.fec != null) {
                        localDataSource.insertTrabajo(trabajo)
                    }
                }
            } catch (e: Exception) {
                // Se emite el error.
                Log.e(TAG, "fetchWords: ${e.message}")
            } finally {
                emit(resultAPI)
            }
        }
    }*/

    /*fun getSavedTrabajos(): Flow<List<Trabajo>> {
        return flow {
            emit(localDataSource.getTrabajos())
        }
    }*/
}
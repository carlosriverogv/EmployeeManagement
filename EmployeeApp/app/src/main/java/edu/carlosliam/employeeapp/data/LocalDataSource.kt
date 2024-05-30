package edu.carlosliam.employeeapp.data

import edu.carlosliam.employeeapp.model.Trabajo


class LocalDataSource(private val db: TrabajoDao) {
    suspend fun getTrabajos(): List<Trabajo> = db.getTrabajos()

    suspend fun insertTrabajo(trabajo: Trabajo) = db.insertTrabajo(trabajo)

    suspend fun deleteTrabajo(trabajo: Trabajo) = db.deleteTrabajo(trabajo)
}
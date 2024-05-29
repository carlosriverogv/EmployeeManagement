package edu.carlosliam.employeeapp.data

import edu.carlosliam.employeeapp.model.Trabajo

class RemoteDataSource {
    private val api = SpringAPI.getRetrofit2Api()

    suspend fun getTrabajosPendientesOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosOrderByPrioridad(trabajadorId)

    suspend fun getTrabajosFinalizadosOrderByPrioridad(trabajadorId: Int):
            List<Trabajo> = api.getTrabajosFinalizadosOrderByPrioridad(trabajadorId)


}
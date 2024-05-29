package edu.carlosliam.employeeapp.data

import edu.carlosliam.employeeapp.model.Trabajador
import edu.carlosliam.employeeapp.model.Trabajo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

class SpringAPI {
    companion object {
        const val BASE_URL = "http://localhost:8080/api/"

        fun getRetrofit2Api(): SpringAPIInterface {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(SpringAPIInterface::class.java)
        }
    }
}

interface SpringAPIInterface {

    // Servicios de trabajos

    @GET("trabajos/{id}")
    suspend fun getTrabajoById(@Path("id") id:Int): Trabajo

    /*
     * Servicio para obtener un listado de trabajos pendientes ordenados por prioridad
     * de un trabajador pasado por parámetro
     */
    @GET("trabajos/{trabajadorId}/prioridad")
    suspend fun getTrabajosOrderByPrioridad(@Path("id") trabajadorId:Int): List<Trabajo>

    /*
	 * Servicio para obtener un listado de trabajos finalizados ordenados por prioridad
	 * de un trabajador pasado por parámetro
	 */
    @GET("trabajos/finalizados/{trabajadorId}/prioridad")
    suspend fun getTrabajosFinalizadosOrderByPrioridad(@Path("id") trabajadorId:Int): List<Trabajo>

    /*
	 * Servicio para obtener un listado de trabajos pendientes de una prioridad
	 * en concreto de un trabajador pasado por parámetro
	 */
    @GET("trabajos/{trabajadorId}/prioridad/{prioridad}")
    suspend fun getTrabajosByPrioridad(@Path("trabajadorId") trabajadorId:Int, @Path("prioridad") prioridad:Int): List<Trabajo>

    @GET("trabajos/pendientes")
    suspend fun getTrabajosPendientes(): List<Trabajo>

    @GET("trabajos/finalizados")
    suspend fun getTrabajosFinalizados(): List<Trabajo>


    @PUT("trabajos/{trabajoId}/finalizar/{actualDate}")
    suspend fun finalizarTrabajo(@Path("trabajoId") trabajoId:Int, @Path("actualDate") actualDate:String): Trabajo

    // Servicios de trabajadores
    @GET("trabajadores/{id}")
    suspend fun getTrabajadorById(@Path("id") id:Int): Trabajador

    @GET("trabajadores/{id}/{pass}/pendientes")
    suspend fun getTrabajosPendientesByTrabajadorLogin(@Path("id") id:Int, @Path("pass") pass:String): List<Trabajo>

    @GET("trabajadores/{id}/{pass}/finalizados")
    suspend fun getTrabajosFinalizadosByTrabajadorLogin(@Path("id") id:Int, @Path("pass") pass:String): List<Trabajo>
}
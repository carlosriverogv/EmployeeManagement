package edu.carlosliam.employeeapp.data

import android.util.Log
import edu.carlosliam.employeeapp.model.Trabajador
import edu.carlosliam.employeeapp.model.Trabajos
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

class SpringAPI {
    companion object {
        const val BASE_URL = "http://192.168.1.69:8080/api/"

        fun getRetrofit2Api(): SpringAPIInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(SpringAPIInterface::class.java)
            Log.d("SpringAPI", "Instancia de Retrofit creada correctamente")
            return api
        }
    }
}

interface SpringAPIInterface {

    // Servicios de trabajos

    @GET("trabajos")
    suspend fun getTrabajos(): Trabajos

    @GET("trabajos/{id}")
    suspend fun getTrabajoById(@Path("id") id:Int): Trabajos

    /*
     * Servicio para obtener un listado de trabajos pendientes ordenados por prioridad
     * de un trabajador pasado por parámetro
     */
    @GET("trabajos/{trabajadorId}/prioridad")
    suspend fun getTrabajosOrderByPrioridad(@Path("id") trabajadorId:Int): List<Trabajos>

    /*
	 * Servicio para obtener un listado de trabajos finalizados ordenados por prioridad
	 * de un trabajador pasado por parámetro
	 */
    @GET("trabajos/finalizados/{trabajadorId}/prioridad")
    suspend fun getTrabajosFinalizadosOrderByPrioridad(@Path("id") trabajadorId:Int): List<Trabajos>

    /*
	 * Servicio para obtener un listado de trabajos pendientes de una prioridad
	 * en concreto de un trabajador pasado por parámetro
	 */
    @GET("trabajos/{trabajadorId}/prioridad/{prioridad}")
    suspend fun getTrabajosByPrioridad(@Path("trabajadorId") trabajadorId:Int, @Path("prioridad") prioridad:Int): List<Trabajos>

    @GET("trabajos/pendientes")
    suspend fun getTrabajosPendientes(): List<Trabajos>

    @GET("trabajos/finalizados")
    suspend fun getTrabajosFinalizados(): List<Trabajos>


    @PUT("trabajos/{trabajoId}/finalizar/{actualDate}")
    suspend fun finalizarTrabajo(@Path("trabajoId") trabajoId:String, @Path("actualDate") actualDate:String): Trabajos

    // Servicios de trabajadores
    @GET("trabajadores/{id}")
    suspend fun getTrabajadorById(@Path("id") id:String): Trabajador

    @GET("trabajadores/{id}/{pass}/pendientes")
    suspend fun getTrabajosPendientesByTrabajadorLogin(@Path("id") id:String, @Path("pass") pass:String): Trabajos

    @GET("trabajadores/{id}/{pass}/finalizados")
    suspend fun getTrabajosFinalizadosByTrabajadorLogin(@Path("id") id:String, @Path("pass") pass:String): Trabajos
}
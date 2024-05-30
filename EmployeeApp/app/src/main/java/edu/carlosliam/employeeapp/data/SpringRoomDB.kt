package edu.carlosliam.employeeapp.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import edu.carlosliam.employeeapp.model.Trabajador
import edu.carlosliam.employeeapp.model.Trabajo

@Database(entities = [Trabajo::class, Trabajador::class], version = 1)
abstract class SpringRoomDB : RoomDatabase() {
    abstract fun trabajoDao(): TrabajoDao
}

@Dao
interface TrabajoDao {

    @Query("SELECT * FROM trabajo")
    suspend fun getTrabajos(): List<Trabajo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajo(trabajo: Trabajo)

    @Delete
    suspend fun deleteTrabajo(trabajo: Trabajo)
}
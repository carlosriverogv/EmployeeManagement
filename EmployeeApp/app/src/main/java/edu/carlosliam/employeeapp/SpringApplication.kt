package edu.carlosliam.employeeapp

import android.app.Application
import androidx.room.Room
import edu.carlosliam.employeeapp.data.SpringRoomDB

class SpringApplication : Application() {
    lateinit var springDB: SpringRoomDB
        private set

    override fun onCreate() {
        super.onCreate()
        springDB = Room.databaseBuilder(
            this,
            SpringRoomDB::class.java, "spring.db"
        ).build()
    }
}
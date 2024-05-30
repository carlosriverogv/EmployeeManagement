package edu.carlosliam.employeeapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.carlosliam.employeeapp.data.Repository
import edu.carlosliam.employeeapp.model.Trabajos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository): ViewModel() {
    private var _currentsTrabajos: Flow<Trabajos> = repository.currentTrabajos
    //private var _savedTrabajos = repository.getSavedTrabajos()
    val currentTrabajos
        get() = _currentsTrabajos
    //val savedTrabajos
    //    get() = _savedTrabajos


    fun fetchTrabajosPendientes() {
        Log.d("MainViewModel", "fetchTrabajosPendientes() llamado")
        viewModelScope.launch {
            try {
                _currentsTrabajos = repository.fetchTrabajosPendientes()
                Log.d("MainViewModel", "Datos de trabajos pendientes obtenidos correctamente")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error al obtener datos de trabajos pendientes: ${e.message}")
            }
        }
    }

    fun fetchTrabajosFinalizados() {
        Log.d("MainViewModel", "fetchTrabajosFinalizados() llamado")
        viewModelScope.launch {
            try {
                _currentsTrabajos = repository.fetchTrabajosFinalizados()
                Log.d("MainViewModel", "Datos de trabajos finalizados obtenidos correctamente")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error al obtener datos de trabajos finalizados: ${e.message}")
            }
        }
    }

    // ROOM

    // SAVE FINALIZADOS

    @Suppress("UNCHECKED_CAST")
    class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
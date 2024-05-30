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

    fun fetchTrabajos() {
        Log.d("MainViewModel", "fetchTrabajos() llamado")
        viewModelScope.launch {
            try {
                _currentsTrabajos = repository.fetchTrabajos()
                Log.d("MainViewModel", "Datos de trabajos obtenidos correctamente")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error al obtener datos de trabajos: ${e.message}")
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
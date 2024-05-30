package edu.carlosliam.employeeapp.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import edu.carlosliam.employeeapp.R
import edu.carlosliam.employeeapp.SpringApplication
import edu.carlosliam.employeeapp.data.RemoteDataSource
import edu.carlosliam.employeeapp.data.Repository
import edu.carlosliam.employeeapp.databinding.ActivityMainBinding
import edu.carlosliam.employeeapp.model.Trabajo
import edu.carlosliam.employeeapp.ui.adapters.TrabajosAdapter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val trabajoList: MutableList<Trabajo> = mutableListOf()

    private val vm: MainViewModel by viewModels {
        val db = (application as SpringApplication).springDB
        val ds = RemoteDataSource()
        MainViewModel.MainViewModelFactory(Repository(db, ds))
    }

    private val trabajosAdapter by lazy {
        TrabajosAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = trabajosAdapter

        Log.e("Prueba", "Estoy")
        fetchTrabajos()
    }

    override fun onStart() {
        super.onStart()

        // Se asigna el listener al swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            fetchTrabajos()
        }
    }

    private fun fetchTrabajos() {
        trabajosAdapter.submitList(null)

        lifecycleScope.launch {
            vm.fetchTrabajosPendientes()
            Log.e("Prueba", "Estoy")
            vm.currentTrabajos.catch {
                Log.e("MainAct1", "Error al obtener trabajos: ${it.message}")
            }.collect {
                trabajosAdapter.submitList(it.result)
                Log.e("MainAct1", "Entra:")
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}
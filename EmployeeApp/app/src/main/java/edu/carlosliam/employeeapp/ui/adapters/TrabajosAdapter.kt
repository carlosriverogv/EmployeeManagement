package edu.carlosliam.employeeapp.ui.adapters

import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.carlosliam.employeeapp.R
import edu.carlosliam.employeeapp.databinding.TrabajoItemBinding
import edu.carlosliam.employeeapp.model.Trabajo

class TrabajosAdapter(

) : ListAdapter<Trabajo, TrabajosAdapter.TrabajoViewHolder>(TrabajoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrabajoViewHolder {
        return TrabajoViewHolder(
            TrabajoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: TrabajoViewHolder, position: Int) {
        val trabajo = getItem(position)
        holder.bind(trabajo)
    }

    inner class TrabajoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TrabajoItemBinding.bind(view)
        fun bind(trabajo: Trabajo) {
            binding.tvCod.text = trabajo.codTrab
            binding.tvDescription.text = trabajo.descripcion

            when (trabajo.prioridad) {
                1 -> binding.tvPrioridad.text
                2 -> binding.tvPrioridad.text
                3 -> binding.tvPrioridad.text
                4 -> binding.tvPrioridad.text 
            }

        }
    }
}

class TrabajoDiffCallback :
    DiffUtil.ItemCallback<Trabajo>() {
    override fun areItemsTheSame(oldItem: Trabajo, newItem: Trabajo): Boolean {
        return oldItem.codTrab == newItem.codTrab
    }
    override fun areContentsTheSame(oldItem: Trabajo, newItem: Trabajo): Boolean {
        return oldItem == newItem
    }
}

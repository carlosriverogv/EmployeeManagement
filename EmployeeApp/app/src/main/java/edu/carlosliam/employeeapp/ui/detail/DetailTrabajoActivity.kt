package edu.carlosliam.employeeapp.ui.detail

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import edu.carlosliam.employeeapp.R
import edu.carlosliam.employeeapp.databinding.ActivityTrabajoDetailBinding
import edu.carlosliam.employeeapp.model.Trabajo
import edu.carlosliam.employeeapp.utils.trabajoList

class DetailTrabajoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrabajoDetailBinding

    companion object {
        const val TRABAJO_ID = "TRABAJO_ID"

        fun navigate(activity: AppCompatActivity, id: String = "") {
            val intent = Intent(activity, DetailTrabajoActivity::class.java).apply {
                putExtra(TRABAJO_ID, id)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrabajoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bloqueo de la rotación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        if (intent.getIntExtra(TRABAJO_ID, 0) > 0) {
            // Se obtiene el episodio seleccionado de la lista de episodios.
            val trabajo = trabajoList.find { it.codTrab.equals(intent.getStringExtra(TRABAJO_ID)) }

            binding.tvDescripcion.text = trabajo?.descripcion
            binding.tvCategoria.text = trabajo?.categoria
            binding.tvCodTrabajo.text = resources.getString(R.string.txt_codTrab, trabajo?.codTrab)

            var formatHTML = getString(R.string.txt_fecha_inicio, trabajo?.fecIni)
            binding.tvFecIni.setText(Html.fromHtml(formatHTML, Html.FROM_HTML_MODE_LEGACY))

            formatHTML = getString(R.string.txt_prioridad_detalle,
                when (trabajo?.prioridad) {
                    1 -> resources.getString(R.string.txt_prioridad_urgente)
                    2 -> resources.getString(R.string.txt_prioridad_alta)
                    3 -> resources.getString(R.string.txt_prioridad_media)
                    else -> resources.getString(R.string.txt_prioridad_baja)
                }
            )
            binding.tvCategoria.setText(Html.fromHtml(formatHTML, Html.FROM_HTML_MODE_LEGACY))
        }
    }
}
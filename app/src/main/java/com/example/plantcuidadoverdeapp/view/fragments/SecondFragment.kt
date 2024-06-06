package com.example.plantcuidadoverdeapp.view.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.plantcuidadoverdeapp.R
import com.example.plantcuidadoverdeapp.databinding.FragmentSecondBinding
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas
import com.example.plantcuidadoverdeapp.model.entities.Plants
import com.example.plantcuidadoverdeapp.model.fromInternetDetallesPlantas
import com.example.plantcuidadoverdeapp.viewmodel.PlantsViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: PlantsViewModel by activityViewModels()
    private var plantsId: Int? = null
    private var plantsName: String? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            plantsId = bundle.getInt("plantsId")
            Log.d("tipo", plantsId.toString())
        }
        arguments?.let { bundle ->
            plantsName = bundle.getString("plantsName")
            Log.d("seleccion", plantsName.toString())
        }

        plantsId?.let { id ->
            viewModel.getPlantDetailByIdFromInternet(id)

        }
        viewModel.getPlantDetail().observe(viewLifecycleOwner, Observer {
            Log.d("seleccion", plantsId.toString())
            var id = it.id
            var name = it.nombre
            // cargamos datos desde la seleccion

            Glide.with(binding.imvPlanta).load(it.imagen).into(binding.imvPlanta)
            binding.tvNombre.text = it.nombre
            binding.tvTipo.text = it.tipo
            binding.tvDescripcion.text = it.descripcion

            binding.fab.setOnClickListener {
                plantsName?.let { it1 -> sendEmail(it1) }
                Log.d("Button", "Contactar")
            }


            /*

            binding.fab.setOnClickListener {
                Log.d("BUTTON", "Click")
                val intent = Intent(Intent.ACTION_SEND)
                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("luci@plantapp.cl"))

                intent.putExtra(
                    Intent.EXTRA_SUBJECT,

                    "SOLICITO INFORMACIÓN SOBRE ESTE CURSO" +id

                )

                mintent.putExtra(

                    Intent.EXTRA_TEXT, "HOLA\n" +

                            "Quisiera pedir información sobre este curso " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."
                )
                try {
                    startActivity(mintent)

                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }*/
        })
    }

    fun sendEmail(nombre: String) {
        Log.d("fun", "funcion enviar correo")
        val recipientEmail = "luci@plantapp.cl"
        val subject = "Consulta por Producto $nombre"
        val message = "Hola,\n\n" +
                "Vi el producto $nombre y me gustaría que me contactaran a este correo o al\n" +
                "siguiente número _________\n" + "Quedo atento."
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error al enviar el correo", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

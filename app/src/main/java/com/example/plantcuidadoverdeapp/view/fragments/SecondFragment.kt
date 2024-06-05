package com.example.plantcuidadoverdeapp.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.plantcuidadoverdeapp.databinding.FragmentSecondBinding
import com.example.plantcuidadoverdeapp.viewmodel.PlantsViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel : PlantsViewModel by activityViewModels()
    private var plantsId : Int? = null
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
            Log.d("seleccion", plantsId.toString())
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
            binding.tvTipo.text= it.tipo
            binding.tvDescripcion.text = it.descripcion


/*
            // ACTION SEND EMAIL
            binding.btMail.setOnClickListener {

                Log.d("BUTTON", "Click")

                val mintent = Intent(Intent.ACTION_SEND)

                mintent.data = Uri.parse("mailto")
                mintent.type = "text/plain"


                // DIRECCION DE CORREO
                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))

                // ASUNTO
                mintent.putExtra(
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
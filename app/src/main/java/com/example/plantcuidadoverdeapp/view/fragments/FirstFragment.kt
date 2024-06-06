package com.example.plantcuidadoverdeapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantcuidadoverdeapp.R
import com.example.plantcuidadoverdeapp.databinding.FragmentFirstBinding
import com.example.plantcuidadoverdeapp.viewmodel.PlantsAdapter
import com.example.plantcuidadoverdeapp.viewmodel.PlantsViewModel


class FirstFragment : Fragment() {

    private lateinit var  binding: FragmentFirstBinding
    private val viewModel: PlantsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlantsAdapter()
        binding.RwPlants.adapter= adapter
        binding.RwPlants.layoutManager= GridLayoutManager(context,2)

      viewModel.getPlantList().observe(viewLifecycleOwner, Observer {
         it?.let {
           Log.d("coleccion", it.toString())
             adapter.updatePlants(it)
    }
      })

        adapter.selectPlant().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("seleccion", it.id.toString())
                viewModel.getPlantDetailByIdFromInternet(it.id)
            }
            val bundle= Bundle().apply {
                putInt("PlantId", it.id)
                putString("PlantName", it.nombre)

            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        })
    }
}
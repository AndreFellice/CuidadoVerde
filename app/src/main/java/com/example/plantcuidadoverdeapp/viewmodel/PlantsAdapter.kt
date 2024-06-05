package com.example.plantcuidadoverdeapp.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantcuidadoverdeapp.R
import com.example.plantcuidadoverdeapp.databinding.PlantsListBinding
import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas


class PlantsAdapter: RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>() {
    private var plantList= listOf<ColeccionPlantas>()
    private val selectedPlant= MutableLiveData<ColeccionPlantas>()

    fun selectPlant(): LiveData<ColeccionPlantas> = selectedPlant

    fun updatePlants(plants: List<ColeccionPlantas>) {
        this.plantList = plants
        notifyDataSetChanged()
    }

    inner class PlantViewHolder(private val binding: PlantsListBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
            private lateinit var plant: ColeccionPlantas
        init {
            itemView.setOnClickListener(this)
        }

        @SuppressLint("StringFormatInvalid")
        fun bind(plant: ColeccionPlantas) {
            Glide.with(binding.imageViewPlant).load(plant.imagen).into(binding.imageViewPlant)
            binding.plantName.text = binding.root.context.getString(R.string.plant_name, plant.nombre)
            binding.plantTipo.text = binding.root.context.getString(R.string.tv_tipo, plant.tipo)

        }

        override fun onClick(v: View?) {
            selectedPlant.value = plantList[bindingAdapterPosition]
            Log.d("ONCLICK", bindingAdapterPosition.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = PlantsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int = plantList.size


}


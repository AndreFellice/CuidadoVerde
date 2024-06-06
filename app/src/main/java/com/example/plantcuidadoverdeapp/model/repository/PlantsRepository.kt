package com.example.plantcuidadoverdeapp.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.plantcuidadoverdeapp.model.RetrofitPlant
import com.example.plantcuidadoverdeapp.model.dao.PlantsDao
import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas
import com.example.plantcuidadoverdeapp.model.fromInternetColeccionPlantas
import com.example.plantcuidadoverdeapp.model.fromInternetDetallesPlantas

class PlantsRepository (private val plantsDao: PlantsDao){
    // retrofit Cliente
    private val networkService = RetrofitPlant.getRetrofitInstance()
    // LiveData para la lista de plantas
    val plantListLiveData: LiveData<List<ColeccionPlantas>> = plantsDao.getAllPlants()


    // Método para obtener la lista de plantas desde el servidor y almacenarla en la base de datos
    suspend fun fetchPlantList(){
        val service = kotlin.runCatching { networkService.fetchPlantList() }

        service.onSuccess { response ->
            if (response.isSuccessful) {
                response.body()?.let { plantList ->
                    Log.d("Plantas", plantList.toString())
                    plantsDao.insertAllPlants(fromInternetColeccionPlantas(plantList))
                }
            } else {
                Log.d("Repo", "${response.code()} - ${response.errorBody()}")
            }
        }.onFailure {
            Log.e("Error", it.message ?: "Error desconocido")
        }
    }
    suspend fun fetchPlantDetail(id: Int):DetallesPlantas?{
        val service = kotlin.runCatching { networkService.fetchPlantDetail(id) }
        return service.getOrNull()?.body()?.let { plantDetail->
            // Mapea los datos obtenidos del servicio a la entidad DetallesPlantas
            val detallesPlantas = fromInternetDetallesPlantas(plantDetail)
            //Inserta los detalles de la planta en la base de datos local
            plantsDao.insertPlantDetail(detallesPlantas)
            detallesPlantas
        }
    }

    fun getPlantDetailById(id: Int): LiveData<DetallesPlantas> {
        return plantsDao.getPlantDetailById(id)
    }
}
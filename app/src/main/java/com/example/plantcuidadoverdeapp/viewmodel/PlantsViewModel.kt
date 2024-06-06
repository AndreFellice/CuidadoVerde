package com.example.plantcuidadoverdeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantcuidadoverdeapp.model.database.PlantsDataBase
import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas
import com.example.plantcuidadoverdeapp.model.repository.PlantsRepository
import kotlinx.coroutines.launch

class PlantsViewModel(application: Application, private val repository: PlantsRepository ): AndroidViewModel(application) {


    private val _plantDetailLiveData = MutableLiveData<DetallesPlantas>()
    val plantDetailLiveData: LiveData<DetallesPlantas>
        get() = _plantDetailLiveData

    // para seleccionar
    private var idSelected: Int = 0


    init {
        viewModelScope.launch {
            repository.fetchPlantList()
        }
    }

    //listado de elementos
    fun getPlantList(): LiveData<List<ColeccionPlantas>> = repository.plantListLiveData

    //listado de detalles
    fun getPlantDetail(): LiveData<DetallesPlantas> = repository.getPlantDetailById(idSelected)

    fun getPlantDetailByIdFromInternet(id:Int) = viewModelScope.launch {
       idSelected = id
        viewModelScope.launch {
      _plantDetailLiveData.postValue(repository.fetchPlantDetail(id))

    }
  }
}
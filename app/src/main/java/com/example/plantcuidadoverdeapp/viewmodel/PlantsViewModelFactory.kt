package com.example.plantcuidadoverdeapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantcuidadoverdeapp.model.database.PlantsDataBase
import com.example.plantcuidadoverdeapp.model.repository.PlantsRepository

class PlantsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantsViewModel::class.java)) {
            val database = PlantsDataBase.getDataBase(application)
            val repository = PlantsRepository(database.getPlantsDao())
            @Suppress("UNCHECKED_CAST")
            return PlantsViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.simpleName}")
    }
}
package com.example.plantcuidadoverdeapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas

@Dao
interface PlantsDao {


    // insertar lista de Plants
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlants(coleccionPlantas: List<ColeccionPlantas>)

    // seleccionar Listado de Plants
    @Query("SELECT * FROM lista_plantas ORDER BY id ASC")
    fun getAllPlants(): LiveData<List<ColeccionPlantas>>

    // inserta de a 1 plantas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantDetail(detallesPlantas: DetallesPlantas)

    @Query("SELECT * FROM detalles_plantas WHERE id=:id")
    fun getPlantDetailById(id: Int): LiveData<DetallesPlantas>

}
package com.example.plantcuidadoverdeapp.model

import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas
import com.example.plantcuidadoverdeapp.model.entities.Plants
import com.example.plantcuidadoverdeapp.model.entities.PlantsDetail

fun fromInternetColeccionPlantas( plantsList: List<Plants>) :List<ColeccionPlantas>{

    return plantsList.map {
        ColeccionPlantas(
            id=it.id,
            nombre= it.nombre,
            tipo= it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion

        )

    }


}


fun fromInternetDetallesPlantas( plants: PlantsDetail) :DetallesPlantas{

    return DetallesPlantas(
        id= plants.id,
        nombre= plants.nombre,
        tipo= plants.tipo,
        imagen = plants.imagen,
        descripcion = plants.descripcion

    )

}

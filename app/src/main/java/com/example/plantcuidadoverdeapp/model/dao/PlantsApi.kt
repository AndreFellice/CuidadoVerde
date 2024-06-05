package com.example.plantcuidadoverdeapp.model.dao

import com.example.plantcuidadoverdeapp.model.entities.Plants
import com.example.plantcuidadoverdeapp.model.entities.PlantsDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantsApi {

    @GET("coleccion_plantas")
    suspend fun fetchPlantList(): Response<List<Plants>>



    @GET("detalles_plantas/{id}")
    suspend fun fetchPlantDetail(@Path("id") id: Int) : Response<PlantsDetail>

}
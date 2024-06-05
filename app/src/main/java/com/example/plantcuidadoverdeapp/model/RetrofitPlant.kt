package com.example.plantcuidadoverdeapp.model

import com.example.plantcuidadoverdeapp.model.dao.PlantsApi
import com.example.plantcuidadoverdeapp.model.entities.Plants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPlant {

    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"

        lateinit var  retrofitInstance : Retrofit

        fun retrofitInstance(): PlantsApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return  retrofit.create(PlantsApi::class.java)
        }


    }
}
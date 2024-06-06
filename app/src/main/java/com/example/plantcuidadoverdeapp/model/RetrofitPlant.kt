package com.example.plantcuidadoverdeapp.model

import com.example.plantcuidadoverdeapp.model.dao.PlantsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitPlant {

    companion object{

        private const val BASE_URL ="https://my-json-server.typicode.com/mauricioponce/TDApi/"

        @Volatile
        private var retrofitInstance: Retrofit? = null

        fun getRetrofitInstance(): PlantsApi {
            // Si retrofitInstance es null, sincronizamos para inicializarla
            val tempInstance = retrofitInstance
            if (tempInstance != null) {
                return tempInstance.create(PlantsApi::class.java)
            }

            synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitInstance = instance
                return instance.create(PlantsApi::class.java)
            }
        }
    }
}
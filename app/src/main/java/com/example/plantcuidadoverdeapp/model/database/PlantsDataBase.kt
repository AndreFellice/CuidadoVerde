package com.example.plantcuidadoverdeapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantcuidadoverdeapp.model.entities.ColeccionPlantas
import com.example.plantcuidadoverdeapp.model.entities.DetallesPlantas
import com.example.plantcuidadoverdeapp.model.dao.PlantsDao

@Database(entities = [ColeccionPlantas::class, DetallesPlantas::class], version = 1,exportSchema = false)
abstract class PlantsDataBase : RoomDatabase() {

    // referencia del dao
    abstract fun getPlantsDao(): PlantsDao


    companion object{

        @Volatile
        private var
                INSTANCE : PlantsDataBase? = null
        fun getDataBase(context: Context) : PlantsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantsDataBase::class.java, "plantas_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
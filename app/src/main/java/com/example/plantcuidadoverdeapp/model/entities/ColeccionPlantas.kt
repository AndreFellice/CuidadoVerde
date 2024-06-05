package com.example.plantcuidadoverdeapp.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="lista_plantas")
data class ColeccionPlantas (

    @PrimaryKey
    val id : Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String,




    )
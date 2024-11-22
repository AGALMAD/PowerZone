package com.example.gymapp.Model

//pondremos la parte del Api que gestiona directamente los datos

data class GymApi(
    val id: String,
    val bank: String,
    val number: String,
    val cvv: String,
    val type: String
)
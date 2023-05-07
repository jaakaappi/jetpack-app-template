package com.example.template.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiInterface {
    @GET("pokemon/{id}")
    fun getPokemonById(id: Int): Call<List<PokemonDTO>>
}
package com.example.unitconverter.services

import com.example.unitconverter.models.CategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

val recipeService = retrofit.create(ApiService::class.java)
interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

}
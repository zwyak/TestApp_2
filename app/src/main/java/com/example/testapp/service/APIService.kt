package com.example.testapp.service

import com.example.testapp.model.ArrayData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("countries/")
    fun loadCountries(): Call<ArrayData>

    @GET("regions/")
    fun loadRegions(@Query("country_id") countryId: Int): Call<ArrayData>

    @GET("districts/")
    fun loadDistricts(@Query("region_id") regionId: Int): Call<ArrayData>

    @GET("cities/")
    fun loadCities(@Query("district_id") districtId: Int): Call<ArrayData>

    @GET("streets/")
    fun loadStreets(@Query("city_id") cityId: Int): Call<ArrayData>
}
package com.example.testapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressService {
    private var retrofit: Retrofit? = null

    fun getAPI(): APIService{
        var BASE_URL: String = "https://api.e-dostavka.by/api/1.0/addresses/"

        if (retrofit == null){
            retrofit = Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        return retrofit!!.create(APIService::class.java)
    }
}
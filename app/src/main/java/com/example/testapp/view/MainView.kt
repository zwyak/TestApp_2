package com.example.testapp.view

import com.example.testapp.model.ArrayData

interface MainView {
    fun countriesReady(countries: ArrayData)
    fun countriesFailure(message: String)

    fun regionsReady(regions: ArrayData)
    fun regionsFailure(message: String)

    fun districtsReady(districts: ArrayData)
    fun districtsFailure(message: String)

    fun citiesReady(cities: ArrayData)
    fun citiesFailure(message: String)

    fun streetsReady(streets: ArrayData)
    fun streetsFailure(message: String)
}
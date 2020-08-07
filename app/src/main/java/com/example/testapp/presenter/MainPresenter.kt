package com.example.testapp.presenter

import com.example.testapp.model.ArrayData
import com.example.testapp.service.AddressService
import com.example.testapp.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(_mainView: MainView) {
    private var mainView: MainView
    private var addressService: AddressService? = null

    init{
        mainView = _mainView
        if (addressService == null){
            addressService = AddressService()
        }
    }

    fun getCountries(){
        addressService!!
            .getAPI()
            .loadCountries()
            .enqueue(object: Callback<ArrayData>{
                override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                    mainView.countriesFailure("Countries wasn't loaded")
                }

                override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                    val data = response.body()

                    if (data != null && response.isSuccessful){
                        mainView.countriesReady(data)
                    }
                }
            })
    }

    fun getRegions(countryId: Int){
        addressService!!
            .getAPI()
            .loadRegions(countryId)
            .enqueue(object: Callback<ArrayData>{
                override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                    mainView.regionsFailure("Regions wasn't loaded")
                }

                override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                    val data = response.body()

                    if (data != null && response.isSuccessful){
                        mainView.regionsReady(data)
                    }
                }
            })
    }

    fun getDistricts(regionId: Int){
        addressService!!
            .getAPI()
            .loadDistricts(regionId)
            .enqueue(object: Callback<ArrayData>{
                override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                    mainView.districtsFailure("Districts wasn't loaded")
                }

                override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                    val data = response.body()

                    if (data != null && response.isSuccessful){
                        mainView.districtsReady(data)
                    }
                }
            })
    }

    fun getCities(districtId: Int){
        addressService!!
            .getAPI()
            .loadCities(districtId)
            .enqueue(object: Callback<ArrayData>{
                override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                    mainView.citiesFailure("Cities wasn't loaded")
                }

                override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                    val data = response.body()

                    if (data != null && response.isSuccessful){
                        mainView.citiesReady(data)
                    }
                }
            })
    }

    fun getStreets(cityId: Int){
        addressService!!
            .getAPI()
            .loadStreets(cityId)
            .enqueue(object: Callback<ArrayData>{
                override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                    mainView.streetsFailure("Streets wasn't loaded")
                }

                override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                    val data = response.body()

                    if (data != null && response.isSuccessful){
                        mainView.streetsReady(data)
                    }
                }
            })
    }
}
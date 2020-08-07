package com.example.testapp

import com.example.testapp.service.AddressService
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class APIUnitTest {
    var addressService: AddressService? = null

    @Before
    fun initAPI(){
        addressService = AddressService()
    }

    @Test
    fun getCountries(){
        val res = addressService!!.getAPI().loadCountries().execute()
        assertEquals(true, res.isSuccessful)
    }

    @Test
    fun getRegions(){
        val res = addressService!!.getAPI().loadRegions(1).execute()
        val regionsCount = res.body()!!.data.count()
        assertEquals(6, regionsCount)
    }

    @Test
    fun getDisticts(){
        val res = addressService!!.getAPI().loadDistricts(1).execute()
        assertEquals(false, res.isSuccessful)
    }

    @Test
    fun getCities(){
        val res = addressService!!.getAPI().loadCities(1).execute()
        assertNotNull(res.body())
    }

    @Test
    fun getStreets(){
        val res = addressService!!.getAPI().loadStreets(1).execute()
        assertNull(res.body())
    }
}
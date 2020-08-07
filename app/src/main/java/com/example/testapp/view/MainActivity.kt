package com.example.testapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.testapp.R
import com.example.testapp.model.ArrayData
import com.example.testapp.model.Data
import com.example.testapp.presenter.MainPresenter
import com.example.testapp.utils.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    val mainPresenter: MainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Util.isInternetConnected(this)){
            val toastFailure = Toast.makeText(this, R.string.noInternet, Toast.LENGTH_LONG)
            toastFailure.setGravity(Gravity.CENTER, 0, 0)
            toastFailure.show()
            return
        }

        mainPresenter.getCountries()

        countries?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedCountry = countries.selectedItem as Data
                mainPresenter.getRegions(selectedCountry.id)
            }
        }

        regions?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedRegion = regions.selectedItem as Data
                mainPresenter.getDistricts(selectedRegion.id)
            }
        }

        districts?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedDistrict = districts.selectedItem as Data
                mainPresenter.getCities(selectedDistrict.id)
            }
        }

        localities?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedCity = localities.selectedItem as Data
                mainPresenter.getStreets(selectedCity.id)
            }
        }

        saveBtn?.setOnClickListener(View.OnClickListener {
            val toastFailure = Toast.makeText(this, R.string.noHouse, Toast.LENGTH_LONG)
            val toastSuccess = Toast.makeText(this, R.string.successDataSaving, Toast.LENGTH_SHORT)
            toastSuccess.setGravity(Gravity.CENTER, 0, 0)
            if(house.text.length == 0){
                toastFailure.show()
            }else{
                toastSuccess.show()
                house.setText("")
                housing.setText("")
                entrance.setText("")
                floor.setText("")
                flat.setText("")
            }
        })
    }

    override fun countriesReady(countriesArray: ArrayData) {
        val countriesAdapter: ArrayAdapter<Data> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, countriesArray.data)
        countriesAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countries.adapter = countriesAdapter!!

        if (Util.isSingle(countriesArray.data)){
            val selectedCountry = countries.selectedItem as Data
            mainPresenter.getRegions(selectedCountry.id)
        }
    }

    override fun countriesFailure(message: String){
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun regionsReady(regionsArray: ArrayData) {
        val regionsAdapter: ArrayAdapter<Data> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, regionsArray.data)
        regionsAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regions.adapter = regionsAdapter!!

        if (Util.isSingle(regionsArray.data)){
            val selectedRegion = regions.selectedItem as Data
            mainPresenter.getDistricts(selectedRegion.id)
        }
    }

    override fun regionsFailure(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun districtsReady(districtsArray: ArrayData) {
        val districtsAdapter: ArrayAdapter<Data> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, districtsArray.data)
        districtsAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        districts.adapter = districtsAdapter!!

        if (Util.isSingle(districtsArray.data)){
            val selectedDistrict = regions.selectedItem as Data
            mainPresenter.getCities(selectedDistrict.id)
        }
    }

    override fun districtsFailure(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun citiesReady(citiesArray: ArrayData) {
        val citiesAdapter: ArrayAdapter<Data> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, citiesArray.data)
        citiesAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        localities.adapter = citiesAdapter!!

        if (Util.isSingle(citiesArray.data)){
            val selectedCity = localities.selectedItem as Data
            mainPresenter.getStreets(selectedCity.id)
        }
    }

    override fun citiesFailure(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    override fun streetsReady(streetsArray: ArrayData) {
        val streetsAdapter: ArrayAdapter<Data> = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, streetsArray.data)
        streetsAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        streets.adapter = streetsAdapter!!
    }

    override fun streetsFailure(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }
}
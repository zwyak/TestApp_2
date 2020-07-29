package testapp.example.com

import addresses.APIService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import pojo.ArrayData
import pojo.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var BASE_URL: String = "https://api.e-dostavka.by/api/1.0/addresses/"
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    private var apiService = retrofit.create(APIService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        streets.setTitle("Выберите улицу")
        streets.setPositiveButton("Подтвердить")

        setListeners()
        loadCountries()

        saveBtn?.setOnClickListener(View.OnClickListener {
            val toastFailure = Toast.makeText(this, "Заполните поле Дом", Toast.LENGTH_LONG)
            val toastSuccess = Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT)
            toastSuccess.setGravity(Gravity.CENTER, 0, 0)
            if(house.text.length == 0) toastFailure.show() else toastSuccess.show()
        })
    }

    private fun loadCountries(){
        apiService.loadCountries().enqueue(object: Callback<ArrayData>{
            override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                Log.d("Countries", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                var countriesAdapter: ArrayAdapter<Data> = ArrayAdapter<Data>(this@MainActivity, android.R.layout.simple_spinner_item, response!!.body()!!.data)
                countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                countries.adapter = countriesAdapter

                if (isSingle(response!!.body()!!.data)) loadRegions()
            }
        })
    }

    private fun loadRegions(){
        val selectedCountry: Data = countries.selectedItem!! as Data

        apiService.loadRegions(selectedCountry.id).enqueue(object: Callback<ArrayData>{
            override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                Log.d("Regions", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                var regionsAdapter: ArrayAdapter<Data> = ArrayAdapter<Data>(this@MainActivity, android.R.layout.simple_spinner_item, response!!.body()!!.data)
                regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                regions.adapter = regionsAdapter

                if (isSingle(response!!.body()!!.data)) loadDistricts()
            }
        })
    }

    private fun loadDistricts(){
        val selectedRegion: Data = regions.selectedItem!! as Data

        apiService.loadDistricts(selectedRegion.id).enqueue(object: Callback<ArrayData>{
            override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                Log.d("Districts", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                var districtsAdapter: ArrayAdapter<Data> = ArrayAdapter<Data>(this@MainActivity, android.R.layout.simple_spinner_item, response!!.body()!!.data)
                districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                districts.adapter = districtsAdapter

                if (isSingle(response!!.body()!!.data)) loadCities()
            }
        })
    }

    private fun loadCities(){
        val selectedDistrict: Data = districts.selectedItem!! as Data

        apiService.loadCities(selectedDistrict.id).enqueue(object: Callback<ArrayData>{
            override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                Log.d("Cities", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                var citiesAdapter: ArrayAdapter<Data> = ArrayAdapter<Data>(this@MainActivity, android.R.layout.simple_spinner_item, response!!.body()!!.data)
                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                localities.adapter = citiesAdapter

                if (isSingle(response!!.body()!!.data)) loadStreets()
            }
        })
    }

    private fun loadStreets(){
        val selectedCity: Data = localities.selectedItem!! as Data

        apiService.loadStreets(selectedCity.id).enqueue(object: Callback<ArrayData>{
            override fun onFailure(call: Call<ArrayData>, t: Throwable) {
                Log.d("Streets", t.message.toString())
            }

            override fun onResponse(call: Call<ArrayData>, response: Response<ArrayData>) {
                var streetsAdapter: ArrayAdapter<Data> = ArrayAdapter<Data>(this@MainActivity, android.R.layout.simple_spinner_item, response!!.body()!!.data)
                streetsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                streets.adapter = streetsAdapter
            }
        })
    }

    private fun<T> isSingle(data: Array<T>): Boolean{
        return if (data.count() > 1) false else true
    }

    private fun setListeners(){
        setOnItemSelectedListenerForCountries()
        setOnItemSelectedListenerForRegions()
        setOnItemSelectedListenerForDistricts()
        setOnItemSelectedListenerForLocalities()
    }

    private fun setOnItemSelectedListenerForCountries(){
        countries?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loadRegions()
            }
        }
    }

    private fun setOnItemSelectedListenerForRegions(){
        regions?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loadDistricts()
            }
        }
    }

    private fun setOnItemSelectedListenerForDistricts(){
        districts?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loadCities()
            }
        }
    }

    private fun setOnItemSelectedListenerForLocalities(){
        localities?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loadStreets()
            }
        }
    }
}
package com.example.testapp.utils

import android.content.Context
import android.net.ConnectivityManager

class Util {
    companion object{
        fun<T> isSingle(array: Array<T>): Boolean{
            return array.count() == 1
        }

        fun isInternetConnected(context: Context): Boolean{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetwork != null
        }
    }
}
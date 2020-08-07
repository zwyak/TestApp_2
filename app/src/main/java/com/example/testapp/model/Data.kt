package com.example.testapp.model

data class Data (
    val id: Int,
    val name: String,
    val status: Int
){
    override fun toString(): String {
        return name
    }
}
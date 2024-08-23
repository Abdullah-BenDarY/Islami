package com.example.islami.pojo

data class ModelRadio(
    val radios: List<Radio>
)

data class Radio(
    val id: Int,
    val name: String,
    val recent_date: String,
    val url: String
)
package com.example.islami.network
import com.example.islami.pojo.ModelRadio
import org.intellij.lang.annotations.Language

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {


    @GET("radios")
    suspend fun getLiveStream(
            @Query("language")language: String
    ): ModelRadio

}
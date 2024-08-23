package com.example.islami.repo

import android.view.textclassifier.TextLanguage
import com.example.islami.network.ApiCalls
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val api: ApiCalls) {

    suspend fun getLiveStream(language: String) =
        api.getLiveStream(language)
}
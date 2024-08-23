package com.example.islami.ui.radio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islami.util.Resource
import com.example.islami.pojo.Radio
import com.example.islami.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _radioLiveData = MutableLiveData<Resource<List<Radio>?>>()
    val radioLiveData get() = _radioLiveData


        fun getLiveStream(language: String) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = repository.getLiveStream(language)
                    if (response.radios.isNotEmpty()) {
                        _radioLiveData.postValue(Resource.Success(response.radios))

                    } else {
                        _radioLiveData.postValue(Resource.Error(response.toString()))
                    }

                } catch (e: Exception) {
                    _radioLiveData.postValue(Resource.Error("An error occurred: ${e.message}"))
                }
            }
        }
    }

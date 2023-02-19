package com.example.kamulahtv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kamulahtv.repository.ProgramApiRepository
import com.example.kamulahtv.network.Program
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class ProgramApiViewModel(private val repository: ProgramApiRepository): ViewModel() {
    private val _program = MutableLiveData<List<Program>>()

    val programs: LiveData<List<Program>>
        get() = _program
    private var viewmModelJob = Job()
    private var coroutineScope = CoroutineScope(viewmModelJob + Dispatchers.Main)

//    init {
//        fetchPrograms()
//    }

    fun fetchPrograms() {

        coroutineScope.launch {
            val getPropertiesDeferred = repository.fetchPrograms()
            try {
                val listResult = getPropertiesDeferred.await()
                _program.value = listResult
            } catch (e: Exception) {

                _program.value = ArrayList()
            }
        }
    }
}

class ProgramApiViewModelFactory(private val repository: ProgramApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProgramApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProgramApiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
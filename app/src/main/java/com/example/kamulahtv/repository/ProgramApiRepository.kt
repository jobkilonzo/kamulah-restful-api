package com.example.kamulahtv.repository

import com.example.kamulahtv.network.NodejsApi
import com.example.kamulahtv.network.Program
import kotlinx.coroutines.Deferred

class ProgramApiRepository {
    fun fetchPrograms() : Deferred<List<Program>> {
        return NodejsApi.programApi.getProgramAsync()
    }
}
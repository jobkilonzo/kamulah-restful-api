package com.example.kamulahtv

import android.app.Application
import com.example.kamulahtv.repository.ProgramApiRepository


class ProgramApplication: Application() {
    val programApiRepository by lazy { ProgramApiRepository() }
}
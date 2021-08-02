package com

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.locationexplorer.BuildConfig
import dagger.hilt.android.testing.HiltTestApplication

class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        BuildConfig.IS_TESTING.set(true)
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
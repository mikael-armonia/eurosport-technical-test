package com.mikaelarmonia.eurosporttechnicaltest

import android.app.Application
import com.mikaelarmonia.eurosporttechnicaltest.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EurosportTTApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EurosportTTApplication)
            modules(appModules)
        }
    }
}
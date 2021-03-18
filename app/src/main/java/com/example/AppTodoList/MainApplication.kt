package com.example.AppTodoList

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Nos permite que hilt sepa el contexto de nuestra aplicacion y asi poder
 * manejar la inyeccion de dependencias
 */
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
package edu.uoc.pac4

import android.app.Application
import edu.uoc.pac4.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(applicationContext)
      modules(dataModule)
    }
  }
}
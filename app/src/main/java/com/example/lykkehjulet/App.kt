
package com.example.lykkehjulet
import android.app.Application
//import com.example.lykkehjulet.viewModel.database
import com.example.lykkehjulet.viewModel.repository
import com.example.lykkehjulet.viewModel.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        koin()
    }

    private fun koin() {
        startKoin {
            androidContext(this@App)
            modules(
                repository,
                viewModel,
            )
        }
    }
}
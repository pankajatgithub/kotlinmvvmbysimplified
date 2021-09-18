package com.example.kotlinmvvmbysimplified

import android.app.Application
import com.example.kotlinmvvmbysimplified.data.db.AppDatabase
import com.example.kotlinmvvmbysimplified.data.network.MyApi
import com.example.kotlinmvvmbysimplified.data.network.NetworkConnectionInterceptor
import com.example.kotlinmvvmbysimplified.data.preferences.PreferenceProvider
import com.example.kotlinmvvmbysimplified.data.repository.QuotesRepository
import com.example.kotlinmvvmbysimplified.data.repository.UserRepository
import com.example.kotlinmvvmbysimplified.ui.auth.AuthViewModelFactory
import com.example.kotlinmvvmbysimplified.ui.home.profile.ProfileViewModelFactory
import com.example.kotlinmvvmbysimplified.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MVVMApplication : Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import( androidXModule(this@MVVMApplication) )
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
//        in quote repository we need preferenceprovider, for this we will create it's instance and after this
        //it will be available in instance()
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
        bind() from singleton { ProfileViewModelFactory(instance()) }
        bind() from singleton { QuotesViewModelFactory(instance()) }
    }

}
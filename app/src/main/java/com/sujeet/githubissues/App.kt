package com.sujeet.githubissues

import android.content.Context
import com.sujeet.githubissues.di.DaggerAppComponent
import com.sujeet.githubissues.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

open class App : DaggerApplication() {

    private lateinit var androidInjector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = androidInjector


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        androidInjector = DaggerAppComponent.builder()
            .application(this)
            .network(networkModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    protected open fun networkModule(): NetworkModule = NetworkModule()


    protected open fun initTimber() = Timber.plant(Timber.DebugTree())
}
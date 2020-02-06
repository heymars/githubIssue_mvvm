package com.sujeet.githubissues.di

import com.sujeet.githubissues.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class
    ])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun database(database: DbModule): Builder

        fun network(network: NetworkModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}
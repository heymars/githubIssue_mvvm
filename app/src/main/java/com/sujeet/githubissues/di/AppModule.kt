package com.sujeet.githubissues.di

import dagger.Module

@Module(includes = [DbModule::class, NetworkModule::class, ViewModelModule::class])
internal object AppModule {
}
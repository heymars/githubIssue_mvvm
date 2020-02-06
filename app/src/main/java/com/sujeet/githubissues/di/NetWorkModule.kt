package com.sujeet.githubissues.di

import com.sujeet.githubissues.App
import com.sujeet.githubissues.data.Constants
import com.sujeet.githubissues.data.remote.ApiServices
import com.sujeet.githubissues.utils.PreferenceHelper
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
      fun buildOkHttpClient(app: App): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(Cache(File(app.cacheDir, "githubissues"), 10 * 1024 * 1024))
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(app: App): OkHttpClient = buildOkHttpClient(app)

    @Provides
    @Singleton
    fun provideRetrofitForGithub(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Provides
    @Singleton
    fun providePreferencesHelper(app: App): PreferenceHelper {
        return PreferenceHelper(app)
    }
}
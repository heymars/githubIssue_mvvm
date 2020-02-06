package com.sujeet.githubissues.di

import androidx.room.Room
import com.sujeet.githubissues.App
import com.sujeet.githubissues.data.local.GithubIssueDao
import com.sujeet.githubissues.data.local.GithubIssueDb
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(app: App): GithubIssueDb {
        return Room.databaseBuilder(app, GithubIssueDb::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideIssueDao(db: GithubIssueDb): GithubIssueDao = db.githubDao()

}
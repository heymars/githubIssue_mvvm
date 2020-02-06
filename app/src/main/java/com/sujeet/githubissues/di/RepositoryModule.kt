package com.sujeet.githubissues.di

import com.sujeet.githubissues.data.repository.RemoteInterface
import com.sujeet.githubissues.data.repository.RemoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindNetworkRepository(repository: RemoteRepository): RemoteInterface
}
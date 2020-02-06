package com.sujeet.githubissues.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sujeet.githubissues.ui.GithubIssueViewModel
import com.sujeet.githubissues.ui.comments.CommentListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class])
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GithubIssueViewModel::class)
    fun bindGithubIssueViewModel(viewModel: GithubIssueViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentListViewModel::class)
    fun bindCommentListViewModel(viewModel: CommentListViewModel): ViewModel
}
package com.sujeet.githubissues.di

import com.sujeet.githubissues.ui.GithubIssueFragment
import com.sujeet.githubissues.ui.comments.CommentListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeTopFragmentInjector(): GithubIssueFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCommentFragmentInjector(): CommentListFragment

}
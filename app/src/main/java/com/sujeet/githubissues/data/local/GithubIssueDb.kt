package com.sujeet.githubissues.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sujeet.githubissues.models.*

@Database(entities = [GithubIssuesResponseModel::class], version = 1)
//@TypeConverters(UserConverter::class, PullRequestConverter::class, LabelsConverter::class)
abstract class GithubIssueDb : RoomDatabase() {
    abstract fun githubDao(): GithubIssueDao
}
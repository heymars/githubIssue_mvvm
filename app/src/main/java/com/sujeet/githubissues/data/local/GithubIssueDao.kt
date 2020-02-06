package com.sujeet.githubissues.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sujeet.githubissues.models.GithubIssuesResponseModel

import io.reactivex.Observable

@Dao
interface GithubIssueDao {
    @Query("SELECT * FROM GithubIssuesResponseModel")
    fun getAllIssues(): Observable<List<GithubIssuesResponseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(githubIssuesResponseModelList: List<GithubIssuesResponseModel>)

    @Query("DELETE FROM GithubIssuesResponseModel")
    fun deleteAll()
}
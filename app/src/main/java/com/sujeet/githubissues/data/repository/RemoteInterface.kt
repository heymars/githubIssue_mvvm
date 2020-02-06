package com.sujeet.githubissues.data.repository

import com.sujeet.githubissues.models.GithubIssuesResponseModel
import com.sujeet.githubissues.models.GithubCommentModel
import io.reactivex.Observable
interface RemoteInterface {
    fun getIssues(): Observable<List<GithubIssuesResponseModel>>
    fun getIssuesComments(issueNumber: Int): Observable<List<GithubCommentModel>>
}
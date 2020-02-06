package com.sujeet.githubissues.data.remote

import com.sujeet.githubissues.models.GithubIssuesResponseModel
import com.sujeet.githubissues.data.Constants
import com.sujeet.githubissues.models.GithubCommentModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {
    @GET(Constants.ISSUES)
    fun getIssues(): Observable<List<GithubIssuesResponseModel>>

    @GET(Constants.BASE_URL + Constants.ISSUES +"/{issue_number}/comments")
    fun getIssuesComments(@Path("issue_number") issueNumber: Int): Observable<List<GithubCommentModel>>

}
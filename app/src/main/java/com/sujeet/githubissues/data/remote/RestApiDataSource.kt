package com.sujeet.githubissues.data.remote

import javax.inject.Inject

class RestApiDataSource @Inject constructor(private val apiServices: ApiServices)  {
    fun getIssues() = apiServices.getIssues()

    fun getIssuesComments(issueNumber: Int) = apiServices.getIssuesComments(issueNumber)
}
package com.sujeet.githubissues.data.repository

import android.text.format.DateUtils
import com.sujeet.githubissues.data.local.GithubIssueDao
import com.sujeet.githubissues.models.GithubIssuesResponseModel
import com.sujeet.githubissues.data.remote.RestApiDataSource
import com.sujeet.githubissues.models.GithubCommentModel
import com.sujeet.githubissues.utils.PreferenceHelper
import com.sujeet.githubissues.utils.Resource
import com.sujeet.githubissues.utils.ResourceState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.Async
import timber.log.Timber
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*
import javax.inject.Inject
import android.os.AsyncTask.execute
import java.util.concurrent.Executors


class RemoteRepository @Inject constructor(private val restApiDataSource: RestApiDataSource,
                                            private val githubIssueDao: GithubIssueDao) : RemoteInterface {
    @Inject
    lateinit var preferenceHelper: PreferenceHelper
    override fun getIssues(): Observable<List<GithubIssuesResponseModel>> {
        if (preferenceHelper.lastSyncTime == 0L){
            preferenceHelper.lastSyncTime = System.currentTimeMillis()
            refreshIssueList()
        }
        if (!DateUtils.isToday(preferenceHelper.lastSyncTime)){
            refreshIssueList()
        }
       return githubIssueDao.getAllIssues()
    }

    override fun getIssuesComments(issueNumber: Int): Observable<List<GithubCommentModel>> {
       return restApiDataSource.getIssuesComments(issueNumber)
    }

    private fun refreshIssueList() {
            val dataExists = restApiDataSource.getIssues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .safeSubscribe(object :DisposableObserver<List<GithubIssuesResponseModel>>(){
                    override fun onNext(t: List<GithubIssuesResponseModel>) {
                        Timber.d("----------repository------${t.size}------")
                        Executors.newSingleThreadExecutor().execute(Runnable {
                            preferenceHelper.lastSyncTime = System.currentTimeMillis()
                            githubIssueDao.deleteAll()
                            githubIssueDao.insertAll(t)//replace with your code
                        })
                    }

                    override fun onComplete() {

                    }

                    override fun onError(e: Throwable) {
                        Timber.d("--------${e.message}----------")
                    }
                })
            }
        }
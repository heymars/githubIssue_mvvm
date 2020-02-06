package com.sujeet.githubissues.ui
import com.sujeet.githubissues.models.GithubIssuesResponseModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sujeet.githubissues.data.local.GithubIssueDao
import com.sujeet.githubissues.data.repository.RemoteRepository
import com.sujeet.githubissues.utils.Resource
import com.sujeet.githubissues.utils.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class GithubIssueViewModel @Inject constructor(private val remoteRepository: RemoteRepository, private val githubIssueDao: GithubIssueDao): ViewModel(),
    LifecycleObserver {
    val githubIssueLiveData : MutableLiveData<Resource<List<GithubIssuesResponseModel>>> = MutableLiveData()
    fun getIssue(){
        githubIssueLiveData.postValue(Resource(ResourceState.LOADING,null,null))
        remoteRepository.getIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .safeSubscribe(object :DisposableObserver<List<GithubIssuesResponseModel>>(){
                override fun onNext(t: List<GithubIssuesResponseModel>) {
                    githubIssueLiveData.postValue(Resource(ResourceState.SUCCESS,t,null))
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    githubIssueLiveData.postValue(Resource(ResourceState.ERROR,null,e.message))
                    Timber.d("--------${e.message}----------")
                }
            })
    }
}


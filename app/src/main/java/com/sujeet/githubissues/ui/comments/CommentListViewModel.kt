package com.sujeet.githubissues.ui.comments

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sujeet.githubissues.data.repository.RemoteRepository
import com.sujeet.githubissues.models.GithubCommentModel
import com.sujeet.githubissues.utils.Resource
import com.sujeet.githubissues.utils.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CommentListViewModel @Inject constructor(private val remoteRepository: RemoteRepository): ViewModel(),
    LifecycleObserver {
    val githubCommentLiveData : MutableLiveData<Resource<List<GithubCommentModel>>> = MutableLiveData()
    fun getComment(issueNumber:Int){
        githubCommentLiveData.postValue(Resource(ResourceState.LOADING,null,null))
        remoteRepository.getIssuesComments(issueNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .safeSubscribe(object : DisposableObserver<List<GithubCommentModel>>(){
                override fun onNext(t: List<GithubCommentModel>) {
                    githubCommentLiveData.postValue(Resource(ResourceState.SUCCESS,t,null))
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    githubCommentLiveData.postValue(Resource(ResourceState.ERROR,null,e.message))
                    Timber.d("--------${e.message}----------")
                }
            })
    }
}


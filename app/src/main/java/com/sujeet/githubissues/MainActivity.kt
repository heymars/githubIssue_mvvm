package com.sujeet.githubissues

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.sujeet.githubissues.ui.GithubIssueFragment
import com.sujeet.githubissues.ui.comments.CommentListFragment
import com.sujeet.githubissues.utils.ProgressDisplay
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), ProgressDisplay, GithubIssueFragment.OnFragmentInteractionListener,
    CommentListFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun showProgress() {
        progress_circular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_circular.visibility = View.GONE
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}

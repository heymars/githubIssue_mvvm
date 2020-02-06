package com.sujeet.githubissues.ui

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.sujeet.githubissues.R
import com.sujeet.githubissues.utils.ProgressDisplay
import com.sujeet.githubissues.utils.ResourceState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.github_issue_fragment.*
import timber.log.Timber
import javax.inject.Inject
import android.content.DialogInterface
import com.sujeet.githubissues.models.GithubIssuesResponseModel
import java.util.Collections.sort

class GithubIssueFragment : DaggerFragment(), OnListItemClickListener {

    private var listener: OnFragmentInteractionListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var progress: ProgressDisplay
    private var githubIssueAdapter: GithubIssueAdapter? = null

    companion object {
        fun newInstance() = GithubIssueFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.github_issue_fragment, container, false)
    }

    private val githubIssueViewModel: GithubIssueViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(GithubIssueViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubIssueViewModel.getIssue()
        githubIssueViewModel.githubIssueLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                handleGitIssueResponse(it.status, it.data, it.message)
            }        })
    }

    private fun handleGitIssueResponse(status: ResourceState, data: List<com.sujeet.githubissues.models.GithubIssuesResponseModel>?, message: String?) {
        when(status){
            ResourceState.LOADING -> showLoading()
            ResourceState.SUCCESS -> handleGetIssueSuccess(data)
            ResourceState.ERROR -> hideLoading(message)
        }
    }

    private fun handleGetIssueSuccess(data: List<GithubIssuesResponseModel>?) {
        progress.hideProgress()
        data?.let {
            sort(it
            ) { o1, o2 ->
                if (o1.updated_at == null || o2.updated_at == null) 0 else o1.updated_at.compareTo(
                    o2.updated_at
                )
            }
            Timber.d("----------------${it.size}-----------------")
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            githubIssueAdapter = GithubIssueAdapter(it as MutableList<GithubIssuesResponseModel>)
            recyclerView.adapter = githubIssueAdapter
            githubIssueAdapter?.setItemClickListener(this)

        }
    }

    private fun hideLoading(message: String?) {
        progress.hideProgress()
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        progress.showProgress()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
        if (requireActivity() is ProgressDisplay) {
            progress = requireActivity() as ProgressDisplay
        } else {
            throw RuntimeException("$context must implement ProgressDisplay")
        }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun onItemClicked(commentCount: Int, issueNumber:Int) {
        if (commentCount > 0){
            val bundle = Bundle()
            bundle.putInt("issueNumber",issueNumber)
            Navigation.findNavController(view!!).navigate(R.id.commentListFragment, bundle)
        }else{
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage("No comment found!")
                .setPositiveButton("Okay", DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                    dialog.dismiss()
                })

            // Create the AlertDialog object and return it
             builder.create()
             builder.show()

        }
    }

}

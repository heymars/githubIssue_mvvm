package com.sujeet.githubissues.ui.comments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.sujeet.githubissues.R
import com.sujeet.githubissues.models.GithubCommentModel
import com.sujeet.githubissues.utils.ProgressDisplay
import com.sujeet.githubissues.utils.ResourceState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comment_list.*
import timber.log.Timber
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CommentListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CommentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentListFragment : DaggerFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var issueNumber: Int = 0
    private var listener: OnFragmentInteractionListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var progress: ProgressDisplay
    private var githubCommentAdapter: GithubCommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            issueNumber = it.getInt("issueNumber")
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private val commentListViewModel: CommentListViewModel by lazy {
        ViewModelProvider(viewModelStore, viewModelFactory).get(CommentListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentListViewModel.getComment(issueNumber)
        commentListViewModel.githubCommentLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                handleCommentResponse(it.status, it.data, it.message)
            }        })
    }

    private fun handleCommentResponse(status: ResourceState, data: List<GithubCommentModel>?, message: String?) {
        when(status){
            ResourceState.LOADING -> showLoading()
            ResourceState.SUCCESS -> handleGetCommentSuccess(data)
            ResourceState.ERROR -> hideLoading(message)
        }    }

    private fun handleGetCommentSuccess(data: List<GithubCommentModel>?) {
        progress.hideProgress()
        data?.let {
            Timber.d("----------------${it.size}-----------------")
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            githubCommentAdapter = GithubCommentAdapter(requireContext(),it as MutableList<GithubCommentModel>)
            recyclerView.adapter = githubCommentAdapter
        }
    }

    private fun hideLoading(message: String?) {
        progress.hideProgress()
        Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        progress.showProgress()
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
        if (requireActivity() is ProgressDisplay) {
            progress = requireActivity() as ProgressDisplay
        } else {
            throw RuntimeException("$context must implement ProgressDisplay")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommentListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommentListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

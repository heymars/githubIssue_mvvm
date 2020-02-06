package com.sujeet.githubissues.ui

import android.annotation.SuppressLint
import com.sujeet.githubissues.models.GithubIssuesResponseModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sujeet.githubissues.R
import kotlinx.android.synthetic.main.layout_issue_item.view.*


class GithubIssueAdapter (private val githubIssueList: MutableList<GithubIssuesResponseModel>) : RecyclerView.Adapter<GithubIssuHolder>() {

    private var clickListener: OnListItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubIssuHolder {
        return GithubIssuHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_issue_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return githubIssueList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GithubIssuHolder, position: Int) {
        val githubIssuesResponseModel = githubIssueList[position]
        holder.tvIssueTitle.text = "${position+1} - ${githubIssuesResponseModel.title}"
        holder.tvIssueBody.text = githubIssuesResponseModel.body
        holder.itemView.setOnClickListener {
         clickListener?.onItemClicked(githubIssuesResponseModel.comments,githubIssuesResponseModel.number)
        }
    }

    fun setItemClickListener(clickListener: OnListItemClickListener){
        this.clickListener = clickListener
    }

}


interface OnListItemClickListener{
    fun onItemClicked(commentCount:Int,issueNumber:Int)
}


class GithubIssuHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvIssueTitle = view.tv_issue as TextView
    val tvIssueBody = view.tv_body as TextView
}
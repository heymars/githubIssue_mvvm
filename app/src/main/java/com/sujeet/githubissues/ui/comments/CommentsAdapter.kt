package com.sujeet.githubissues.ui.comments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sujeet.githubissues.R
import com.sujeet.githubissues.models.GithubCommentModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_comment_item.view.*


class GithubCommentAdapter (val context: Context, private val githubCommentList: MutableList<GithubCommentModel>) : RecyclerView.Adapter<CommentsHolder>() {

    private var clickListener: OnListItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder {
        return CommentsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_comment_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return githubCommentList.size
    }

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        val githubCommentModel = githubCommentList[position]
        holder.tvIssueTitle.text = githubCommentModel.body
        Glide.with(context).load(githubCommentModel.user.avatar_url).into(holder.ivCircularImage)
        holder.itemView.setOnClickListener {

        }
    }

    fun setItemClickListener(clickListener: OnListItemClickListener){
        this.clickListener = clickListener
    }

}


interface OnListItemClickListener{
    fun onItemClicked(typeName: String)
}


class CommentsHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvIssueTitle = view.tv_issue as TextView
    val ivCircularImage = view.profile_image as CircleImageView
}
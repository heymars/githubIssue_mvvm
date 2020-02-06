package com.sujeet.githubissues.models

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity
data class GithubIssuesResponseModel (
    @SerializedName("url") val url : String?= null,
    @SerializedName("repository_url") val repository_url : String?= null,
    @SerializedName("labels_url") val labels_url : String?= null,
    @SerializedName("comments_url") val comments_url : String?= null,
    @SerializedName("events_url") val events_url : String?= null,
    @SerializedName("html_url") val html_url : String?= null,
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String?= null,
    @SerializedName("number") val number : Int,
    @SerializedName("title") val title : String?= null,
//    @Embedded
//    @TypeConverters(UserConverter::class)
//    @SerializedName("user") val user : UserIssue,
//    @Embedded
//    @TypeConverters(LabelsConverter::class)
//    @SerializedName("labels") val labels : List<Labels>,
    @SerializedName("state") val state : String?= null,
    @SerializedName("locked") val locked : Boolean,
//    @SerializedName("assignee") val assignee : String="",
//    @SerializedName("assignees") val assignees : List<UserIssue>,
//    @SerializedName("milestone") val milestone : String="",
    @SerializedName("comments") val comments : Int,
    @SerializedName("created_at") val created_at : String?= null,
    @SerializedName("updated_at") val updated_at : String?= null,
    @SerializedName("closed_at") val closed_at : String?= null,
    @SerializedName("author_association") val author_association : String?= null,
//    @Embedded
//    @TypeConverters(PullRequestConverter::class)
//    @SerializedName("pull_request") val pull_request : PullRequest,
    @SerializedName("body") val body : String?= null
)
//
//@Entity
//data class Labels (
//    @PrimaryKey
//    @SerializedName("id") val idLabels : Int,
//    @SerializedName("node_id") val node_idLabels : String,
//    @SerializedName("url") val urlLabels : String,
//    @SerializedName("name") val name : String,
//    @SerializedName("color") val color : String,
//    @SerializedName("default") val default : Boolean,
//    @SerializedName("description") val description : String
//)

//@Entity
//data class PullRequest (
//    @SerializedName("url") val urlPullRequest : String,
//    @SerializedName("html_url") val html_urlPullRequest : String,
//    @SerializedName("diff_url") val diff_url : String,
//    @SerializedName("patch_url") val patch_url : String
//)

data class Assignee(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int
)

//@Entity
//data class UserIssue (
//    @SerializedName("login") val login : String,
//    @PrimaryKey
//    @SerializedName("id") val idUserIssue : Int,
//    @SerializedName("node_id") val node_idUserIssue : String,
//    @SerializedName("avatar_url") val avatar_url : String,
//    @SerializedName("gravatar_id") val gravatar_id : String,
//    @SerializedName("url") val urlUserIssue : String,
//    @SerializedName("html_url") val html_urlUserIssue : String,
//    @SerializedName("followers_url") val followers_url : String,
//    @SerializedName("following_url") val following_url : String,
//    @SerializedName("gists_url") val gists_url : String,
//    @SerializedName("starred_url") val starred_url : String,
//    @SerializedName("subscriptions_url") val subscriptions_url : String,
//    @SerializedName("organizations_url") val organizations_url : String,
//    @SerializedName("repos_url") val repos_url : String,
//    @SerializedName("events_url") val events_urlUserIssue : String,
//    @SerializedName("received_events_url") val received_events_url : String,
//    @SerializedName("type") val type : String,
//    @SerializedName("site_admin") val site_admin : Boolean
//)

//class UserConverter {
//
//    @TypeConverter
//    fun fromUserIsseToList(value: List<UserIssue>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<UserIssue>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun toUserIsseList(value: String): List<UserIssue> {
//        val gson = Gson()
//        val type = object : TypeToken<List<UserIssue>>() {}.type
//        return gson.fromJson(value, type)
//    }
//}
//
//
//class PullRequestConverter {
//
//    @TypeConverter
//    fun fromPullRequestToList(value: List<PullRequest>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<PullRequest>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun toPullRequestList(value: String): List<PullRequest> {
//        val gson = Gson()
//        val type = object : TypeToken<List<PullRequest>>() {}.type
//        return gson.fromJson(value, type)
//    }
//}
//
//class LabelsConverter {
//
//    @TypeConverter
//    fun fromLabelsToList(value: List<Labels>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<Labels>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun toLabelsList(value: String): List<Labels> {
//        val gson = Gson()
//        val type = object : TypeToken<List<Labels>>() {}.type
//        return gson.fromJson(value, type)
//    }
//}

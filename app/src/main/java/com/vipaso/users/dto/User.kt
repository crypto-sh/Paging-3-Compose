package com.vipaso.users.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.*


@Keep
data class User(
    val login : String = "",
    val id : String = "",
    val url : String = "",
    val type : String = "",
    @Keep val score : Float = 0.0f,
    @SerializedName("node_id") val nodeId : String = "",
    @SerializedName("html_url") val htmlUrl : String = "",
    @SerializedName("avatar_url") val avatarUrl : String = ""
)
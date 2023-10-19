package com.vipaso.users.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vipaso.core.defaultApiPageSize


@Keep
data class BaseResponse(
    @SerializedName("total_count") val totalCount: Long = 0L,
    @SerializedName("incomplete_results") val incompleteResults: Boolean = false,
    @SerializedName("items") val users : List<User> = listOf()
) {

    fun nextPage(page : Int) : Int? {
        val size = defaultApiPageSize * (if (page == 0) 1 else page)
        return if (size <= totalCount) page + 1 else null
    }
}


package com.vipaso.users.useCases

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vipaso.core.api.ApiService
import com.vipaso.users.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataSourceUsers(
    private val query: String,
    private val pageSize : Int,
    private val apiService: ApiService
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition: Int = state.anchorPosition ?: return null
        val (_, prevKey, nextKey) = state.closestPageToPosition(anchorPosition) ?: return null
        if (prevKey != null) {
            return prevKey + 1
        }
        if (nextKey != null) {
            return nextKey - 1
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return withContext(Dispatchers.Default) {
            return@withContext try {
                val current: Int = params.key ?: 1
                val result = apiService.users(query.ifEmpty { "''" }, current, pageSize)
                LoadResult.Page(
                    data = result.users,
                    prevKey = null,
                    nextKey = result.nextPage(current)
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}
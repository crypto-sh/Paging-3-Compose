package com.vipaso.users.useCases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vipaso.core.api.ApiService
import com.vipaso.core.defaultApiPageSize
import com.vipaso.users.dto.User
import kotlinx.coroutines.flow.Flow



interface UseCaseUsers {

    fun users(query: String = ""): Flow<PagingData<User>>

}

class UseCaseUsersImpl(
    private val apiService: ApiService
) : UseCaseUsers {

    override fun users(query: String): Flow<PagingData<User>> {
        return Pager(PagingConfig(defaultApiPageSize)) {
            DataSourceUsers(query, defaultApiPageSize, apiService)
        }.flow
    }
}
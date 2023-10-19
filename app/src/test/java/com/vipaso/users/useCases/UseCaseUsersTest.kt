package com.vipaso.users.useCases

import com.vipaso.core.BaseUnitTest
import com.vipaso.core.api.ApiService
import com.vipaso.core.defaultApiPageSize
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UseCaseUsersTest : BaseUnitTest() {

    private val apiService : ApiService = mockk()

    private val useCase : UseCaseUsers = UseCaseUsersImpl(apiService)

    //TODO we can't provide test for this step as we have to use Pager library
    //I need more time to provide tests for this
    fun users_no_filter_success() = runTest {
        val query = "expected query"
        val factory = DataSourceUsers(query, defaultApiPageSize, apiService)
        val result = useCase.users(query).first()
    }

}
package com.vipaso.users.useCases

import androidx.paging.PagingSource
import com.vipaso.core.BaseUnitTest
import com.vipaso.core.api.ApiService
import com.vipaso.users.dto.BaseResponse
import com.vipaso.users.dto.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class DataSourceUsersTest : BaseUnitTest() {

    private val loadParams: PagingSource.LoadParams<Int> = mockk(relaxed = true)

    private val expectedPageSize = 10
    private val expectedQuery = "expected query"
    private val expectedError = IOException("There is an error!")
    private val apiService: ApiService = mockk()
    private val dataSource = DataSourceUsers(expectedQuery, expectedPageSize, apiService)

    @Test
    fun test_00_data_source_load_sucess() = runTest {
        val page = 1
        val apiResult = BaseResponse()
        coEvery { apiService.users(expectedQuery, page, expectedPageSize) } returns apiResult

        val result = dataSource.load(loadParams)

        coVerify(exactly = 1) { apiService.users(expectedQuery, page, expectedPageSize) }
        assertEquals(
            PagingSource
                .LoadResult
                .Page(apiResult.users, null, apiResult.nextPage(page)), result
        )
    }

    @Test
    fun test_01_data_source_load_failure() = runTest {
        coEvery { apiService.users(expectedQuery, any(), expectedPageSize) } throws expectedError

        val result = dataSource.load(loadParams)

        coVerify(exactly = 1) { apiService.users(expectedQuery, any(), expectedPageSize) }

        val expected = PagingSource.LoadResult.Error<Int, User>(expectedError)
        assertEquals(expected, result)
    }
}
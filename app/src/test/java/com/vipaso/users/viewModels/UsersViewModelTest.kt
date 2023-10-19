package com.vipaso.users.viewModels

import androidx.paging.PagingData
import com.vipaso.core.BaseUnitTest
import com.vipaso.users.dto.User
import com.vipaso.users.useCases.UseCaseUsers
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test


class UsersViewModelTest : BaseUnitTest() {

    private val useCase : UseCaseUsers = mockk()

    private val viewModel : UsersViewModel = UsersViewModelImpl(useCase)

    @Test
    fun test_00_users_success() = runTest {
        val expected = PagingData.from(listOf<User>())
        every { useCase.users(any()) } returns flow {
            emit(expected)
        }

        viewModel.onCreate(mockk())
        val items = viewModel.users().first()

        coVerify(exactly = 1) { useCase.users("") }

        assertEquals(expected, items)
    }

    @Test
    fun test_01_users_filter_success() = runTest {
        val query = "expected query"
        val expected = PagingData.from(listOf<User>())
        every { useCase.users(any()) } returns flow {
            emit(expected)
        }

        viewModel.filter(query)
        val items = viewModel.users().first()

        coVerify(exactly = 1) { useCase.users(query) }

        assertEquals(expected, items)
    }
}
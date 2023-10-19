package com.vipaso.users.dto

import com.vipaso.core.BaseUnitTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import org.junit.Test

class BaseResponseShould : BaseUnitTest() {

    @Test
    fun test_00_has_next_page() {
        val page = 1
        val data = BaseResponse(40L, false, listOf())
        assertEquals(page + 1, data.nextPage(page))
    }

    @Test
    fun test_01_has_not_next_page() {
        val page = 2
        val data = BaseResponse(40L, false, listOf())
        assertNull(data.nextPage(page))
    }
}
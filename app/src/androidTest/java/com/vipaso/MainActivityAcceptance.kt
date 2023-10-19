package com.vipaso

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vipaso.users.ui.clearButtonTestTag
import com.vipaso.users.ui.searchFieldTestTag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityAcceptance {

    private lateinit var appContext : Context

    @get:Rule(order = 0)  val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)  val compose  = createAndroidComposeRule<MainActivity>()

    @Before
    fun before() {
        hiltRule.inject()
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun searchTextFieldVisibility() {
        compose.onNodeWithText(appContext.getString(R.string.search_place_holder)).assertIsDisplayed()
    }

    @Test
    fun clearButtonVisibility() {
        compose.onNodeWithTag(clearButtonTestTag).assertDoesNotExist()
        compose.onNodeWithTag(searchFieldTestTag).performTextInput("Test Text")
        compose.onNodeWithTag(clearButtonTestTag).assertIsDisplayed()
    }
}
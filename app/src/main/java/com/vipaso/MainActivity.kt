package com.vipaso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.vipaso.core.theme.VipasoTheme
import com.vipaso.users.ui.ScreenMain
import com.vipaso.users.viewModels.UsersViewModel
import com.vipaso.users.viewModels.UsersViewModelImpl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : UsersViewModel by viewModels<UsersViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        setContent { VipasoTheme { ScreenMain(viewModel) } }
    }
}


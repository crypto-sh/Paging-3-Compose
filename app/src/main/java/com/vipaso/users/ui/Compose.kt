package com.vipaso.users.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.vipaso.R
import com.vipaso.core.composable.ComposeEmptyResult
import com.vipaso.core.composable.ComposeErrorPage
import com.vipaso.core.composable.ComposeLoadingPage
import com.vipaso.users.dto.User
import com.vipaso.users.viewModels.UsersViewModel


const val clearButtonTestTag = "clear_button"
const val searchFieldTestTag = "search_field"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMain(viewModel: UsersViewModel) {
    val users = viewModel.users().collectAsLazyPagingItems()
    var filter by remember { mutableStateOf("") }
    var clear by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                TextField(
                    modifier = Modifier.testTag(searchFieldTestTag).fillMaxWidth(),
                    value = filter,
                    onValueChange = {
                        filter = it
                        viewModel.filter(filter)
                        clear = filter.isNotEmpty()
                    },
                    label = {
                        Text(text = stringResource(id = R.string.search_place_holder))
                    }
                )
            }, actions = {
                if (clear) {
                    IconButton(
                        modifier = Modifier.testTag(clearButtonTestTag),
                        onClick = {
                        filter = ""
                        viewModel.filter(filter)
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
            })
        }
    ) { padding ->
        ComposeContentUsersScreen(Modifier.padding(padding), users)
    }

}

@Composable
fun ComposeContentUsersScreen(modifier: Modifier, users: LazyPagingItems<User>) {
    when (users.loadState.refresh) {
        LoadState.Loading -> {
            ComposeLoadingPage()
        }
        is LoadState.Error -> {
            val data = users.loadState.refresh as LoadState.Error
            ComposeErrorPage(error = data.error)
        }
        else -> {
            if (users.itemCount == 0) {
                ComposeEmptyResult()
            } else {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(users.itemCount) { index ->
                        users[index]?.let { user ->
                            ComposeUser(user = user)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComposeUser(user: User) {
    Row(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp)
            .height(64.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(56.dp)
                .padding(2.dp)
                .clip(CircleShape),
            model = user.avatarUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = user.login, maxLines = 1, color = Color.Black)
            Text(text = user.type, maxLines = 1, color = Color.Black)
        }
    }
}

package com.vipaso.users.viewModels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vipaso.users.dto.User
import com.vipaso.users.useCases.UseCaseUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UsersViewModel : DefaultLifecycleObserver {

    fun filter(query: String)

    fun users() : Flow<PagingData<User>>

}

@HiltViewModel
class UsersViewModelImpl @Inject constructor(
    private val useCaseUser : UseCaseUsers
) : UsersViewModel, ViewModel() {

    private var filterJob : Job? = null
    private val _query : Channel<String> = Channel(Channel.CONFLATED)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        _query.trySend("")
    }

    override fun filter(query: String) {
        filterJob?.cancel()
        filterJob = viewModelScope.launch {
            delay(1000)
            _query.trySend(query)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun users(): Flow<PagingData<User>> = _query.receiveAsFlow().flatMapLatest { query ->
        useCaseUser.users(query).cachedIn(viewModelScope)
    }

}
package com.vipaso.core.di

import com.vipaso.core.api.ApiService
import com.vipaso.users.useCases.UseCaseUsers
import com.vipaso.users.useCases.UseCaseUsersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideUseCaseUsers(apiService: ApiService) : UseCaseUsers {
        return UseCaseUsersImpl(apiService)
    }

}
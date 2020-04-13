package com.mario.examplemvvm.koin

import com.mario.core.client.ApiClient
import com.mario.core.domain.cache.UserCache
import com.mario.core.domain.provider.ProviderUser
import com.mario.core.domain.repository.RepositoryUser
import com.mario.examplemvvm.client.ApiClientImpl
import org.koin.dsl.module

val domainModule = module {

    single { ProviderUser(get(),get()) }

    single { RepositoryUser(get()) }

    single { UserCache() }

    single<ApiClient> { ApiClientImpl(get()) }
}
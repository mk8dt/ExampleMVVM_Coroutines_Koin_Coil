package com.mario.examplemvvm.koin

import com.mario.core.domain.provider.ProviderUser
import com.mario.core.domain.repository.RepositoryUser
import com.mario.core.domain.service.ServiceUser
import org.koin.dsl.module

val domainModule = module {

    single { ServiceUser(get()) }

    single { ProviderUser(get()) }

    single { RepositoryUser(get()) }
}
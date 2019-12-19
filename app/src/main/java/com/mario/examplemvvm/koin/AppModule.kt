package com.mario.examplemvvm.koin

import android.content.res.Resources
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mario.core.client.ApiClient
import com.mario.examplemvvm.client.ApiClientImpl
import com.mario.examplemvvm.client.RetrofitApiClient
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ApiClient> { ApiClientImpl(get()) }

    single<RetrofitApiClient> { provideRetrofitClient() }

    single<Resources> { androidApplication().resources }
}

private fun provideRetrofitClient() = Retrofit.Builder()
    .baseUrl(RetrofitApiClient.BASE_URL)
    .client(OkHttpClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()
    .create(RetrofitApiClient::class.java)

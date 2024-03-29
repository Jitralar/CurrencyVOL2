package com.upce.currencyvol2.data.di

import com.upce.currencyvol2.data.CurrencyAPI //FIXME
import com.upce.currencyvol2.data.main.DefaultMainRep
import com.upce.currencyvol2.data.main.MainRepository
import com.upce.currencyvol2.data.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "http://api.exchangeratesapi.io/v1/"

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): CurrencyAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyAPI::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api : CurrencyAPI): MainRepository = DefaultMainRep(api)

    @Singleton
    @Provides
    fun provideDispatcher(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }



}
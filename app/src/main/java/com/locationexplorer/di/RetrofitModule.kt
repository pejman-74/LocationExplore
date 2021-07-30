package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.BASE_URL
import com.locationexplorer.util.network.ConnectionChecker
import com.locationexplorer.util.network.ConnectionCheckerImpl
import com.locationexplorer.util.network.NetworkConnectionInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideNetworkChecker(@ApplicationContext context: Context): ConnectionChecker =
        ConnectionCheckerImpl(context)

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(connectionChecker: ConnectionChecker): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(connectionChecker)

    @Provides
    @Singleton
    @Named("NetworkConnectionClient")
    internal fun provideNetworkConnectionInterceptorClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(
        mosh: Moshi,
        @Named("NetworkConnectionClient") networkConnectionInterceptorClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(mosh))
            .client(networkConnectionInterceptorClient)
            .build()

}
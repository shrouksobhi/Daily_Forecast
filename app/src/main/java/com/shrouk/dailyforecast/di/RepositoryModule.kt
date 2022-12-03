package com.shrouk.dailyforecast.di


import com.shrouk.dailyforecast.repositories.Repository
import com.shrouk.dailyforecast.dataSource.localDataSource.DaoInterface
import com.shrouk.dailyforecast.dataSource.remoteDataSource.Api
import com.shrouk.dailyforecast.repositories.ForecastRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideApiInerface(retrofit: Retrofit)= retrofit.create(Api::class.java)


    @Provides
    @Singleton
    fun provideRepository(apiInterface: Api,forecastDao: DaoInterface)
            = Repository(apiInterface, forecastDao) as ForecastRepository
}
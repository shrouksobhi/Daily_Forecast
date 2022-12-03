package com.shrouk.dailyforecast.di

import android.content.Context
import androidx.room.Room
import com.shrouk.dailyforecast.dataSource.localDataSource.DaoInterface
import com.shrouk.dailyforecast.dataSource.localDataSource.ForeCastDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideForecastDatabase(@ApplicationContext applicationContext: Context): ForeCastDataBase {
        return Room.databaseBuilder(
            applicationContext,
            ForeCastDataBase::class.java,
            "forecast_Table"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(foreCastDatabase: ForeCastDataBase):DaoInterface {
        return foreCastDatabase.forecastDatabaseDao()
    }
}
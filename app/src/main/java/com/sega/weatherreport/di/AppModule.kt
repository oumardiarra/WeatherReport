package com.sega.weatherreport.di


import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sega.weatherreport.data.local.WeatherDatabase
import com.sega.weatherreport.data.remote.api.WeatherApiService
import com.sega.weatherreport.data.remote.api.WeatherApiService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()

    }

    @Provides
    fun provideListOfWaitingWords(): MutableList<String> {
        return mutableListOf(
            "Nous téléchargeons les données…",
            "C’est presque fini…",
            "Plus que quelques secondes avant d’avoir le résultat…"
        )
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext app: Context): WeatherDatabase {
        return Room.databaseBuilder(
            app,
            WeatherDatabase::class.java,
            "weatherdb.db"
        ).build()
    }
}
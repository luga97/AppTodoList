package com.example.AppTodoList.di

import android.content.Context
import com.example.AppTodoList.data.local.AppDatabase
import com.example.AppTodoList.data.local.ItemDao
import com.example.AppTodoList.data.remote.itemRemoteDataSource
import com.example.AppTodoList.data.remote.ItemService
import com.example.AppTodoList.data.repository.ItemRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideItemService(retrofit: Retrofit): ItemService = retrofit.create(ItemService::class.java)

    @Singleton
    @Provides
    fun provideItemRemoteDataSource(itemService: ItemService) = itemRemoteDataSource(itemService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideItemDao(db: AppDatabase) = db.itemDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: itemRemoteDataSource,
                          localDataSource: ItemDao) =
        ItemRepository(remoteDataSource, localDataSource)
}
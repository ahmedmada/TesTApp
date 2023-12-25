package com.swensonhe.test.di

import android.content.Context
import androidx.room.Room
import com.swensonhe.test.api.ApiHelper
import com.swensonhe.test.api.ApiHelperImpl
import com.swensonhe.test.api.ApiService
import com.swensonhe.test.local.ItemDao
import com.swensonhe.test.local.ItemDatabase
import com.swensonhe.test.utils.NetworkConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: ApiService): ApiHelper {
        return ApiHelperImpl(api)
    }

    @Provides
    fun provideItemDao(itemDatabase: ItemDatabase): ItemDao {
        return itemDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideItemDatabase(@ApplicationContext appContext: Context): ItemDatabase {
        return Room.databaseBuilder(
            appContext,
            ItemDatabase::class.java,
            "ItemsDB"
        ).build()
    }
}


//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule{
//
//    @Provides
//    fun provideBaseUrl() = "https://mocki.io/v1/"
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }else{
//        OkHttpClient
//            .Builder()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .build()
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
//
//}

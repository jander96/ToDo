package todo.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import todo.framework.network.ApiInterceptor
import todo.framework.network.ToDoApiServices
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggerBody = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val loggerHeader = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .addInterceptor(loggerBody)
            .addInterceptor(loggerHeader)
            .callTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {

        val BASE_URL = "https://api.todoist.com/rest/v2/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitServices(retrofit: Retrofit): ToDoApiServices {
        return retrofit.create(ToDoApiServices::class.java)
    }





}
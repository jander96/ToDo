package todo.framework.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val BASE_URL = "https://api.todoist.com/rest/v2/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val loggerBody = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val loggerHeader = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .addInterceptor(loggerBody)
        .addInterceptor(loggerHeader)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()



    object TodoApiByRetrofit {
        val retrofitServices: ToDoApiServices by lazy {
         retrofit.create(ToDoApiServices::class.java)
        }
    }
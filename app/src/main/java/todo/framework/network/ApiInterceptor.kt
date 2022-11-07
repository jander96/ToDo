package todo.framework.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer 46a6d8fd8b25cd3fb9a07ee44175b62ae35fb419")
            .build()
        return chain.proceed(request)
    }
}
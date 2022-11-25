package todo.domain

sealed class ResponseState<T>(
    val data: T? = null,
    val messange: String? = null
){
    class Success<T>(data: T ): ResponseState<T>(data)
    class Error<T>(messange: String?, data: T? = null): ResponseState<T>(data,messange)
    class Loading<T>:ResponseState<T>()
}

package com.sega.weatherreport.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val progress: Int? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Progress<T>(progress: Int, data: T? = null, message: String? = null) :
        Resource<T>(data, message, progress)

}

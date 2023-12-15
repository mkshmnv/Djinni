package com.mkshmnv.djinni.repository

import com.mkshmnv.djinni.Logger

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>() {
        init {
            Logger.logcat("Data: $data", "Resource Success")
        }
    }
    data class Error(val message: String) : Resource<Nothing>(){
        init {
            Logger.logcat("Data: $message", "Resource Error")
        }
    }
    object Loading : Resource<Nothing>()
}